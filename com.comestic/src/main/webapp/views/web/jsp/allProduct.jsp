<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>Tất cả sản phẩm</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/views/web/css/allProduct.css'/>">
</head>
<body>
	<div id="contain">
		<div class="filter__row">
			<div class="filter__sort grid wide">
				<!-- Fixed on moble: .active -->
				<div class="filter">
				 	<div class="filter__header">
				 		<i class="fa-solid fa-filter"></i>
				 		<span>Bộ lọc</span>
				 		<span class="icon__mobile" hidden></span>
				 		<span class="filter__apply__number">0</span>
					</div>

					<div class="filter__body">
						<!-- active: .active -->
					 	<div class="filter__category">
					    	<div class="filter__text__body">
					    		<span>Danh mục sản phẩm</span>
					    		<i class="fa-solid fa-caret-down"></i>
					    	</div>
					 		<div class="filter__category__content" data-name="categoryid">
					 			<div class="filter__list">
					 				<!-- List Category -->
						 		</div>
						 		<div class="filter__category__apply">
						 			<button class="filter__apply__exit">Bỏ chọn</button>
						 			<button class="filter__apply__agree">Áp dụng</button>
						 		</div>
					 		</div>
					 	</div>

					 	<div class="filter__category">
					    	<div class="filter__text__body">
					    		<span>Hãng sản xuất</span>
					    		<i class="fa-solid fa-caret-down"></i>
					    	</div>
					 		<div class="filter__category__content" data-name="manufactureid">
					 			<div class="filter__list">
						 			<!-- Menufacture List -->
						 		</div>
						 		<div class="filter__category__apply">
						 			<button class="filter__apply__exit">Bỏ chọn</button>
						 			<button class="filter__apply__agree">Áp dụng</button>
						 		</div>
					 		</div>
					 	</div>

					 	<div class="filter__category">
					    	<div class="filter__text__body">
					    		<span>Giá bán</span>
					    		<i class="fa-solid fa-caret-down"></i>
					    	</div>
					 		<div class="filter__category__content" data-name="price">
					 			<div class="filter__list">
				 					<div class="filter__list__priceSelect">
				 						<span class="filter__list__item" data-min="0" data-max="5000000">
				 							Dưới 5 triệu
				 						</span>
							 			<span class="filter__list__item" data-min="5000000" data-max="9000000">
							 				Từ 5tr - 9tr
							 			</span>
							 			<span class="filter__list__item" data-min="9000000" data-max="12000000">
							 				Từ 9tr - 12tr
							 			</span>
							 			<span class="filter__list__item" data-min="12000000" data-max="inf">
							 				Trên 12 triệu
							 			</span>
				 					</div>
				 					<!-- <div class="filter__list__priceEnter">
				 						<div class="priceEnter__info">
				 							<p class="priceEnter__text">Hoặc chọn giá bạn muốn</p>
				 							<div class="priceEnter__inuput">
				 								<span id="priceEnterMin">2.000.000đ</span>
				 								<input type="range" min="0" max="100">
				 								<span id="priceEnterMax">12.000.000đ</span>
				 							</div>
				 						</div>
				 					</div> -->
						 		</div>
						 		<div class="filter__category__apply">
						 			<button class="filter__apply__exit">Bỏ chọn</button>
						 			<button class="filter__apply__agree">Áp dụng</button>
						 		</div>
					 		</div>
					 	</div>

					 	<div class="filter__category">
					    	<div class="filter__text__body">
					    		<span>Khuyến mãi</span>
					    		<i class="fa-solid fa-caret-down"></i>
					    	</div>
					 		<div class="filter__category__content" data-name="sale">
					 			<div class="filter__list">
						 			<span class="filter__list__item" data-index="0">Đang có khuyến mãi</span>
						 		</div>
						 		<div class="filter__category__apply">
						 			<button class="filter__apply__exit">Bỏ chọn</button>
						 			<button class="filter__apply__agree">Áp dụng</button>
						 		</div>
					 		</div>
					 	</div>

					 	<div class="filter__apply">
					 		<button class="filter__apply__exit all">Bỏ chọn</button>
					 		<button class="filter__apply__agree">Áp dụng</button>
					 	</div>

					 	<div class="filter__close">Đóng</div>
					</div>
					<!-- end .filter__body -->
				</div>
				<!-- active: .active -->
				<div class="sort">
					<div class="sort__header">
						<span class="sort__header__text">Sắp xếp theo</span>
						<i class="fa-solid fa-caret-down"></i>
					</div>
					<div class="sort__list">
						<!-- active: .active -->
						<div class="sort__item active" data-sort="0">
							<i class="fa-solid fa-check"></i>
							<span>Phổ biến nhất</span>
						</div>
						<div class="sort__item" data-sort="1">
							<i class="fa-solid fa-check"></i>
							<span>Giá tăng dần</span>
						</div>
						<div class="sort__item" data-sort="2">
							<i class="fa-solid fa-check"></i>
							<span>Giá giảm dần</span>
						</div>
						<div class="sort__item" data-sort="3">
							<i class="fa-solid fa-check"></i>
							<span>Tỷ lệ giảm</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- end .filter__row -->

		<div class="body">
			<div class="grid wide">
				<p class="product__text__header">Có tất cả 90 sản phẩm</p>
				<div class="productList">
					<div class="row">

					</div>
				</div>
				<!-- hidden: .hidden -->
				<div class="view__more__product">
					<span>Xem thêm 80 sản phẩm</span>
					<i class="fa-solid fa-caret-down"></i>
				</div>
			</div>
		</div>
		<!-- end .body -->

		<div class="overload"></div>
	</div>
	<!-- Javascript -->
	<script type="text/javascript" src="<c:url value='/views/web/js/header.js'/>"></script>
	<script type="text/javascript">
		var categoryid = ${categoryId};
		header(true);
	</script>
	<script type="text/javascript" src="<c:url value='/views/common/js/filterandsorter.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/views/web/js/allProduct.js'/>"></script>
</body>
</html>