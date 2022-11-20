function filterAndSorter(callback) {

	const filter = document.querySelector('.filter');
	const overload = document.querySelector('.overload');
	const filterNumber = filter.querySelector('.filter__apply__number');
	const active = 'active';
	const sort = document.querySelector('.sort');

	// Variable
	var listData = {};
	var sortData = {
		'sortName' : 'view',
		'sortBy' : 'desc'
	};
	var offset = 0;
	const maxItem = 12;
	var listElement = {};
	var listElementTemp = {};
	var popupElement;
	var temp;

	// Functions Common
	function handleClass(element, action, value) 
	{
		if(action == 'add') { element.classList.add(value); }
		else if(action == 'remove') { element.classList.remove(value); }
		else if(action == 'toggle') { element.classList.toggle(value); }
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

	function showPopupElement()
	{
		handleClass(overload,'add',active);
		handleClass(popupElement,'add',active);
	}

	function closePopupElement()
	{
		if(popupElement)
		{
			listElementTemp = {};
			handleClass(popupElement,'remove',active);
			handleClass(overload,'remove',active);
			popupElement = null;
		}
	}

	function activeFilterItemValue(element) { handleClass(element, 'add', active); }

	function deActiveFilterItemValue(element) { handleClass(element, 'remove', active); }

	function isActiveFilterItemValue(element) { return element.classList.contains(active); }

	function getListDataFromListElement()
	{
		listData = {};
		for(var key in listElement)
		{
			if(key == 'price')
			{
				temp = listElement[key].dataset.min;
				if(temp != '0') {
					listData['pricemin'] = parseInt(temp);
				}

				temp = listElement[key].dataset.max;
				if(temp != 'inf') {
					listData['pricemax'] = parseInt(temp);
				}
			}
			else {
				listData[key] = listElement[key].dataset.index;
			}
		}
	}

	function updateNumberFilter() { filterNumber.innerText = (Object.keys(listData).length); }

	function invoke() {
		if(typeof callback === 'function')
		{
			temp = Object.assign({'filter' : listData}, sortData);
			callback(temp);
		}
	}

	function handleEventClickApply()
	{
		var filterItemValue;
		for(var key in listElementTemp)
		{
			filterItemValue = listElementTemp[key];
			if(isActiveFilterItemValue(filterItemValue))
			{
				listElement[key] = filterItemValue;
			}
			else {
				delete listElement[key+""];
			}
		}	
		getListDataFromListElement();
		updateNumberFilter();
		closePopupElement();
		invoke();
	}

	function handleEventClickExit() 
	{
		for(var key in listElementTemp) { deActiveFilterItemValue(listElementTemp[key]); }
		for(var key in listElement) { activeFilterItemValue(listElement[key]); }
		closePopupElement();
	}

	function deleteOneCategoryFilter(categoryFilter) {
		temp = categoryFilter.dataset.name;
		if(temp == 'price') {
			delete listElement['price'];
			delete listData['pricemin'];
			delete listData['pricemax'];
		}
		else {
			delete listElement[temp + ""];
			delete listData[temp + ""];
		}

		categoryFilter.querySelectorAll('.filter__list__item').forEach(ele => deActiveFilterItemValue(ele));
		invoke();
	}

	function deleteAllCategoryFilter()
	{
		filter.querySelectorAll('.filter__category__content').forEach(ele => deleteOneCategoryFilter(ele));
	}

	// Event CLick Open popupElement
	filter.querySelectorAll('.filter__text__body').forEach(ele => {
		ele.addEventListener('click', (e) => {
			handleEventClickExit();
			popupElement = getParentBySelector(e.target,'.filter__category');
			showPopupElement();
		});
	});

	filter.querySelector('.icon__mobile').addEventListener('click', (e) => {
		handleClass(filter,'add',active);
		popupElement = filter;
	});

	//Event Exit popupElement
	overload.addEventListener('click', () => {
		if(popupElement) { handleEventClickExit(); }
	});

	filter.querySelector('.filter__close').addEventListener('click', () => handleEventClickExit());

	//Event Delete All ItemFilterValue
	filter.querySelectorAll('.filter__apply__exit:not(.all)').forEach(ele => {
		ele.addEventListener('click', (e) => {
			deleteOneCategoryFilter(getParentBySelector(e.target,'.filter__category__content'));
			updateNumberFilter();
			closePopupElement();
		});
	});

	filter.querySelector('.filter__apply__exit.all').addEventListener('click', () => {
		deleteAllCategoryFilter();
		updateNumberFilter();
		closePopupElement();
	});

	// Handle Click FilterElementValue
	filter.querySelectorAll('.filter__list__item').forEach(ele => {
		ele.addEventListener('click', function() {
			let temp = getParentBySelector(this,'.filter__category__content');
			listElementTemp[temp.dataset.name] = this;
			if(isActiveFilterItemValue(this)) 
			{
				deActiveFilterItemValue(this);
				return;
			}
			//Deactive all value other
			[...temp.querySelectorAll('.filter__list__item')].forEach(ele => deActiveFilterItemValue(ele));
			activeFilterItemValue(this);
		});
	});

	// Handle Click Apply
	filter.querySelectorAll('.filter__apply__agree').forEach(ele => {
		ele.addEventListener('click', (e) => {
			handleEventClickApply();
		});
	});

	// Setup categoryId When Load Page
	if(categoryid != null) {
		temp = filter.querySelector('[data-name="categoryid"]');
		[...temp.querySelectorAll('.filter__list__item')].find(ele => ele.dataset.index == categoryid).click();
		temp.querySelector('.filter__apply__agree').click();
	}
	else {
		invoke();
	}

	/***** Sort *******/
	var selectElement = sort.querySelector('.sort__item');
	sort.querySelector('.sort__header').addEventListener('click', (e) => {
		handleClass(sort,'toggle',active);
	});

	sort.querySelectorAll('.sort__item').forEach(ele => {
		ele.addEventListener('click', function() {
			if(selectElement)
			{
				handleClass(selectElement,'remove',active);
			}
			switch(this.dataset.sort) {
				case '0' : 
					sortData['sortName'] = 'view';
					sortData['sortBy'] = 'desc';
					break;
				case '1' : 
					sortData['sortName'] = 'price';
					sortData['sortBy'] = 'asc';
					break;
				case '2':
					sortData['sortName'] = 'price';
					sortData['sortBy'] = 'desc';
					break;
				case '3':
					sortData['sortName'] = 'percentdes';
					sortData['sortBy'] = 'desc';
			}

			selectElement = this;
			handleClass(selectElement,'add',active);
			invoke();
			handleClass(sort,'toggle',active);
		});
	});

};
