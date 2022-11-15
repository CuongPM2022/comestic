<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Chi tiết sản phẩm</title>
	<link rel="stylesheet" type="text/css" href="<c:url value='/views/web/css/productDetail.css'/>">
</head>
<body>
 	<div class="body" id="contain">
        	<div class="grid wide">
        		<div class="directory">
					<span class="directory_text category"><!-- Điện thoại --></span>
					<span class="directory_icon">
						<i class="fa-solid fa-chevron-right"></i>
					</span>
					<span class="directory_text product"><!-- Điện thoại Redmi --></span>
				</div>

				<div class="productInfo">
					<div class="row">
                        <div class="col p-12 t-12 m-12">
                            <div class="product__intro">
                                <span class="name"><!-- Điện thoại Redmi XYZ 123 --></span>
                                <span class="stars">

                                </span>
                                <span class="num_review"><!-- 20 đánh giá --></span>
                            </div>
                        </div>

						<div class="col p-7 t-12 m-12">
							<div class="images">
                                <div class="img_show" 
                                     style="background-image: url();">
                                </div>
                                <div class="all_text">
                                    <!-- Tất cả hình ảnh (1/3) -->
                                </div>
                                <div class="imgs">
                                    <!-- active: .active -->
                                    <!-- <img class="img_other active" src="<c:url value='/views/source/image/product/pd04.png'/>">
                                    <img class="img_other" src="<c:url value='/views/source/image/product/pd03.png'/>">
                                    <img class="img_other" src="<c:url value='/views/source/image/product/dt02.jpg'/>"> -->
                                </div>
                            </div>
						</div>

						<div class="col p-5 t-12 m-12">
							<div class="info">
								<div class="styles">
                                    <div class="style__select__contain">
    									
                                    </div>

									<div class="price">
										<span class="price__text">Giá: </span>
										<span class="price__num"><!-- 2.600.000đ --></span>
									</div>

                                    <div class="state">
                                        <span class="state__text">Trạng thái: </span>
                                        <span class="state__value">Còn hàng</span>
                                    </div>

                                    <!-- Hide: .hide -->
									<div class="info__detail">
										<p class="info__detail__text">Thông tin chi tiết</p>
										<div class="info__detail__describe">
											<p>RAM: 2GB</p>
											<p>Bộ nhớ: 16GB</p>
											<p>Pin: 20000 mA</p>
											<p>Hãng sản xuất: Samsung</p>
										</div>
									</div>

                                    <div class="number">
                                        <p class="number__text">Số lượng: </p>
                                        <span class="number_item sub">-</span>
                                        <span class="number_item value">
                                            <input class="number__input" type="text" value="1">
                                        </span>
                                        <span class="number_item plus">+</span>
                                    </div>

									<div class="row">
                                        <div class="col p-6 t-6 m-12"> 
                                            <button id="addtocart">Thêm vào giỏ hàng</button>
                                        </div>
                                        <div class="col p-6 t-6 m-12">
                                            <button id="buynow">Mua sản phẩm ngay</button>
                                        </div>
									</div>
									
								</div>
							</div>
						</div>
					</div>
				</div>

                <!-- Popup: .popup, No information: .hide -->
                <div class="describe__contain">
                    <div class="row">
                        <div class="col p-7 t-12 m-12">
                            <div class="describe__detail">
                                <div class="describe__detail__header">Thông tin sản phẩm</div>
                                <div class="describe__detail__content">
                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

                                    <img src="<c:url value='/views/source/image/product/dt02.jpg'/>" style="width:100%">

                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

                                    <img src="<c:url value='/views/source/image/product/dt01.jpg'/>" style="width:100%">

                                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                                    tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                                    quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                                    consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                                    cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                                    proident, sunt in culpa qui officia deserunt mollit anim id est laborum.</p>

                                </div>
                                <div class="describe__detail__viewmore hide">
                                    <button>
                                        <span>Xem thêm</span>
                                        <i class="fa-solid fa-caret-down"></i>
                                    </button>
                                </div>

                                <div class="describe__detail__close">Đóng</div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col p-7 t-12 m-12">
                        <!-- no review: .no-review -->
                        <div class="review__detail">
                            <p class="review__detail__header">Đánh giá điện thoại Nokia A1202</p>
                            <div class="review__detail__stardetail">
                                <div class="star__total">
                                    <span class="star__total__number">3.7</span>
                                    <span class="star__total__icon">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </span>
                                    <span class="star__total__review">48 đánh giá</span>
                                </div>
                                <div class="star__number__list">
                                    <div class="star__number__item">
                                        <div class="star__number__quantity">
                                            <span>5</span>
                                            <i class="fa-solid fa-star"></i>
                                        </div>

                                        <div class="star__number__range">
                                            <div style="--percent:40%;"></div>
                                        </div>

                                        <div class="star__number__percent">40%</div>
                                    </div>

                                    <div class="star__number__item">
                                        <div class="star__number__quantity">
                                            <span>4</span>
                                            <i class="fa-solid fa-star"></i>
                                        </div>

                                        <div class="star__number__range">
                                            <div style="--percent:30%;"></div>
                                        </div>

                                        <div class="star__number__percent">30%</div>
                                    </div>

                                    <div class="star__number__item">
                                        <div class="star__number__quantity">
                                            <span>3</span>
                                            <i class="fa-solid fa-star"></i>
                                        </div>

                                        <div class="star__number__range">
                                            <div style="--percent:20%;"></div>
                                        </div>

                                        <div class="star__number__percent">20%</div>
                                    </div>

                                    <div class="star__number__item">
                                        <div class="star__number__quantity">
                                            <span>2</span>
                                            <i class="fa-solid fa-star"></i>
                                        </div>

                                        <div class="star__number__range">
                                            <div style="--percent:10%;"></div>
                                        </div>

                                        <div class="star__number__percent">10%</div>
                                    </div>

                                    <div class="star__number__item">
                                        <div class="star__number__quantity">
                                            <span>1</span>
                                            <i class="fa-solid fa-star"></i>
                                        </div>

                                        <div class="star__number__range">
                                            <div style="--percent:0%;"></div>
                                        </div>

                                        <div class="star__number__percent">0%</div>
                                    </div>

                                </div>
                            </div>
                            <div class="review__detail__list">
                                <!-- Max Item: 2 -->
                                <div class="review__detail__item">
                                    <span class="review__item__name">Võ Nguyên Giáp</span>
                                    <div class="review__item__starnum">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>
                                    <div class="review__item__content">Sản phẩm khá là tốt, sẽ giới thiệu cho nhiều người!</div>
                                    <div class="review__item__time">7 phút trước</div>
                                </div>

                                <div class="review__detail__item">
                                    <span class="review__item__name">Sống ảo</span>
                                    <div class="review__item__starnum">
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>
                                    <div class="review__item__content">Sản phẩm khá là tốt, sẽ giới thiệu cho nhiều người!</div>
                                    <div class="review__item__time">7 phút trước</div>
                                </div>

                                <div class="review__detail__item">
                                    <span class="review__item__name">Nguyễn Văn Anh</span>
                                    <div class="review__item__starnum">
                                       <i class="fa-solid fa-star"></i>
                                        <i class="fa-solid fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                        <i class="fa-regular fa-star"></i>
                                    </div>
                                    <div class="review__item__content">Sản phẩm khá là tốt, sẽ giới thiệu cho nhiều người!</div>
                                    <div class="review__item__time">7 phút trước</div>
                                </div>
                            </div>
                            <div class="review__detail__no-review">Hiện không có đánh giá cho sản phẩm!</div>
                            <div class="review__detail__option">
                                <div class="row">
                                    <div class="col p-6 t-6 m-12">
                                        <div class="review__writenew">Viết đánh giá</div>
                                    </div>
                                    <div class="col p-6 t-6 m-12">
                                        <!-- hide: .hide -->
                                        <div class="review__detail__viewmore">
                                            <span>Xem thêm đánh 20 giá</span>
                                            <i class="fa-solid fa-caret-down"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Display popup: .popup -->
                        <div class="write__review__contain">
                            <div class="grid wide">
                                <div class="row">
                                    <div class="col p-5 t-8 m-12">
                                        <div class="write__review">
                                            <div class="write__review__header">
                                                <span class="write__review__text">Đánh giá</span>
                                                <span class="write__review__close">
                                                    <i class="fa-solid fa-xmark"></i>
                                                </span>
                                            </div>

                                            <div class="write__review__body">
                                                <div class="write__review__star">
                                                    <!-- Select .select, UserClick: .userselect -->
                                                    <div class="write__review__starselect select">
                                                        <span class="review__star__select">
                                                            <i class="fa-solid fa-star"></i>
                                                        </span>
                                                        <span class="review__star__unselect">
                                                            <i class="fa-regular fa-star"></i>
                                                        </span>
                                                        <span class="review__star__text">Rất tệ</span>
                                                    </div>

                                                    <div class="write__review__starselect select">
                                                        <span class="review__star__select">
                                                            <i class="fa-solid fa-star"></i>
                                                        </span>
                                                        <span class="review__star__unselect">
                                                            <i class="fa-regular fa-star"></i>
                                                        </span>
                                                        <span class="review__star__text">Tệ</span>
                                                    </div>

                                                    <div class="write__review__starselect select userselect">
                                                        <span class="review__star__select">
                                                            <i class="fa-solid fa-star"></i>
                                                        </span>
                                                        <span class="review__star__unselect">
                                                            <i class="fa-regular fa-star"></i>
                                                        </span>
                                                        <span class="review__star__text">Bình thường</span>
                                                    </div>

                                                    <div class="write__review__starselect">
                                                        <span class="review__star__select">
                                                            <i class="fa-solid fa-star"></i>
                                                        </span>
                                                        <span class="review__star__unselect">
                                                            <i class="fa-regular fa-star"></i>
                                                        </span>
                                                        <span class="review__star__text">Tốt</span>
                                                    </div>

                                                    <div class="write__review__starselect">
                                                        <span class="review__star__select">
                                                            <i class="fa-solid fa-star"></i>
                                                        </span>
                                                        <span class="review__star__unselect">
                                                            <i class="fa-regular fa-star"></i>
                                                        </span>
                                                        <span class="review__star__text">Rất tốt</span>
                                                    </div>

                                                </div>

                                                <div class="write__review__content">
                                                    <div class="formGroupt" id="write__review__text">
                                                        <textarea placeholder="Mời bạn để lại đánh giá..."></textarea>
                                                        <span class="form-message">Trường này không thể bỏ trống!</span>
                                                    </div>

                                                    <div class="formGroupt">
                                                        <input type="text" placeholder="Họ và tên">
                                                        <span class="form-message">Trường này không thể bỏ trống!</span>
                                                    </div>

                                                    <div class="formGroupt">
                                                        <input type="text" placeholder="Email">
                                                        <span class="form-message">Trường này không thể bỏ trống!</span>
                                                    </div>

                                                    <div class="formGroupt">
                                                        <input type="number" placeholder="Số điện thoại">
                                                        <span class="form-message">Trường này không thể bỏ trống!</span>
                                                    </div>

                                                </div>
                                                
                                            </div>

                                            <div class="write__review__send">Gửi đánh giá</div>
                                        </div>
                                    </div>
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Product relative -->
				<div class="product_relative">
					<div class="product_relative_text">
						Sản phẩm có liên quan
					</div>
					<div class="row">
                        <div class="col p-2-4 t-4 m-6">
                            <div class="product__item sale">
                                <div class="product__image" 
                                     style="background-image: url(<c:url value='/views/source/image/product/pd04.png'/>);">
                                </div>
                                <div class="product__info">
                                    <span class="product__supply">Samsung</span>
                                    <div class="product__name">Điện thoại Samsung A2 104</div>
                                    <div class="product__price">
                                        <span class="price__sold">2.000.000đ</span>
                                        <span class="percent">Giảm 20%</span>
                                    </div>
                                    <div class="product__review">
                                        <span class="product__star__num">4.6</span>
                                        <span class="product__star__icon">
                                            <i class="fa-solid fa-star"></i>
                                        </span>
                                        <span class="product__number__comment">(22)</span>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col p-2-4 t-4 m-6">
                                        <div class="product__item sale">
                                            <div class="product__image" 
                                                 style="background-image: url(<c:url value='/views/source/image/product/pd10.png'/>);">
                                            </div>
                                            <div class="product__info">
                                                <span class="product__supply">Samsung</span>
                                                <div class="product__name">Điện thoại Samsung A2 104</div>
                                                <div class="product__price">
                                                    <span class="price__sold">2.000.000đ</span>
                                                    <span class="percent">Giảm 20%</span>
                                                </div>
                                                <div class="product__review">
                                                    <span class="product__star__num">4.6</span>
                                                    <span class="product__star__icon">
                                                        <i class="fa-solid fa-star"></i>
                                                    </span>
                                                    <span class="product__number__comment">(22)</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col p-2-4 t-4 m-6">
                                        <div class="product__item">
                                            <div class="product__image" 
                                                 style="background-image: url(<c:url value='/views/source/image/product/pd07.png'/>);">
                                            </div>
                                            <div class="product__info">
                                                <span class="product__supply">Samsung</span>
                                                <div class="product__name">Điện thoại Samsung A2 104</div>
                                                <div class="product__price">
                                                    <span class="price__sold">2.000.000đ</span>
                                                    <span class="percent">Giảm 20%</span>
                                                </div>
                                                <div class="product__review">
                                                    <span class="product__star__num">4.6</span>
                                                    <span class="product__star__icon">
                                                        <i class="fa-solid fa-star"></i>
                                                    </span>
                                                    <span class="product__number__comment">(22)</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col p-2-4 t-4 m-6">
                                        <div class="product__item">
                                            <div class="product__image" 
                                                 style="background-image: url(<c:url value='/views/source/image/product/pd02.png'/>);">
                                            </div>
                                            <div class="product__info">
                                                <span class="product__supply">Samsung</span>
                                                <div class="product__name">Điện thoại Samsung A2 104</div>
                                                <div class="product__price">
                                                    <span class="price__sold">2.000.000đ</span>
                                                    <span class="percent">Giảm 20%</span>
                                                </div>
                                                <div class="product__review">
                                                    <span class="product__star__num">4.6</span>
                                                    <span class="product__star__icon">
                                                        <i class="fa-solid fa-star"></i>
                                                    </span>
                                                    <span class="product__number__comment">(22)</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col p-2-4 t-4 m-6">
                                        <div class="product__item">
                                            <div class="product__image" 
                                                 style="background-image: url(<c:url value='/views/source/image/product/pd06.png'/>);">
                                            </div>
                                            <div class="product__info">
                                                <span class="product__supply">Samsung</span>
                                                <div class="product__name">Điện thoại Samsung A2 104</div>
                                                <div class="product__price">
                                                    <span class="price__sold">2.000.000đ</span>
                                                    <span class="percent">Giảm 20%</span>
                                                </div>
                                                <div class="product__review">
                                                    <span class="product__star__num">4.6</span>
                                                    <span class="product__star__icon">
                                                        <i class="fa-solid fa-star"></i>
                                                    </span>
                                                    <span class="product__number__comment">(22)</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

					</div>
				</div>

                <!-- Comment -->
                <div class="row">
                    <div class="col p-12 t-12 m-12">
                        <!-- No comment: .no-comment -->
                        <div class="comment">
                            <p class="comment__header__text">Bình luận sản phẩm</p>
                            <div class="comment__header">
                                <textarea class="comment__text__edit" placeholder="Nhập bình luận..."></textarea>
                                <div class="comment__send">
                                    <button>Gửi bình luận</button>
                                </div>

                                <!-- Active: .active -->
                                <div class="comment__information__contain">
                                    <div class="grid wide">
                                        <div class="row">
                                            <div class="col p-5 t-8 m-12">
                                                <div class="comment__information">
                                                    <div class="comment__information__header">
                                                        <span class="comment__information__text">Thông tin người gửi</span>
                                                        <span class="comment__information__close"><i class="fa-solid fa-xmark"></i></span>
                                                    </div>

                                                    <div class="comment__information__body">
                                                        <div class="formGroupt" id="fullname">
                                                            <input type="text" placeholder="Nhập họ tên">
                                                            <!-- Error: .error -->
                                                            <span class="form-message">Trường này không thể bỏ trống!</span>
                                                        </div>

                                                        <div class="formGroupt" id="email">
                                                            <input type="text" placeholder="Nhập email">
                                                            <span class="form-message">Trường này không thể bỏ trống!</span>
                                                        </div>

                                                        <div class="formGroupt" id="phone">
                                                            <input type="number" placeholder="Nhập số điện thoại">
                                                            <span class="form-message">Trường này không thể bỏ trống!</span>
                                                        </div>
                                                    </div>

                                                    <div class="comment__information__send">Gửi bình luận</div>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                            </div>
                            <div class="comment__body">
                                <p class="comment__body__text">Có 100 bình luận</p>
                                <div class="comment__list">
                                    <div class="comment__item">
                                        <div class="comment__item__question">
                                            <span class="comment__text__avatar">N</span>
                                            <span class="comment__item__name">Nhi</span>
                                            <div class="comment__item__content">Máy có bán trả góp không ạ?</div>
                                            <span class="comment__item__reply">Trả lời</span>
                                            <span class="comment__item__time">7 giờ trước</span>
                                        </div>
                                        <div class="comment__reply">
                                            <div class="comment__reply__contain">
                                                <!-- Admin: .admin -->
                                                <div class="comment__reply__item admin">
                                                    <span class="comment__text__avatar">Q</span>
                                                    <span class="comment__item__name">Hữu Qui</span>
                                                    <span class="comment__item__admin">Quản trị viên</span>
                                                    <div class="comment__item__content">
                                                        Dạ bên em có hỗ trợ bán trả góp ạ. <br>
                                                        Thông tin đến chị.
                                                    </div>
                                                    <span class="comment__item__reply">Trả lời</span>
                                                    <span class="comment__item__time">7 giờ trước</span>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="comment__item">
                                        <div class="comment__item__question">
                                            <span class="comment__text__avatar">N</span>
                                            <span class="comment__item__name">Nhi</span>
                                            <div class="comment__item__content">Máy có bán trả góp không ạ?</div>
                                            <span class="comment__item__reply">Trả lời</span>
                                            <span class="comment__item__time">7 giờ trước</span>
                                        </div>
                                        <div class="comment__reply">
                                            <div class="comment__reply__contain">
                                                <div class="comment__reply__item admin">
                                                    <span class="comment__text__avatar">Q</span>
                                                    <span class="comment__item__name">Hữu Qui</span>
                                                    <span class="comment__item__admin">Quản trị viên</span>
                                                    <div class="comment__item__content">
                                                        Dạ bên em có hỗ trợ bán trả góp ạ. <br>
                                                        Thông tin đến chị.
                                                    </div>
                                                    <span class="comment__item__reply">Trả lời</span>
                                                    <span class="comment__item__time">7 giờ trước</span>
                                                </div>

                                                <div class="comment__reply__item">
                                                    <span class="comment__text__avatar">N</span>
                                                    <span class="comment__item__name">Nhi</span>
                                                    <span class="comment__item__admin">Quản trị viên</span>
                                                    <div class="comment__item__content">
                                                        Ok, cám ơn em.
                                                    </div>
                                                    <span class="comment__item__reply">Trả lời</span>
                                                    <span class="comment__item__time">7 giờ trước</span>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                </div>
                                <div class="comment__pagination">
                                    <div class="row">
                                        <div class="col p-6 t-8 m-12">
                                            <ul class="comment__pagination__list">
                                                <li class="comment__pagination__item">
                                                    <a href="#"><<</a>
                                                </li>

                                                <!-- Active: .active -->
                                                <li class="comment__pagination__item active">
                                                    <a href="#">1</a>
                                                </li>

                                                <li class="comment__pagination__item">
                                                    <a href="#">2</a>
                                                </li>

                                                <li class="comment__pagination__item">
                                                    <a href="#">3</a>
                                                </li>

                                                <li class="comment__pagination__item">
                                                    <a href="#">...</a>
                                                </li>

                                                <li class="comment__pagination__item">
                                                    <a href="#">20</a>
                                                </li>

                                                <li class="comment__pagination__item">
                                                    <a href="#">>></a>
                                                </li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

        	</div>
        </div>
        <!-- end .body -->
        <!-- Active: .active -->
	    <div class="overload"></div>
		<!-- Javascript -->
		<script>
			var idProduct = ${id};
		</script>
		<script type="text/javascript" src="<c:url value='/views/web/js/header.js'/>"></script>
		<script type="text/javascript" src="<c:url value='/views/web/js/productDetail.js'/>"></script>
		<script type="text/javascript">header(true);</script>
</body>
</html>