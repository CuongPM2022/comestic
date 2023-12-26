(async function() {
	const $ = document.querySelector.bind(document);
	const formatNumber = new Intl.NumberFormat();
	let str, str1, temp;
	let offset = 0, maxitem = 8;
	var paging;

	/* Functions Common */
	function getData(path, callback)
	{
		return fetch(path)
			   .then(response => response.json())
			   .then(data => callback(data));
	}

	function getParentBySelector(element, selector)
	{
		if(element.matches(selector)) { return element; }
		var temp = element;
		do {
			temp = temp.parentElement;
		} while(!temp.matches(selector));
		return temp;
	}

	function renderUIManufactureList(data) {
		str = ``;
		data.forEach(manufacture => {
			str += `<span class="filter__list__item" data-index="${manufacture.id}">
				${manufacture.name}
			</span>`;
		$('[data-name="manufactureid"]').querySelector('.filter__list').innerHTML = str;
		});
	}

	function renderUICategoryList(data) {
		str = '';
		data.listData.forEach(category => {
			str += `<span class="filter__list__item" data-index="${category.id}">
						${category.name}
					</span>`;
		});
		$('[data-name="categoryid"]').querySelector('.filter__list').innerHTML = str;
	}

	function renderUIListProduct(data) {
	    let percentDes = null;
		str = '';
		data.listData.forEach(product => {
			percentDes = product.listVariety[0].percentDes;
			str += `
					<div class="col p-2-4 t-4 m-6">
                        <div class="product__item ${percentDes != 0 ? 'sale' : ''}">
                            <div class="product__image" 
                                 style="background-image: url(${product.image});">
                            </div>
                            <div class="product__info">
                                <span class="product__supply">${product.manufactureName}</span>
                                <div class="product__name">${product.name}</div>
                                <div class="product__price">
                                    <span class="price__sold">
                                    	${formatNumber.format(product.price * (1 - percentDes/100))}đ
                                    </span>
                                    <span class="percent">Giảm ${percentDes}%</span>
                                </div>
                                <div class="product__review">
                                    <span class="product__star__num">${product.star}</span>
                                    <span class="product__star__icon">
                                        <i class="fa-solid fa-star"></i>
                                    </span>
                                    <span class="product__number__comment">(${product.totalComment})</span>
                                </div>
                            </div>
                        </div>
                    </div>`;
		});
		$('.productList .row').innerHTML += str;
		$('.product__text__header').innerText = `Có tất cả ${data.totalItem} sản phẩm`;
		temp = $('.view__more__product');
		if(offset >= data.totalItem) {
			temp.classList.add('hidden');
		}
		else {
			temp.classList.remove('hidden');
		}
		temp.querySelector('span').innerText = `Xem thêm ${data.totalItem - offset} sản phẩm`;
	}

	function getProductListFromAPI(maxItem) {
		paging['offset'] = offset;
		paging['maxItem'] = maxItem;
		temp = encodeURIComponent(JSON.stringify(paging));
		getData(`/api-product?action=allProduct&paging=${temp}`, renderUIListProduct);
		offset += maxItem;
	}

	//Call API

	// Call API get Manufacture
	await getData(`/api-manufacture?action=allItem`, renderUIManufactureList);

	// Call API get Category
	await getData(`/api-category?action=allCategory`, renderUICategoryList);

	filterAndSorter(data => {
		paging = data;
		offset = 0;
		$('.productList .row').innerHTML = '';
		getProductListFromAPI(10);
	});

	// Assign Event
	$('.view__more__product').addEventListener('click', (e) => {
		getProductListFromAPI(5);
	});

})();