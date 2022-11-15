function listTable(listSelector, callback)
{
	this.contain = document.querySelector(listSelector);
	if(this.contain) 
	{
		this.allAction = this.contain.querySelector('.allAction');
		this.table = this.contain.querySelector('.listTable');
		this.allCheckBoxElement = this.table.querySelector('th input[type="checkbox"]');
		this.listCheckboxElement = this.table.querySelectorAll('tbody tr input[type="checkbox"]');
		this.listData = [];

		// Functions Common
		this.getParentElementBySelector = function(element, selector) {
			if(element.matches(selector))
			{
				return element;
			}
			let temp = element;
			do {
				temp = temp.parentElement;
			} while(!temp.matches(selector));
			return temp;
		}

		this.getDataListFromTable = function()
		{
			this.lisData = [];
			[...this.listCheckboxElement].forEach(ele => {
				if(ele.checked)
				{
					this.lisData.push(this.getParentElementBySelector(ele,'tr').dataset.key);
				}
			});
			return this.lisData;
		}

		this.handleDeleteAllElement = function(action)
		{
			if(this.allAction)
			{
				const deleteALlElement = this.allAction.querySelector('.action--delete');
				if(deleteALlElement) 
				{
					if(action === 'show')
					{
						deleteALlElement.classList.add('show');
					}
					else if(action === 'hide')
					{
						deleteALlElement.classList.remove('show');
					}
				}
			}
		}

		//Assign Event
		if(this.allAction)
		{
			this.allAction.addEventListener('click', ({target:element}) => {
				if(element.closest('.action--create'))
				{
					if(typeof callback === 'function')
					{
						callback({
							'action' : 'create'
						});
					}
				}
				else if(element.closest('.action--delete'))
				{
					if(typeof callback === 'function')
					{
						callback({
							'action' : 'delete',
							'data' : this.getDataListFromTable()
						});
					}
				}

			});
		}

		if(this.allCheckBoxElement)
		{
			this.allCheckBoxElement.addEventListener('change', (e) => {
				let isAllChecked = e.target.checked;
				if(isAllChecked) {
					this.handleDeleteAllElement('show');
				}
				else {
					this.handleDeleteAllElement('hide');
				}

				[...this.listCheckboxElement].forEach(ele => ele.checked = isAllChecked);
			});

			[...this.listCheckboxElement].forEach(ele => {
				ele.addEventListener('change', (e) => {
					let isCheked = e.target.checked;
					let numChecked = this.getDataListFromTable().length;
					if(isCheked)
					{
						if(numChecked === this.listCheckboxElement.length)
						{
							this.allCheckBoxElement.checked = true;
						}
						this.handleDeleteAllElement('show');
					}
					else {
						this.allCheckBoxElement.checked = false;
						if(numChecked === 0)
						{
							this.handleDeleteAllElement('hide');
						}
					}
				});
			});

		}

		if(this.table)
		{
			this.table.addEventListener('click', (e) => {
				const element = e.target;
				let action = '';
				if(element.closest('.td__action--edit'))
				{
					action = 'edit';
				}
				else if(element.closest('.td__action--delete'))
				{
					action = 'delete';
				}
				else if(element.closest('.td__action--detail'))
				{
					action = 'detail';
				}

				if(typeof callback === 'function' && action != '')
				{
					callback({
						'action' : action,
						'data' : this.getParentElementBySelector(element,'tr')
								 .dataset.key
					});
				}
			});
		}
	}

}