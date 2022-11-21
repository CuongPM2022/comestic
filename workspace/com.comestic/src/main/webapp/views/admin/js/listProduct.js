(async function() {
	const $ = document.querySelector.bind(document);
	const formatNumber = new Intl.NumberFormat();
	let str, str1, temp;
	let offset = 0, maxitem = 10, currentPage = 1;
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

	function getNewPage(newPage) {
		if(newPage !== currentPage) {
			offset = (newPage - 1) * maxitem;
			currentPage = newPage;
			getProductListFromAPI(maxitem);
		}
	}

	function renderUI(data) {
		console.log(data);
		// List Product
		str = '';
		data.listData.forEach((product, index) => {
			str += `
					<tr data-key="${product.id}">
                        <td><input type="checkbox" ></td>
                        <td>${offset + index + 1}</td>
                        <td>${product.code}</td>
                        <td>${product.name}</td>
                        <td><img src="${product.image}"></td>
                        <td>${product.manufactureName}</td>
                        <td class="tbCenter">${product.numberVariety}</td>
                        <td class="tbCenter">${product.number}</td>
                        <td>${formatNumber.format(product.price)}đ</td>
                        <td>
                        	${formatNumber.format(product.price * (1 - product.percentDes/100))}đ
                        </td>
                        <td class="tbCenter">
                             <span class="td__action td__action--edit">
                                <i class="fa-regular fa-pen-to-square"></i>
                            </span>

                            <span class="td__action td__action--delete">
                                <i class="fa-solid fa-trash"></i>
                            </span>
                        </td>
                    </tr>`;
		});
		$('.listTable tbody').innerHTML = str;

		// pagination
		temp = Math.ceil(data.totalItem/maxitem);
		new Pagination('.listPaging', {
            'currentPage' : currentPage,
            'totalPage' : temp,
            'maxpageItem' : maxitem,
            'callback' : getNewPage
        });
	}

	async function getProductListFromAPI() {
		paging['offset'] = offset;
		paging['maxItem'] = maxitem;
		temp = encodeURIComponent(JSON.stringify(paging));
		await getData(`/api-product?action=allProduct&paging=${temp}`, renderUI);
		offset += maxitem;
	}

	// Call API
	// Call API get Manufacture
	await getData(`/api-manufacture?action=allItem`, renderUIManufactureList);

	// Call API get Category
	await getData(`/api-category?action=allCategory`, renderUICategoryList);

	filterAndSorter(data => {
		offset = 0;
		currentPage = 1;
		paging = data;
		getProductListFromAPI();
	});

})();