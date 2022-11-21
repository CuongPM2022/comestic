function Pagination(selector, option = {
	'currentPage' : 1,
	'totalPage' : 2,
	'maxPageItem' : 5
}) 
{
	const $ = document.querySelector.bind(document);
	this.currentPage = option.currentPage;
	this.totalPage = option.totalPage;
	this.maxPageItem = option.maxPageItem;
	this.callback = option.callback;
	this.temp = '';
	this.pagingElement = $(selector);

	//Functions common
	function getHTMLItem(content, isActive = false) {
		return `<li class="listPaging__item ${ isActive ? 'active' : ''}">
					${content}
				</li>`;
	}

	this.showUICurrentIndex = function() {
		this.pagingElement.querySelectorAll('.listPaging__item').forEach(item => {
			if(item.innerText == this.currentPage) {
				item.classList.add('active');
			}
			else {
				item.classList.remove('active');
			}
		});
	}
	
	this.renderUIPaganation = function() {
		if(this.totalPage < 2) {
			this.pagingElement.classList.add('hide');
		}
		else {
			let str = '';
			this.pagingElement.classList.remove('hide');
			str += getHTMLItem('<<');
			str += getHTMLItem(1,this.currentPage == 1);
			if(this.currentPage > 4) {
				str += getHTMLItem('...');
				str += getHTMLItem(this.currentPage -1);
			}
			else {
				for(let i = 2; i < this.currentPage; i++) {
					str += getHTMLItem(i);
				}
			}

			if(this.currentPage != 1 && this.currentPage != this.totalPage) {
				str += getHTMLItem(this.currentPage, true);
			}

			if(this.currentPage < this.totalPage -3) {
				str += getHTMLItem(this.currentPage + 1);
				str += getHTMLItem('...');
			}
			else {
				for(let i = this.currentPage + 1; i < this.totalPage; i++) {
					str += getHTMLItem(i);
				}
			}
			str += getHTMLItem(this.totalPage, this.currentPage == this.totalPage);
			str += getHTMLItem('>>');
			this.pagingElement.innerHTML = str;
			this.showUICurrentIndex();
		}
	}

	this.renderUIPaganation();

	this.pagingElement.addEventListener('click', ({target:element}) => {
		if(element.closest('.listPaging__item')) {
			let content = element.innerText;
			if(content == '<<') {
				if(this.currentPage > 1) {
					this.currentPage --;
					this.renderUIPaganation();
					this.callback(this.currentPage);
				}
			}
			else if(!isNaN(content)) {
				this.currentPage = parseInt(content);
				this.renderUIPaganation();
				this.callback(this.currentPage);
			}
			else if(content == '>>') {
				if(this.currentPage < this.totalPage) {
					this.currentPage ++;
					this.renderUIPaganation();
					this.callback(this.currentPage);
				}
			}
		}
	})
}