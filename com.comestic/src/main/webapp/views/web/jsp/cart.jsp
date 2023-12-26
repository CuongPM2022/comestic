<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Giỏ hàng</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='views/web/css/cart.css'/>">
</head>
<body>
	<div id="contain">
		<div class="body">
			<div class="grid wide">
				<div class="row center">
					<div class="col p-6 t-12 m12">
						<div class="cart__contain">
								<div class="body_text">
									<a href="#" class="body_text_shopping">Tiếp tục mua sắm</a>
									<span class="cart_detail_text">Chi tiết giỏ hàng</span>
								</div>

								<div class="cart_detail" id="form">
									<ul class="product_list"> 
										<!-- Min quantity: .min, Max quantity: .max, Sale: .sale -->
									</ul>

									<div class="cart_total">
										<span class="cart__total__text">Tạm tính (3 sản phẩm): </span>
										<span class="cart__total__money">2.600.000đ</span>
									</div>

									<div class="cart_customer">
										<span>Thông tin khách hàng</span>
										<div class="row">
											<div class="col p-12 t-12 m-12">
												<div class="formGroupt" id="gender">
													<input checked type="radio" name="gender" rules="" value="male">Nam 
													<span class="blank"></span>
													<input type="radio" name="gender" value="female">Nữ
												</div>
											</div>

											<div class="col p-6 t-6 m-12">
												<div class="formGroupt" id="name">
													<input type="text" id="name" name="nameCustomer" placeholder="Họ và tên"
														   rules="fullname">
													<!-- Show: .show -->
													<span class="form-message">Trường này là bắt buộc!</span>
												</div>
											</div>

											<div class="col p-6 t-6 m-12">
												<div class="formGroupt" id="phone">
													<input type="text" id="phone" name="phone" rules="number" 
														   placeholder="Số điện thoại">
													<span class="form-message">Trường này là bắt buộc!</span>
												</div>
											</div>

											<div class="col p-12 t-12 m-12">
												<div class="formGroupt" id="email">
													<input type="text" id="email" name="email" rules="email" 
														   placeholder="Email">
													<span class="form-message">Trường này là bắt buộc!</span>
												</div>
											</div>

											<div class="col p-12 t-12 m-12">
												<div class="formGroupt" id="address">
													<input type="text" id="address" name="address" placeholder="Địa chỉ nhận hàng"
														   rules="require|lmin=12|lmax=100">
													<span class="form-message">Trường này là bắt buộc!</span>
												</div>

												<div class="formGroupt" id="method">
													<p>Chọn cách thức nhận hàng</p>
													<input checked type="radio" name="method" rules="" value="home">Giao tận nơi
													<span class="blank"></span>
													<input type="radio" name="method" value="store">Nhận tại cửa hàng
												</div>

												<div class="formGroupt" id="cart_note">
													<input type="text" name="note" rules="" placeholder="Ghi chú (Không bắt buộc)">
												</div> 
											</div>
										</div>
									</div>

									<div class="cart_order">
										<div class="cart_order_text">
											<span class="text">Tổng tiền:</span>
											<span class="totalMoney__number">2.600.000đ</span>
										</div>

										<div class="btnOrder form-submit">
											Đặt hàng
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
		</div>

		<div id="footer" class="grid wide">
			Cảm ơn bạn đã sử dụng dịch vụ của chúng tôi!
		</div>
	</div>
	<!-- Javascript -->
	<script type="text/javascript" src="<c:url value='views/web/js/header.js'/>"></script>
	<script type="text/javascript" src="<c:url value='views/common/js/validate.js'/>"></script>
	<script type="text/javascript" src="<c:url value='views/web/js/cart.js'/>"></script>
	<script type="text/javascript">
		header();
	</script>
</body>
</html>