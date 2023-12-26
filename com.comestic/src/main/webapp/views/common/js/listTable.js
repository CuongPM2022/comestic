function listTable(listSelector, callback)
{
	this.contain = document.querySelector(listSelector);
	if(this.contain) 
	{
		this.allAction = this.contain.querySelector('.allAction');
		this.table = this.contain.querySelector('.listTable');
		this.allCheckBoxElement = this.table.querySelector('th input[type="checkbox"]');
		this.listData = [];
		this.temp = null;

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
			this.table.querySelectorAll('tbody tr input[type="checkbox"]').forEach(ele => {
				if(ele.checked)
				{
					this.temp = this.getParentElementBySelector(ele,'tr').dataset.key;
					this.lisData.push(parseInt(this.temp));
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

				this.table.querySelectorAll('tbody tr input[type="checkbox"]')
						  .forEach(ele => ele.checked = isAllChecked);
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
				else if(element.closest('td input[type="checkbox"]')) {
					const checked = element.checked;
					if(checked) {
						this.handleDeleteAllElement('show');
						const numUncheck = this.table.querySelectorAll('td input[type="checkbox"]:not(:checked)').length;
						if(numUncheck == 0) {
							this.allCheckBoxElement.checked = true;
						}
					}
					else {
						this.allCheckBoxElement.checked = false;
						const numChecked = this.table.querySelectorAll('td input[type="checkbox"]:checked').length;
						if(numChecked == 0) {
							this.handleDeleteAllElement('hide');
						}
					}
				}

				if(typeof callback === 'function' && action != '')
				{
					this.temp = parseInt(this.getParentElementBySelector(element,'tr').dataset.key);
					if(action == 'delete') {
						this.temp = [this.temp];
					}
					callback({
						'action' : action,
						'data' : this.temp
					});
				}
			});
		}
	}

}