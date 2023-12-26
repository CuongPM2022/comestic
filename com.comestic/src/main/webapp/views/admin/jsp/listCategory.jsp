<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Quản lý danh mục</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/admin/css/listCategory.css' />">
</head>
<body>
    <div class="body" id="list_contain">
        <div class="header__contain">
            <p class="text__header">QUẢN LÝ DANH MỤC SẢN PHẨM</p>
            <div class="allAction">
                <span class="allAction__item action--create show">
                    <i class="fa-regular fa-square-plus"></i>
                </span>
                <!-- Show: .show -->
                <span class="allAction__item action--delete">
                    <i class="fa-solid fa-trash"></i>
                </span>
            </div>
        </div>

        <table class="listTable">
            <thead>
                <tr>
                    <th><input type="checkbox" ></th>
                    <th>STT</th>
                    <th>Mã</th>
                    <th>Tên danh mục</th>
                    <th>Danh mục cha</th>
                    <th>Ảnh</th>
                    <th>Ngày tạo</th>
                    <th>Người tạo</th>
                    <th class="tbCenter">Tác vụ</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

        <!-- Hide: .hide -->
        <div class="listPaging__contain">
            <div class="row">
                <div class="col p-4 t-8 m-12">
                    <ul class="listPaging">
                       
                    </ul>
                </div>
            </div>
        </div>

        <!-- Popup: .popup -->
        <div class="form__contain grid wide">
            <div class="row center">
                <div class="col p-5 t-8 m-12">
                    <div class="form">
                        <p class="form__header">Thêm danh mục sản phẩm</p>

                        <div class="form__body">
                            <div class="formGroupt">
                                <p class="form__item__name">Tên danh mục</p>
                                <input class="form__item__input" type="text" placeholder="Điện tử" 
                                       rules="require" name="name">
                                <!-- show: .show -->
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <div class="formGroupt">
                                <p class="form__item__name">Mã code danh mục</p>
                                <input class="form__item__input" type="text" placeholder="dien-tu" 
                                       rules="require" name="code">
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <div class="formGroupt">
                                <p class="form__item__name">Chọn ảnh cho danh mục</p>
                                <img class="form__review__image" src="<c:url value='views/source/image/default_image.jpg' />">
                                <input class="form__item__input" type="file" rules="require" 
                                       name="imageName" hidden>
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>

                            <div class="formGroupt">
                                <p class="form__item__name">Chọn danh mục cha</p>
                                <select id="select__category" class="form__item__input" name="parentId">
                                 
                                </select>
                                <span class="form-message">Trường này không thể bỏ trống</span>
                            </div>
                            <button class="form__button form-submit">Thêm danh mục</button>

                        </div>

                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Active: .active -->
    <div class="overload"></div>

    <!-- Javascript -->
    <script type="text/javascript" src="<c:url value='views/common/js/validate.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/views/common/js/listTable.js' />"></script>
    <script type="text/javascript" src="<c:url value='/views/admin/js/listCategory.js' />"></script>
    <script type="text/javascript" src="<c:url value='/views/admin/js/pagination.js'/>"></script>
</body>
</html>