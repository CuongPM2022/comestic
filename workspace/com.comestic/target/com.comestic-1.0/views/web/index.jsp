<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html lang="vi">
<head>
<title>Trang chủ</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/web/css/home.css'/>">
</head>
<body>
	<div id="contain">

		<div class="slider">
			<div class="grid wide">
				<div class="row">
					<div class="col p-3 t-0 m-0">
						<div class="slider__category">
							<p class="slider__text">Danh mục sản phẩm</p>
							<ul class="slider__category__list">

							</ul>
						</div>
						<!-- end .slider__category -->
					</div>

					<div class="col p-9 t-12 m-12">
						<div class="slider__slideShow">
							<ul class="slide__list"></ul>

							<div class="slide__dot"></div>
						</div>
						<!-- end .slider__slideShow -->
					</div>

				</div>
			</div>
		</div>
		<!-- end .slider -->
		<div class="body">
			<div class="grid wide">
				<div class="row">
					<div class="col p-12 t-12 m-12">
						<div class="saleProduct">
							<p class="saleProduct__text groupt__text__header">Sản phẩm
								nổi bật</p>
							<div class="listProduct">
								<div class="row">

								</div>
							</div>
							<!-- end .listProduct -->
						</div>
						<!-- end .saleProduct -->
					</div>

					<div class="col p-3 t-12 m-12">
						<div class="hotProductList new">
							<div class="hotCategory newProduct">
								<p class="hotCategory__text groupt__text__header">Sản phẩm mới</p>
								<div class="row">

								</div>
							</div>
						</div>

						<div class="hotProductList sell">
							<div class="hotCategory">
								<p class="hotCategory__text groupt__text__header">Bán chạy
									nhất</p>
								<div class="row">

								</div>
							</div>
						</div>
					</div>

					<div class="col p-9 t-12 m-12">
						<div class="allProduct">
							<p class="allProduct__text  groupt__text__header">Tất cả sản
								phẩm</p>
							<div class="row">

							</div>
							<!-- Show .show -->
							<div class="view__more__product">
								<span>Xem thêm sản phẩm</span> <i
									class="fa-solid fa-caret-down"></i>
							</div>
						</div>
						<!-- end .allProduct -->
					</div>
				</div>
			</div>
		</div>
		<!-- end .body -->
	</div>
	<!-- Javascript -->
	<script type="text/javascript"
		src="<c:url value='/views/web/js/header.js'/>"></script>
	<script type="text/javascript"
		src="<c:url value='/views/web/js/home.js'/>"></script>
	<script type="text/javascript">
		header();
	</script>
</body>
</html>