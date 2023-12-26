const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);
const formatNumber = new Intl.NumberFormat();

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

function getData(path, callback)
{
	return fetch(path)
		   .then(response => response.json())
		   .then(data => callback(data));
}

function postData(path, data, callback) {
	return fetch(path, {
				'method' : 'POST',
				'headers' : {
					'Content-Type' : 'application/json'
				},
				'body' : JSON.stringify(data)
			})
			.then(response => response.json())
			.then(data => callback(data));
}

(function() {
	var userCart;
	let temp, str, str1;

	let cart = CookieUtil.get('cart');
	if(cart) {
		temp = encodeURIComponent(cart);
		getData(`/api-cart?action=allItem&cart=${temp}`, data => {
			userCart = data;
			handleUserCart(data);
			validateData();
		});
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

	function getHTMLCartItem(item) {
		str1 = `
				<li class="product_item ${item.percentDes != 0 ? 'sale':''}" data-key="${item.varietyId}">
					<img src="${item.image}">
					<div class="item_info">
						<p class="name">${item.productName}</p>
						<p>
							<span class="priceSold">
								${formatNumber.format(Math.round(item.price * (1 - item.percentDes/100)))}đ
							</span>
							<span class="price">${formatNumber.format(item.price)}đ</span>
						</p>
						<p class="cart__item__delete">
							<i class="fa-regular fa-circle-xmark"></i>
							<span class="item__delete__text">Xóa</span>
						</p>
					</div>
					<div class="number">
						<span class="number_item sub">-</span>
                        <span class="number_item value">
                            <input class="number__input" type="text" value="${item.userQuantity}">
                        </span>
                        <span class="number_item plus">+</span>
					</div>
				</li>`;
		return str1;
	}

	function getNumberAndMoney() {
		temp = {
			'totalNumber' : 0,
			'totalMoney' : 0
		};
		userCart.listCartItem.forEach(item => {
			temp.totalNumber += item.userQuantity;
			temp.totalMoney += item.price * (1 - item.percentDes/100) * item.userQuantity;
		});
		return temp;
	}

	function updateUINumberAndMoney(object) {
		$('.cart__total__text').innerText = `Tạm tính (${object.totalNumber} sản phẩm): `;
		$('.cart__total__money').innerText = `${formatNumber.format(object.totalMoney)}đ`;
		$('.totalMoney__number').innerText = `${formatNumber.format(object.totalMoney)}đ`;
		$('.cart__number').innerText = object.totalNumber + '';
	}

	function handleUserCart(data) {
		str = '';
		temp = {'listCartItem' : []};
		data.listCartItem.forEach(item => {
			str += getHTMLCartItem(item);
			temp.listCartItem.push({
				'varietyId' : item.varietyId,
				'userQuantity' : item.userQuantity
			});
		});
		$('.product_list').innerHTML = str;
		CookieUtil.put('cart', JSON.stringify(temp));
		temp = getNumberAndMoney();
		updateUINumberAndMoney(temp);

		// Functions Common
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

		function updateCartQuantity(varietyId, newQuantity) {
			let cart = JSON.parse(CookieUtil.get('cart'));
			let length = cart.listCartItem.length;
			for(let i = 0; i < length; i++) {
				if(cart.listCartItem[i].varietyId == varietyId) {
					cart.listCartItem[i].userQuantity = newQuantity;
					userCart.listCartItem[i].userQuantity = newQuantity;
					break;
				}
			}
			CookieUtil.put('cart', JSON.stringify(cart));
			temp = getNumberAndMoney();
			updateUINumberAndMoney(temp);
		}

		function getMaxQuantity(varietyId) {
			return userCart.listCartItem.find(item => item.varietyId == varietyId).number;
		}

		function updateUIQuantityInput(element, newQuantity) {
			getParentBySelector(element, '.number').querySelector('.number__input')
												   .value = newQuantity;
		}

		function handleChangeQuantity(element) {
			const parentElement = getParentBySelector(element, '.product_item');
			let input = parentElement.querySelector('.number__input').value;
			if(!isNumber(input)) { return; }
			input = parseInt(input);
			let varietyId = parentElement.dataset.key;

			if(element.classList.contains('sub')) {
				if(input > 1) {
					input -= 1;
					updateCartQuantity(varietyId, input);
					updateUIQuantityInput(element, input);
				}
			}
			else if(element.classList.contains('plus')) {
				if(input < getMaxQuantity(varietyId)) {
					input += 1;
					updateCartQuantity(varietyId, input);
					updateUIQuantityInput(element, input);
				}
			}
		}

		function handleDeleteCartItem(element) {
			let parentElement = getParentBySelector(element, '.product_item');
			let varietyId = parentElement.dataset.key;
			temp = JSON.parse(CookieUtil.get('cart'));

			let length = temp.listCartItem.length;
			for(let i = 0; i < length; i++) {
				if(temp.listCartItem[i].varietyId == varietyId) {
					temp.listCartItem.splice(i,1);
					userCart.listCartItem.splice(i,1);
					break;
				}
			}

			if(temp.listCartItem.length > 0) {
				CookieUtil.put('cart', JSON.stringify(temp));
			}
			else {
				CookieUtil.delete('cart');
			}

			temp = getNumberAndMoney();
			updateUINumberAndMoney(temp);
			parentElement.outerHTML = '';
		}


		// Asign Event
		$$('.product_item').forEach(ele => {
			ele.addEventListener('click', ({target:element}) => {
				if(element.closest('.number_item')) {
					handleChangeQuantity(element);
				}
				else if(element.closest('.cart__item__delete')) {
					handleDeleteCartItem(element);
				}
			});
		});
	}

	function validateData() {
		const validate = new Validate('#form', validateAndSubmit);

		function handleValidate(data) {
			str = '';
			temp = { 'listCartItem' : [] };
			data.listCartItem.forEach(item => {
				if(item.userQuantity > item.number) {
					str += ` ${item.number} sản phẩm ${item.productName},`;
					item.userQuantity = item.number;
				}
				temp.listCartItem.push({
					'varietyId' : item.varietyId,
					'userQuantity' : item.userQuantity
				});
			});
			if(str != '') {
				str = 'Xin lỗi, hệ thống chỉ còn:' + str.substring(0, str.length - 1) + '.';
				str += ' Bạn có muốn tiếp tục mua hàng?';
				if(!confirm(str)) { return false; }	
			}
			return temp;
		}

		function handleSubmit(cartData) {

			function checkCartrespon(data) {
				return data.listCartItem.every(cartItem => cartItem.userQuantity > 0);
			}

			postData(`/api-cart?action=addNew`, cartData, data => {
				if(checkCartrespon(data)) {
					alert('Đặt hàng thành công!');
					CookieUtil.delete('cart');
					window.location = 'trang-chu';
				}
			});
		}

		function validateAndSubmit(dataCustomer) {
			temp = CookieUtil.get('cart');
			if(!temp) { return; }
			temp = encodeURIComponent(temp);
			getData(`/api-cart?action=allItem&cart=${temp}`, data => {
				temp = handleValidate(data);
				if(temp) {
					temp = Object.assign(temp, dataCustomer);
					handleSubmit(temp);
				}
			});
		}


	}



})();