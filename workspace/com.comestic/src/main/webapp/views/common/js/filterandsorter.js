function filterAndSorter(callback) {

	const filter = document.querySelector('.filter');
	const overload = document.querySelector('.overload');
	const filterNumber = filter.querySelector('.filter__apply__number');
	const active = 'active';
	const sort = document.querySelector('.sort');

	// Variable
	var listData = {};
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
		var sort_temp = listData['sort'];
		listData = {};
		listData['sort'] = sort_temp;
		for(var key in listElement)
		{
			if(key == 'price')
			{
				listData[key] = [];
				listData[key][0] = listElement[key].dataset.min;
				listData[key][1] = listElement[key].dataset.max;
			}
			else {
				listData[key] = listElement[key].dataset.index;
			}
		}
	}

	function updateNumberFilter() { filterNumber.innerText = (Object.keys(listData).length - 1); }

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
		if(typeof callback === 'function')
		{
			callback(listData);
		}
	}

	function handleEventClickExit() 
	{
		for(var key in listElementTemp) { deActiveFilterItemValue(listElementTemp[key]); }
		for(var key in listElement) { activeFilterItemValue(listElement[key]); }
		closePopupElement();
	}

	function deleteOneCategoryFilter(categoryFilter) {
		delete listElement[categoryFilter.dataset.name + ""];
		delete listData[categoryFilter.dataset.name + ""];
		[...categoryFilter.querySelectorAll('.filter__list__item')].forEach(ele => deActiveFilterItemValue(ele));
		console.log(listData);
	}

	function deleteAllCategoryFilter()
	{
		[...filter.querySelectorAll('.filter__category__content')].forEach(ele => deleteOneCategoryFilter(ele));
	}

	// Event CLick Open popupElement
	[...filter.querySelectorAll('.filter__text__body')].forEach(ele => {
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
	[...filter.querySelectorAll('.filter__apply__exit:not(.all)')].forEach(ele => {
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
	[...filter.querySelectorAll('.filter__list__item')].forEach(ele => {
		ele.addEventListener('click', function() {
			var temp = getParentBySelector(this,'.filter__category__content');
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
	[...filter.querySelectorAll('.filter__apply__agree')].forEach(ele => {
		ele.addEventListener('click', (e) => {
			handleEventClickApply();
		});
	});

	/***** Sort *******/
	var selectElement = sort.querySelector('.sort__item');
	listData['sort'] = selectElement.dataset.sort;
	sort.querySelector('.sort__header').addEventListener('click', (e) => {
		handleClass(sort,'toggle',active);
	});

	[...sort.querySelectorAll('.sort__item')].forEach(ele => {
		ele.addEventListener('click', function() {
			if(selectElement)
			{
				handleClass(selectElement,'remove',active);
			}
			listData['sort'] = this.dataset.sort;
			selectElement = this;
			handleClass(selectElement,'add',active);
			if(typeof callback === 'function')
			{
				callback(listData);
			}
		});
	});
};
