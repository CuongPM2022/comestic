<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thêm mới sản phẩm</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/common/css/grid.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/common/css/icon/css/all.min.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/admin/css/createProduct.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/admin/css/header_menu.css' />">
</head>
<body>
    <div class="body">
        <div class="grid">
            <div class="row">
                <div class="col p-8">
                    <div class="createProduct form">
                        <p class="form__header">Thông tin chi tiết sản phẩm</p>

                        <div class="form__body grid">
                            <div class="row">
                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Tên sản phẩm</p>
                                        <input class="form__item__input" type="text" name="name"
                                               rules="require" placeholder="Điện thoại đi động">
                                        <!-- show: .show -->
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Mã code sản phẩm</p>
                                        <input class="form__item__input" type="text" name="code"
                                               rules="require" placeholder="dien-thoai-di-dong">
                                        <!-- show: .show -->
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Số lượng cảnh báo</p>
                                        <input class="form__item__input" type="number" placeholder="4"
                                               rules="number|min=2" name="numberLimit">
                                        <!-- show: .show -->
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Danh mục sản phẩm</p>
                                        <select class="form__item__input category" rules="require" name="categoryCode">
                                            
                                        </select>
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Nhà sản xuất</p>
                                        <select class="form__item__input manufacture" rules="require" name="manufactureCode">
                                           
                                        </select>
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Trạng thái</p>
                                        <div class="formGroupt__radio">
                                            <input checked class="form__item__input" value="1"
                                                   rules="require" name="state" type="radio"> Hiển thị
                                            <span class="form__blank"></span>
                                            <input class="form__item__input" value="0"
                                                   rules="require" name="state" type="radio"> Ẩn
                                        </div>
                                    </div>
                                </div>

                                <div class="col p-12">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Mô tả ngắn</p>
                                        <textarea class="form__item__input" rules="require" name="shortDescription"
                                                  placeholder="Nhập mô tả ngắn..."></textarea>
                                        <!-- show: .show -->
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-12">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Bài viết cho sản phẩm</p>
                                        <textarea class="form__item__input" rules="require" name="longDescription"
                                                  placeholder="Nội dung bài viết..."></textarea>
                                        <!-- show: .show -->
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                            </div>

                        </div>
                    </div>
                    <!-- end .createProduct -->

                    <div class="dataProduct">
                        <div class="box__header">
                            <p class="box__header__text">Dữ liệu sản phẩm</p>
                            <select class="select__variety">
                                <option selected value="0">Sản phẩm đơn giản</option>
                                <option value="1">Sản phẩm có nhiều biến thể</option>
                            </select>
                        </div>

                        <!-- Active: .active -->
                        <div class="product__single active">
                            <div class="row">
                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <img class="form__review__image" src="<c:url value='/views/source/image/default_image.jpg' />">
                                        <input class="form__item__input" rules="require" name="image" type="file" hidden>
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Số lượng</p>
                                        <input class="form__item__input" type="number" name="number" rules="number|min=1">
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Giá bán</p>
                                        <input class="form__item__input" type="number" rules="double|min=0" name="price">
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                                <div class="col p-6">
                                    <div class="formGroupt">
                                        <p class="form__item__name">Giá ưu đãi</p>
                                        <input class="form__item__input" type="number" name="priceSale" rules="double|min=0">
                                        <span class="form-message">Trường này không thể bỏ trống</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <!-- end .product__single -->

                        <!-- Active: .active -->
                        <div class="product__many__variety">
                            <div class="row no-gutter">
                                <div class="col p-3">
                                    <div class="product__variety__btn">
                                        <!-- Active: .active -->
                                        <div class="btn__variety attribute active">Các thuộc tính</div>
                                        <div class="btn__variety variety">Các biến thể</div>
                                    </div>
                                </div>

                                <div class="col p-9">
                                    <div class="product__variety__contain">
                                        <!-- Active: .active -->
                                        <div class="product__attribute active">
                                            <div class="attribute__header">
                                                <!-- Active: .active -->
                                                <div class="attribute__select__contain active">
                                                    <select class="attribute__select">
                                                        <option value="">Chọn thuộc tính </option>
                                                        <option value="1">Màu sắc</option>
                                                        <option value="2">Kích thước</option>
                                                    </select>
                                                    <button class="attribute__btn add">Thêm</button>
                                                </div>
                                                <button class="attribute__btn add_new">Tạo thuộc tính mới</button>
                                            </div>

                                            <div class="attribute__list">
                                                <!-- Short: .short -->
                                                <!-- Error data: .error -->
                                            </div>
                                        </div>

                                        <!-- Active: .active -->
                                        <div class="product__variety">
                                            <div class="variety__header">
                                                <select class="btn variety__select">
                                                    <option value="1">Thêm một biến thể mới</option>
                                                    <option value="2">Tạo biến thể từ tất cả thuộc tính</option>
                                                </select>
                                                <!-- Active: .active -->
                                                <button class="btn variety__btn active">Thực hiện</button>
                                            </div>

                                            <!-- Active: .active -->
                                            <div class="variety__option">
                                                <div class="row">
                                                    <!-- List attribute select -->
                                                </div>
                                                <div>
                                                    <button class="btn variety__option__btn">Thêm biến thể</button>
                                                </div>
                                            </div>

                                            <div class="variety__list">
                                                <!-- Short: .short -->
                                                <!-- error data: .error -->

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <!-- end .product__many__variety -->

                        <button class="btn create__product__apply">Thêm sản phẩm</button>
                    </div>
                    <!-- end .dataProduct -->

                </div>

                <div class="col p-4">
                    <div class="createCategory form">
                        <p class="form__header">Thêm danh mục sản phẩm</p>

                        <div class="form__body">
                            <div class="formGroupt">
                                <p class="form__item__name">Tên danh mục</p>
                                <input class="form__item__input" name="name" rules="require" type="text" placeholder="Điện tử">
                                <!-- show: .show -->
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <div class="formGroupt">
                                <p class="form__item__name">Mã code danh mục</p>
                                <input class="form__item__input" name="code_1" rules="require" type="text" placeholder="dien-tu">
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <div class="formGroupt">
                                <p class="form__item__name">Chọn ảnh cho danh mục</p>
                                <img class="form__review__image" src="<c:url value='/views/source/image/default_image.jpg' />">
                                <input class="form__item__input" name="image" rules="require" type="file" hidden>
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <button class="form__button category_addnew">Thêm danh mục</button>

                        </div>

                    </div>
                
                    <div class="createManufacture form">
                        <p class="form__header">Thêm nhà sản xuất</p>

                        <div class="form__body">
                            <div class="formGroupt">
                                <p class="form__item__name">Tên nhà sản xuất</p>
                                <input class="form__item__input" name="name" rules="require" type="text" placeholder="Panasonic">
                                <!-- show: .show -->
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <div class="formGroupt">
                                <p class="form__item__name">Mã code nhà sản xuất</p>
                                <input class="form__item__input" name="code_1" rules="require" type="text" placeholder="panasonic">
                                <!-- show: .show -->
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <button class="form__button manufacture_addnew">Thêm nhà sản xuất</button>

                        </div>

                    </div>

                </div>
            </div>
        </div>
    </div>

    <!-- Active: .active -->
    <div class="overload"></div>

    <!-- Javascript -->
    <script type="text/javascript">
        var id = null;
        <c:if test="${id != null}">id=${id}</c:if>
    </script>
    <script type="text/javascript" src="<c:url value='/views/admin/js/header_menu.js' />"></script>
    <script type="text/javascript" src="<c:url value='/views/common/js/validate.js' />"></script>
    <script type="text/javascript" src="<c:url value='/views/admin/js/createProduct_core.js' />"></script>
    <script type="text/javascript" src="<c:url value='/views/admin/js/createProduct_model.js' />"></script>
    <script type="text/javascript" src="<c:url value='/views/admin/js/createProduct_view.js' />"></script>
    <script type="text/javascript" src="<c:url value='views/admin/js/createProduct_controller.js' />"></script>
    <script type="text/javascript" src="<c:url value='views/admin/js/createProduct.js' />"></script>
</body>
</html>