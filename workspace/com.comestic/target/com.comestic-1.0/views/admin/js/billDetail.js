(function() {
	const $ = document.querySelector.bind(document);
	const formatNumber = new Intl.NumberFormat();
	let listBillState;
	let temp, temp1;
	let str, str1;
	var stateId;

	function getData(path, callback)
	{
		return fetch(path)
			   .then(response => response.json())
			   .then(data => callback(data));
	}

	function postData(path, data, callback, method = 'POST') {
		return fetch(path, {
					'method' : method,
					'headers' : {
						'Content-Type' : 'application/json'
					},
					'body' : JSON.stringify(data)
				})
				.then(response => response.json())
				.then(data => callback(data));
	}

	//Functions Common
	function getParentBySelector(element, selector)
	{
		if(element.matches(selector)) { return element; }
		var temp = element;
		do {
			temp = temp.parentElement;
		} while(!temp.matches(selector));
		return temp;
	}

	function renderUIListCartItem(listCartItem) {
		str = '';
		listCartItem.forEach((item, index) => {
			temp1 = item.userQuantity * item.price * (1 - item.percentDes/100);
			str += `
					<tr>
                        <td>${index + 1}</td>
                        <td>${item.productCode}</td>
                        <td>${item.productName}</td>
                        <td><img src="${item.image}"></td>
                        <td>${formatNumber.format(item.price)}đ</td>
                        <td>${item.userQuantity}</td>
                        <td>${item.percentDes}%</td>
                        <th>${formatNumber.format(temp1)}đ</th>
                    </tr>`;
		});

		$('.listTable tbody').innerHTML = str;
	}

	function renderUIStateSelect() {
		str = '';
		listBillState.forEach(state => {
			str += `<option ${stateId == state.id ? 'selected' : ''} value="${state.id}">
						${state.name}
					</option>`;
		});
		$('.select__state').innerHTML = str;
	}

	async function renderUIFromDataAPI(data) {
		billId = data.id;
		temp = $('.info__customer');
		temp.querySelector('.fullname').innerText = data.nameCustomer;
		temp.querySelector('.gender').innerText = data.gender;
		temp.querySelector('.phone').innerText = data.phone;
		temp.querySelector('.email').innerText = data.email;
		temp.querySelector('.address').innerText = data.address;
		temp.querySelector('.note').innerText = data.note;
		temp.querySelector('.method').innerText = data.method == 'home' ? 'Tại nhà' : 'Tại cửa hàng';

		temp = $('.bill__info');
		temp.querySelector('.code').innerText = data.code;
		temp.querySelector('.date').innerText = data.strDate;
		temp.querySelector('.totalMoney').innerText = formatNumber.format(data.totalMoney) + 'đ';
		temp1 = data.totalMoney - data.importMoney;
		temp.querySelector('.percentDes').innerText = temp1 == 0 ? 'Không' : `${formatNumber.format(temp1)}đ`;
		temp.querySelector('.importMoney').innerText = formatNumber.format(data.importMoney) + 'đ';

		stateId = data.stateId;
		await getData(`/api-cart?action=listBillState`, states => {
			listBillState = states;
			renderUIStateSelect();
		});
		renderUIListCartItem(data.listCartItem);
	}

	// Call API
	getData(`/api-cart?action=billDetail&billId=${billId}`, data => {
		renderUIFromDataAPI(data);
	});

	// assign Event
	$('.btn__state').addEventListener('click', (e) => {
		temp = $('.select__state');
		temp = temp.options[temp.selectedIndex].value;
		temp = parseInt(temp);
		if(temp == stateId) { return; }
		const bill = {
			'id' : billId,
			'stateId' : temp
		};

		postData(`/api-cart?action=updateBillState`, bill, data => {
			if(!data) {
				alert('Cập nhật không thành công!');
				return;
			}
			else {
				alert('Cập nhật đơn hàng thành công!');
				stateId = temp;
				renderUIStateSelect();
			}
		}, 'PUT');
	});

})();