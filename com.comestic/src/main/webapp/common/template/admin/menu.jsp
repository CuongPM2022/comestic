<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
 <!-- Active: .active -->
 <div class="menu">
     <!-- Active: .active -->
     <div class="menu__item active">
         <a href="<c:url value='/admin-home' />">
             <i class="fa-solid fa-house"></i>
             <span class="menu__item__text">Trang chủ</span>
         </a>
     </div>

     <div class="menu__item">
         <a href="<c:url value='/admin-bill?action=listBill' />">
             <i class="fa-solid fa-money-bills"></i>
             <span class="menu__item__text">Quản lý đơn hàng</span>
         </a>
     </div>

     <div class="menu__item">
         <a href="<c:url value='/admin-category?action=allCategory' />">
             <i class="fa-regular fa-rectangle-list"></i>
             <span class="menu__item__text">Quản lý danh mục</span>
         </a>
     </div>

      <div class="menu__item">
         <a href="<c:url value='/admin-product?action=allProduct' />">
             <i class="fa-brands fa-product-hunt"></i>
             <span class="menu__item__text">Quản lý sản phẩm</span>
         </a>
     </div>

     <div class="menu__item">
         <a href="#">
             <i class="fa-solid fa-comments"></i>
             <span class="menu__item__text">Quản lý bình luận</span>
         </a>
     </div>
     
 </div>
 <!-- end .menu -->