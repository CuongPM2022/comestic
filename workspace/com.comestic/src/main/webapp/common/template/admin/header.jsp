<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <div class="header">
     <div class="row center">
         <div class="col p-8 t-4 m-4">
             <div class="header__dropmenu">
                 <span class="header__dropmenu__icon">
                     <i class="fa-solid fa-bars"></i>
                 </span>

                 <span class="header__text">ADMIN</span>
             </div>
         </div>

         <div class="col p-4 t-8 m-8">
             <div class="header__detail">
                 <span class="header__search__icon header__detail__icon">
                     <i class="fa-solid fa-magnifying-glass"></i>
                 </span>

                 <!-- Empty: .empty -->
                 <div class="header__notify__icon header__detail__icon">
                     <i class="fa-solid fa-bell"></i>

                     <span class="header__notify__number">4</span>

                     <!-- Popup: .popup -->
                     <div class="header__notify">
                         <p class="header__notify__text">THÔNG BÁO MỚI</p>
                         <div class="header__notify__body">
                             <!-- Hide: .hide -->
                             <div class="header__notify__item">
                                 <span class="notify__item__icon" style="background:var(--primaryGreen);">
                                     <i class="fa-solid fa-cart-plus"></i>
                                 </span>
                                 <div class="notify__item__content">
                                     <p class="notify__content__text">4 đơn hàng mới</p>
                                     <p class="notify__content__time">
                                         <i class="fa-regular fa-clock"></i>
                                         <span class="content__time__text">
                                             2 giờ trước
                                         </span>
                                     </p>
                                 </div>
                             </div>

                             <div class="header__notify__item">
                                 <span class="notify__item__icon" style="background:var(--primaryBlue);">
                                     <i class="fa-solid fa-comments"></i>
                                 </span>
                                 <div class="notify__item__content">
                                     <p class="notify__content__text">2 bình luận mới</p>
                                     <p class="notify__content__time">
                                         <i class="fa-regular fa-clock"></i>
                                         <span class="content__time__text">
                                             3 giờ trước
                                         </span>
                                     </p>
                                 </div>
                             </div>

                             <div class="header__notify__item">
                                 <span class="notify__item__icon" style="background:var(--primaryRed);">
                                     <i class="fa-regular fa-trash-can"></i>
                                 </span>
                                 <div class="notify__item__content">
                                     <p class="notify__content__text">1 đơn hàng đã hủy</p>
                                     <p class="notify__content__time">
                                         <i class="fa-regular fa-clock"></i>
                                         <span class="content__time__text">
                                             2 giờ trước
                                         </span>
                                     </p>
                                 </div>
                             </div>
                         </div>
                         <div class="header__notify__empty">Hiện chưa có thông báo nào!</div>
                     </div>

                 </div>

                 <div class="header__account">
                     <span class="header__account__name">
                         <span>Nam Phan</span>
                         <i class="fa-solid fa-caret-down"></i>
                     </span>
                 
                     <img src="<c:url value='/views/source/image/product/pd01.png'/>">
                     <!-- Active: .active -->
                     <div class="account__menu">
                         <div class="account__item">
                             <i class="fa-regular fa-user"></i>
                             <span>Thông tin tài khoản</span>
                         </div>
                         <div class="account__item">
                             <i class="fa-solid fa-right-from-bracket"></i>
                             <span>Đăng xuất</span>
                         </div>
                     </div>
                 </div>
             </div>
         </div>

         <div class="col p-12 t-12 m-12">
             <!-- Active: .active -->
             <div class="header__search">
                 <span class="header__search__icon">
                     <i class="fa-solid fa-magnifying-glass"></i>
                 </span>

                 <form action="" mrthod="get" class="frmSearch">
                     <input type="text" name="txtSearch" placeholder="Nhập từ khóa cần tìm...">
                 </form>

                 <span class="header__search__close">
                     <i class="fa-solid fa-xmark"></i>
                 </span>
             </div>
         </div>

     </div>
 </div>
 <!-- end .header -->