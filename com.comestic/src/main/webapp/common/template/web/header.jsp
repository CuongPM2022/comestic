<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Show search on scroll: .short -->
<div class="header">
	<div class="grid wide">
		<div class="header__top">
			<div class="header__navbar">
				<i class="fa-solid fa-bars"></i>
			</div>

			<div class="header__logo">
				<span class="logo__image"> <a href="/"> <img
						src="<c:url value='/views/source/image/logo.png'/>">
				</a>
				</span>
			</div>

			<div class="header__search">
				<form class="frmSearch" action="" method="GET">
					<div class="search__input">
						<span class="serch__input__text"> <input type="text"
							name="inputSearch" placeholder="Bạn cần tìm gì...">
						</span> <span class="search__input__icon"> <i
							class="fa-solid fa-magnifying-glass"></i>
						</span>
					</div>

					<div class="search__suggest">
						<span class="suggest__item">Điện thoại</span>
						<span class="suggest__item">Điện tử</span> 
						<span class="suggest__item">Điện lạnh</span> 
						<span class="suggest__item">Gia dụng</span>
					</div>
				</form>
			</div>

			<div class="header__info">
				<div class="info__phone">
					<i class="fa-solid fa-square-phone"></i>
					<div class="info__phone__detail">
						<p class="phone__text">Liên hệ</p>
						<p class="phone__number">0939774515</p>
					</div>
				</div>

				<div class="info__cart">
					<a href="/cart">
						<i class="fa-solid fa-cart-shopping"></i>
						<span class="cart__text">Giỏ hàng</span>
						<span class="cart__number">0</span>
					</a>
				</div>
			</div>
		</div>
	</div>

	<div class="menu">
		<div class="grid wide">
			<ul class="menu__list">
				<li class="menu__item"><a href="/"> <i
						class="fa-solid fa-house"></i> <span class="menu__item_text">Trang
							chủ</span>
				</a></li>

				<li class="menu__item menu__item__category"><a href="#"> <i
						class="fa-regular fa-rectangle-list"></i> <span id="lstCategory"
						class="menu__item_text">Danh mục sản phẩm</span> <i
						class="fa-solid fa-angle-down"></i>
				</a>
					<ul class="header__category">
						
					</ul>
				</li>

				<li class="menu__item"><a href="#"> <i
						class="fa-regular fa-address-book"></i> <span
						class="menu__item_text">Về chúng tôi</span>
				</a></li>

				<li class="menu__item"><a href="#"> <i
						class="fa-solid fa-check-to-slot"></i> <span
						class="menu__item_text">Kiểm tra đơn hàng</span>
				</a></li>

			</ul>
		</div>
	</div>

	<div class="header__overload"></div>
</div>
<!-- end .header -->