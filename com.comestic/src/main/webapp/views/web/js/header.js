function header(fixedMenuOnScroll = true)
{
	const $ = document.querySelector.bind(document);
	const header = $('.header');
	const overload = $('.header__overload');
	const categoryHeader = $('#lstCategory');
	const headerCategory = $('.header__category');
	const contain = $("#contain");
	const menu = $('.menu');
	const active = 'active';

	$('.header__navbar').addEventListener('click', () => {
		overload.classList.add(active);
		menu.classList.add(active);
	});

	overload.addEventListener('click', () => {
		overload.classList.remove(active);
		menu.classList.remove(active);
	});

	categoryHeader.addEventListener('click', () => {
		headerCategory.classList.toggle(active);
	});
	
	/* get danh muc */
	function getData(path, callback)
	{
		fetch(path)
		.then(response => response.json())
		.then(data => callback(data));
	}
	
	getData('/api-category?action=allCategory', data => {
		let str = '';
		data.listData.forEach(category => {
			str += `<li class="header__category__item">
						<a href="/product?action=allProduct&categoryId=${category.id}">${category.name}</a>
					</li>`;
		});
		$('.header__category').innerHTML = str;
	});

	/* xu ly menu khi scroll */
	if(fixedMenuOnScroll)
	{
		var top, oldTop = 0;
		const breakpoint = header.offsetHeight;
		document.onscroll = function() {
			top = window.scrollY || document.documentElement.scrollTop;
			if(top > breakpoint && oldTop <= breakpoint) {
				contain.style.marginTop = `${header.offsetHeight}px`;
				header.classList.add('short');
			}
			else {
				if(top <= breakpoint && oldTop > breakpoint)
				{
					header.classList.remove('short');
					contain.style.marginTop = '0px';
				}
			}
			oldTop = top;
		}
	}

	// get Number Product Cart
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

	let cart = CookieUtil.get('cart');

	if(!cart) {
		$('.cart__number').innerText = '0';
	}
	else {
		cart = JSON.parse(cart);
		$('.cart__number').innerText = cart['listCartItem']
							  			   .reduce((acc, item) => acc + item.userQuantity,0);
	}

}
