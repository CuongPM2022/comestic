const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);
const formatNumber = new Intl.NumberFormat();
var varietySelect;

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

//CallAPI
getData(`/api-product?action=detailProduct&id=${idProduct}`, data => {
	productDetail(data);
});

function productDetail(product) {
	//gan du lieu
	let str = '', str1 = '';
	const numVariety = product.listVariety.length;
	const varietyAvatar = product.listVariety[0];
	let attributeName = {};
	let attributeValue = {};
	let attributeSelect = {};

	$('.directory_text.category').innerText = product.categoryName;
	$('.directory_text.product').innerText = product.name;
	$('.product__intro .name').innerText = product.name;

	const star = Math.round(product.star);
	str = '';
	for(let i=1; i <= 5; i++) {
		str+= `<i class="fa-solid fa-star ${i<=star ? 'active':''}"></i>`;
	}
	$('.product__intro .stars').innerHTML = str;
	$('.num_review').innerText = `${product.totalPreview > 0 ? product.totalPreview:'Chưa có'} đánh giá`;
	$('.price__num').innerText = `${formatNumber.format(varietyAvatar.price)}đ`;
	$('.info__detail__describe').innerHTML = product.shortDescription;

	//Attributes
	varietySelect = product.listVariety[0];
	updateUIState();

	// Style select
	if(numVariety == 1) {
		$('.style__select__contain').classList.add('hide');
	}
	else {
		handleProductManyVariety();
	}

	// Images
	$('.img_show').style.backgroundImage = `url(${varietyAvatar.image})`;
	if(numVariety > 1) {
		str = '';
		product.listVariety.forEach((variety, index) => {
			str += `
				<img varietyId="${variety.id}" class="img_other ${index == 0 ? 'active':''}" 
					 src="${variety.image}" >`;
		});
		$('.imgs').innerHTML = str;
	}


	if(numVariety == 1) {
		$('.all_text').classList.add('hide');
	}
	else {
		$('.all_text').innerText = `Tất cả hình ảnh (1/${numVariety})`;
	}

	if(product.longDescription == null) {
		$('.describe__detail').classList.add('hide');
	}
	else {
		$('.describe__detail__content').innerHTML = product.longDescription;
	}

	function getAttributeFromListVariety(listVariety) {
		let attributeValue = {};
		for(let i=0; i<listVariety.length; i++) {
			let listAttribute = listVariety[i].listAttribute;
			for(let j=0; j<listAttribute.length; j++) {
				let id = listAttribute[j].id;
				let value = listAttribute[j].value;

				attributeName[id] = listAttribute[j].name;
				attributeSelect[id] = null;
				if(!attributeValue[id]) {
					attributeValue[id] = [];
				}
				if(!attributeValue[id].includes(value)) {
					attributeValue[id].push(value);
				}
			}
		}
		return attributeValue;
	}

	function getHTMLAttribute(idAttribute, nameAttribute, values, oldValue) {
		str1 = '';
		values.forEach((value, index) => {
			str1 += `
			<span class="style__select ${(!oldValue && index==0 || value == oldValue) ? 'active':''}">
				${value}
			</span>`;
		});
		str = `
			<div class="style__detail" id_attribute="${idAttribute}">
				<span class="style__detail__name">${nameAttribute}:</span>
				${str1}
			</div>`;
		return str;
	}

	function renderUIFirstAttribute() {
		let id = Object.keys(attributeValue)[0];
		let values = Object.values(attributeValue)[0];
		attributeSelect[id] = values[0];
		str1 = getHTMLAttribute(id, attributeName[id],values);
		$('.style__select__contain').innerHTML = str1;
	}

	function renderUISecondAttribute(listVariety) {
		let  attributes;
		let id = Object.keys(attributeSelect)[0];
		let value = Object.values(attributeSelect)[0];
		let arrTemp = [];
		for(let variety of listVariety) {
			attributes = variety.listAttribute;
			if(attributes[0].id == id && attributes[0].value == value) {
				for(let i=1; i<attributes.length; i++) {
					if(!arrTemp.includes(attributes[i].value)) {
						arrTemp.push(attributes[i].value);
					}
				}
			}
		}
		
		id = Object.keys(attributeValue)[1];
		value = attributeSelect[id];
		if(!value || !arrTemp.includes(value)) {
			attributeSelect[id] = arrTemp[0];
		}
		else {
			attributeSelect[id] = value;
		}

		str = getHTMLAttribute(id, attributeName[id],arrTemp, value);
		str1 = $('.style__select__contain')
				.querySelector('.style__detail').outerHTML + str;
		$('.style__select__contain').innerHTML = str1;
		//console.log(attributeSelect);
	}

	function updateUIState() {
		if(varietySelect.number > 0) {
			$('.state__value').innerText = 'Còn hàng';
		}
		else {
			$('.state__value').innerText = 'Hết hàng';
		}
	}

	function handleProductManyVariety() {
		//console.log('attribute');
		attributeValue = getAttributeFromListVariety(product.listVariety);
		renderUIFirstAttribute();
		if(Object.keys(attributeName).length > 1) {
			renderUISecondAttribute(product.listVariety);
		}



		//assignEvent
		function activeUIItemStyle(element) {
			getParentBySelector(element, '.style__detail').querySelectorAll('.style__select')
			.forEach(ele => {
				if(ele == element) {
					ele.classList.add('active');
				}
				else {
					ele.classList.remove('active');
				}
			});
		}

		function autoCLickImageElement() {
			let attributeId = Object.keys(attributeSelect)[0];
			let value = attributeSelect[attributeId];
			let num;
			for(let variety of product.listVariety) {
				num = 0;
				for(let key in attributeSelect) {
					for(let attr of variety.listAttribute) {
						if(attr.id == key && attr.value == attributeSelect[key]) {
							num += 1;
							break;
						}
					}
				}
				if(num == Object.keys(attributeSelect).length) {
					varietySelect = variety;
					break;
				}
			}

			updateUIState();
			let input = $('.number__input').value;
			if(!isNumber(input)) {
				updateUIQuantity(1);
			}
			else {
				if(parseInt(input) > varietySelect.number) {
					updateUIQuantity(varietySelect.number);
				}
			}

			console.log('varietyId: ' + varietySelect.id);

			// click Image Element
			$$('.img_other').forEach(img => {
				if(img.getAttribute('varietyId') == varietySelect.id) {
					img.classList.add('active');
					img.click();
				}
				else {
					img.classList.remove('active');
				}
			});
		}

		$('.style__select__contain').addEventListener('click', ({target:element}) => {
			if(element.closest('.style__select')) {
				let styleDetail = getParentBySelector(element, '.style__detail');
				let id = styleDetail.getAttribute('id_attribute');
				attributeSelect[id] = element.innerText;
				activeUIItemStyle(element);
				if(styleDetail.closest('.style__detail:first-child') && Object.keys(attributeName).length > 1) 
				{
					renderUISecondAttribute(product.listVariety);
				}
				//console.log(attributeSelect);
				autoCLickImageElement();
			}
		});

		$('.imgs').addEventListener('click', function({target:element}) {
			if(element.closest('.img_other')) {
				this.querySelectorAll('.img_other').forEach((ele,index) => {
					if(ele == element) {
						ele.classList.add('active');
						$('.img_show').style.backgroundImage = `url(${element.src})`;
						$('.all_text').innerText = `Tất cả hình ảnh (${index + 1}/${numVariety})`;
					}
					else {
						ele.classList.remove('active');
					}
				});
			}
		});	
	}

	function isNumber(input) {
		if(input == '') {
			return false;
		}

		if(!isNaN(input)) {
			if(!input.includes('.')) {
				return true;
			}
		}
		return false;
	}

	function updateUIQuantity(newQuantity) {
		$('.number__input').value = newQuantity;
	}

	$('.number').addEventListener('click', function({target:element}) {
		if(element.closest('.number_item')) {
			const numberElement = getParentBySelector(element, '.number');
			let input = numberElement.querySelector('.number__input').value;
			if(element.classList.contains('sub')) {
				if(isNumber(input) && parseInt(input) > 1) {
					let newQuantity = parseInt(input) - 1;
					updateUIQuantity(newQuantity);
				}
			}
			else if(element.classList.contains('plus')) {
				if(isNumber(input) && parseInt(input) < varietySelect.number) {
					getData(`/api-cart?action=numberItem&id=${varietySelect.id}`, data => {
						let newQuantity = parseInt(input) + 1;
						if(newQuantity <= data) {
							updateUIQuantity(newQuantity);
						}
					});
				}
			}
		}
	});

	$('.number__input').addEventListener('input', ({target:element}) => {
		let input = element.value;
		if(!isNumber(input)) {
			element.value = 1;
		}
		else {
			input = parseInt(input);
			if(input <= 0 || input > varietySelect.number) {
				element.value = varietySelect.number;
			}
		}
	});

	const CookieUtil = {
		'put' : function(key, value) {
			document.cookie = `${key}=${value}`;
		},
		'get' : function(key) {
			let cookies = document.cookie.split('; ');
			for(let cookie of cookies) {
				if(cookie.startsWith(key+'=')) {
					return cookie.substring(key.length + 1, cookie.length);
				}
			}
		},
		'delete' : function(key) {
			const date = new Date();
			date.setTime(date.getTime() - 1000);
			const expires = date.toUTCString();
			document.cookie = `${key}=; expires=${expires}`;
		}
	};

	function addNewCartItem(number) {
		let input = $('.number__input').value;
		if(!isNumber(input)) {
			alert('Số lượng không hợp lệ!');
			return false;
		}

		input = parseInt(input);
		let cart = CookieUtil.get('cart');
		if(!cart) {
			cart = {
				'listCartItem' : []
			};
		}
		else {
			cart = JSON.parse(cart);
		}

		let check = true;
		for(let item of cart.listCartItem) {
			if(item.varietyId == varietySelect.id) {
				if(item.userQuantity + input > number) {
					alert(`Xin lỗi, hệ thống chỉ còn tối đa ${number} sản phẩm!`);
					return false;
				}
				else {
					item.userQuantity += input;
				}
				check = false;
				break;
			}
		}

		if(check) {
			cart.listCartItem.push({
				'varietyId' : varietySelect.id,
				'userQuantity' : input
			});
		}

		$('.cart__number').innerText = cart['listCartItem']
						  			   .reduce((acc, item) => acc + item.userQuantity,0);
		CookieUtil.put('cart', JSON.stringify(cart));
		return true;	
	}


	$('#addtocart').addEventListener('click', (e) => {
		getData(`/api-cart?action=numberItem&id=${varietySelect.id}`, data => {
			if(addNewCartItem(data)) {
				alert('Đã thêm sản phẩm vào giỏ hàng!');
			}
		});
	});

	$('#buynow').addEventListener('click', (e) => {
		console.log('Chyển trang!');
	});

}

