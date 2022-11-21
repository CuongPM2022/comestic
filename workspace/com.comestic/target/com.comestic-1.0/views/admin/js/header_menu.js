(function headerMenu() {
	const $ = document.querySelector.bind(document);
	const overload = $('.overload');
	const menu = $('.menu');
	const headerAccount = $('.account__menu');
	const active = 'active';

	$('.header__dropmenu__icon').addEventListener('click', (e) => {
		overload.classList.toggle(active);
		menu.classList.toggle(active);
	});

	$('.header__account').addEventListener('click', (e) => {
		headerAccount.classList.toggle(active);
	});

	$('.header__notify__icon').addEventListener('click', (e) => {
		$('.header__notify').classList.toggle('popup');
	});

	$('.header__search__icon').addEventListener('click', (e) => {
		$('.header__search').classList.add(active);
	});

	$('.header__search__close').addEventListener('click', (e) => {
		$('.header__search').classList.remove(active);
	});

	overload.addEventListener('click', () => {
		overload.classList.remove(active);
		menu.classList.remove(active);
	});

})();