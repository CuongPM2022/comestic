const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);
var offset = 0;

function getData(path, callback)
{
	return fetch(path)
		  .then(response => response.json())
		  .then(data => callback(data));
}

// CallAPI Slide
getData('/api-slide?action=allSlide', data => {
	let strSlide = '';
	let strDot = '';
	data.listData.forEach((slide, index) => {
		strSlide += `
			<li class="slide__item ${index == 0 ? 'active':''}"
				style="background-image: url(${slide.imageName});">
				<a href="#"></a>
			</li>`;

		strDot += `<span class="dot__item ${index == 0 ? 'active':''}"></span>`;
	});
	$('.slide__list').innerHTML = strSlide;
	$('.slide__dot').innerHTML = strDot;
	slider();
});

function slider() {
	var slideIndex = 0;
	var setTimeoutIndex;
	const slideList = $$('.slide__item');
	const dotList = $$('.dot__item');
	var length = dotList.length;
	var time = 8000;

	function change(index)
	{
		slideList[index].classList.toggle('active');
		dotList[index].classList.toggle('active');
	}

	function nextSlide()
	{
		change(slideIndex);
		slideIndex++;
		if(slideIndex >= length) {
			slideIndex = 0;
		}
		change(slideIndex);
		setTimeoutIndex = setTimeout(nextSlide,time);
	}

	function curentSlide(index)
	{
		change(slideIndex);
		change(index);
		slideIndex = index;
		clearTimeout(setTimeoutIndex);
		setTimeoutIndex = setTimeout(nextSlide, time);
	}

	//xu ly su kien nhan dotItem
	Array.from(dotList).forEach((dotItem, index) => {
		dotItem.setAttribute('index', index);
		dotItem.onclick = function()
		{
			curentSlide(this.getAttribute('index'));
		}
	})
	curentSlide(0);
}

(async function home() {
	
	//call API Category
	await getData('/api-category?action=allCategory', (data) => {
			let str = '';
			data.listData.forEach(category => {
				str += `
					<li class="slider__category__item">
						<a href="/product?action=allProduct&categoryId=${category.id}">
							<span class="slider__item__icon"> 
								<img src="${category.imageName}">
							</span> <span class="slider__item__text">${category.name}</span> <!-- active: .active -->
								<span class="slider__item__spand active"> <i
									class="fa-solid fa-chevron-right"></i>
							</span>
						</a>
					</li>`;
			})

			$('.slider__category__list').innerHTML = str;
		});

	//call API get Product
	function getStringProduct(product, width)
	{
		let percentDes = product.listVariety[0].percentDes;
		//Sale: .sale
		let str =  `
					<div class="col ${width} t-4 m-6">
						<a href="product?action=productDetail&id=${product.id}" class="product__item__link">			
							<div class="product__item ${percentDes!=0 ? 'sale':''}">
								<div class="product__image"
									style="background-image: url(${product.image});">
								</div>
								<div class="product__info">
									<span class="product__supply">${product.manufactureName}</span>
									<div class="product__name">${product.name}</div>
									<div class="product__price">
										<span class="price__sold">${formatNumber.format(product.price)}đ</span> 
										<span class="percent">Giảm ${percentDes}%</span>
									</div>
									<div class="product__review">
										<span class="product__star__num">${product.star}</span> <span
											class="product__star__icon"> <i
											class="fa-solid fa-star"></i>
										</span> 
										<span class="product__number__comment">
											${product.totalComment ? '(' + product.totalComment + ')':''}
										</span>
									</div>
								</div>
							</div>
						</a>
					</div>`;
		return str;
	}

	//sale product
	var formatNumber = new Intl.NumberFormat();
	var paging = {
		'offset' : 0,
		'maxItem' : 5,
		'sortName' : 'percentdes',
		'sortBy' : 'desc',
		'filter' : {
			'sale' : 'sale'
		}
	};

	let str = '';
	let strPaging = encodeURIComponent(JSON.stringify(paging));
	await getData('/api-product?action=allProduct&paging=' + strPaging, data => {
		str = '';
		data.listData.forEach(product => {
			str += getStringProduct(product,'p-2-4');
		});
		$('.saleProduct .listProduct .row').innerHTML = str;
	});

	// new product
	paging = {
		'offset' : 0,
		'maxItem' : 5,
		'sortName' : 'date',
		'sortBy' : 'desc'
	};
	strPaging = encodeURIComponent(JSON.stringify(paging));
	await getData('/api-product?action=allProduct&paging=' + strPaging, data => {
		str = '';
		data.listData.forEach(product => {
			str += getStringProduct(product,'p-12');
		});
		$('.hotProductList.new .row').innerHTML = str;
	});

	// seller peoduct
	paging = {
		'offset' : 0,
		'maxItem' : 5
	};
	strPaging = encodeURIComponent(JSON.stringify(paging));
	await getData('/api-product?action=sellProduct&paging=' + strPaging, data => {
		str = '';
		data.listData.forEach(product => {
			str += getStringProduct(product,'p-12');
		});
		$('.hotProductList.sell .row').innerHTML = str;
	});

	// all product
	function viewMoreProduct(maxItem = 8) {
		paging = {
			'offset' : offset,
			'maxItem' : maxItem,
			'sortName' : 'view',
			'sortBy' : 'desc'
		};
		strPaging = encodeURIComponent(JSON.stringify(paging));
		getData('/api-product?action=allProduct&paging=' + strPaging, data => {
			str = '';
			data.listData.forEach(product => {
				str += getStringProduct(product,'p-3');
			});
			$('.allProduct .row').innerHTML += str;
			offset += maxItem;
			if(offset < data.totalItem) {
				$('.view__more__product').classList.add('show');
			}
			else {
				$('.view__more__product').classList.remove('show');
			}
		});
	}

	viewMoreProduct(12);

	$('.view__more__product').addEventListener('click',() => viewMoreProduct(4));
	
})();

