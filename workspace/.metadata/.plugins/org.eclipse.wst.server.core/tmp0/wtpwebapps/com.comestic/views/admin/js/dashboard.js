(function() {
	const $ = document.querySelector.bind(document);
	const formatNumber = new Intl.NumberFormat();
	const active = 'active';
	let temp;
	let str, str1;

	//Functions Common

	function handleClass(element, action, value = active) {
		if(action == 'add') {
			element.classList.add(value);
		}
		else if(action == 'remove') {
			element.classList.remove(value);
		}
		else if(action == 'toggle') {
			element.classList.toggle(value);
		}
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

	function setDateTime() {
		let date = new Date;
		date = date.toISOString().split('T')[0];
		const minDay = $('.minDay');
		const maxDay = $('.maxDay');
		minDay.value = date;
		minDay.max = date;
		maxDay.value = date;
		maxDay.max = date;
	}
	setDateTime();

	const  handleClickSortElement = {
		'parentElement' : null,
		'sortKey' : null,
		'minDay' : null,
		'maxDay' : null,
		'setup' : function() {
			const date = new Date();
			temp = date.toISOString().split('T')[0];
			//console.log(temp = date.toISOString());
			this.minDay = temp;
			this.maxDay = temp;
		},
		'actiUIItemSort' : function() {
			getParentBySelector(this.parentElement,'.sort__list')
			.querySelectorAll('.sort__item').forEach(ele => {
				if(ele == this.parentElement) {
					handleClass(ele,'add');
				}
				else {
					handleClass(ele,'remove');
				}
			});
		},
		'handleEvent' : function(element) {
			this.parentElement = getParentBySelector(element,'.sort__item');
			this.sortKey = this.parentElement.dataset.sort;
			this.actiUIItemSort();
			if(this.parentElement.classList.contains('other')) {
				handleClass($('.filter__select'),'add','show');
			}
			else {
				var date = new Date();
				this.maxDay = date.toISOString().split('T')[0];
				if(this.sortKey == 'day') {
					this.minDay = this.maxDay;
				}
				else if(this.sortKey == 'week') {
					const days = date.getDay();
					if(days == 0) {
						date.setDate(date.getDate() - 6);
					}
					else {
						date.setDate(date.getDate() - (days - 1));
					}
					this.minDay = date.toISOString().split('T')[0];
				}
				else if(this.sortKey == 'month') {
					date.setDate(1);
					this.minDay = date.toISOString().split('T')[0];
				}
				getStatistic();
			}
		},
		'handleClickSelectDay' : function(element) {
			this.minDay = element.querySelector('.minDay').value;
			this.maxDay = element.querySelector('.maxDay').value;
			if(this.minDay > this.maxDay) {
				alert('Ngày không hợp lệ!');
				return;
			}
			getStatistic();
			handleClass($('.filter__select'),'remove','show');
		}
	};
	handleClickSortElement.setup();

	//Call API
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

	function renderUITableElement(data) {
		if(data) {
			str = '';
			data.forEach((item, index) => {
				str += `
					<tr>
                        <td>${index + 1}</td>
                        <td>${item.code}</td>
                        <td>${item.nameCustomer}</td>
                        <td>${formatNumber.format(item.importMoney)}đ</td>
                        <td>${item.strDate}</td>
                        <td class="tbCenter">
                        	<a href="admin-bill?action=billDetail&billId=${item.id}">
                             	<span class="td__action td__action--info">
                                <i class="fa-solid fa-circle-info"></i>
                            	</span>
                            </a>
                        </td>
                    </tr>`;
			});
			$('.listTable tbody').innerHTML = str;
		}
	}

	function renderUIStatistic(data) {
	    temp = $('.statistic__list');
	    temp.querySelector('.newbill .statistic__body__number').innerText = data.newBill;
	    temp.querySelector('.totalbill .statistic__body__number').innerText = data.totalBill;
	    temp.querySelector('.total_statistic .statistic__body__number')
	    	.innerText = formatNumber.format(data.totalMoney) + 'đ';
	    temp.querySelector('.deletebill .statistic__body__number').innerText = data.cancelBill;

	    temp = $('.statistic.access');
	    temp.querySelector('.total_access .statistic__body__number').innerText = data.totalAccess || 0;
	}

	// Call API get new bill
	let paging = {
		'sortName' : 'date',
		'sortBy' : 'desc',
		'filter' : {
			'stateid' : 1
		}
	};
	paging = encodeURIComponent(JSON.stringify(paging));
	getData(`/api-cart?action=listCart&paging=${paging}`, data => {
		renderUITableElement(data);
	});

	// Call API get Statistic
	function getStatistic() {
		paging = {
			'filter' : {
				'minday' : handleClickSortElement.minDay,
				'maxday' : handleClickSortElement.maxDay
			}
		};
		temp = encodeURIComponent(JSON.stringify(paging));
	    getData(`/api-statistic?paging=${temp}`, data => {
		    renderUIStatistic(data);
	    });
	}
	
	getStatistic();

	// Assign Event
	$('.sort').addEventListener('click', function({target:element}) {
		handleClass(this,'toggle');
		if(element.closest('.sort__item')) {
			handleClickSortElement.handleEvent(element);
		}
	});

	$('.select__day').addEventListener('click', function() {
		const element = $('.filter__select');
		handleClass(element,'remove','show');
		handleClickSortElement.handleClickSelectDay(element);
	});
	

})();