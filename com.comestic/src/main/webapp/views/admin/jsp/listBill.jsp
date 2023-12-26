<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Danh sách đơn hàng</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/admin/css/listBill.css'/>">
</head>
<body>
     <div class="body" id="list_contain">
         <div class="sort__contain">
             <p class="text__header">DANH SÁCH ĐƠN HÀNG</p>
             <!-- active: .active -->
             <div class="sort">
                 <div class="allAction">
                     <!-- show: .show -->
                     <span class="allAction__item action--delete">
                         <i class="fa-solid fa-trash"></i>
                     </span>
                 </div>
                 <div class="sort__header">
                     <span class="sort__header__text">Lọc theo</span>
                     <i class="fa-solid fa-caret-down"></i>
                 </div>
                 <div class="sort__list">
                     <!-- active: .active -->
                 </div>
             </div>
         </div>

         <table class="listTable">
             <thead>
                 <tr>
                     <th><input type="checkbox"></th>
                     <th>STT</th>
                     <th>Mã</th>
                     <th>Khách hàng</th>
                     <th>Tổng tiền</th>
                     <th>Thời gian</th>
                     <th>Trạng thái</th>
                     <th class="tbCenter">Thao tác</th>
                 </tr>
             </thead>
             <tbody>
                
             </tbody>
         </table>

         <!-- Hide: .hide -->
         <div class="listPaging__contain hide">
             <div class="row">
                 <div class="col p-4 t-8 m-12">
                     <ul class="listPaging">
                         <!-- Active: .active -->
                         <li class="listPaging__item"><<</li>
                         <li class="listPaging__item active">1</li>
                         <li class="listPaging__item">2</li>
                         <li class="listPaging__item">3</li>
                         <li class="listPaging__item">...</li>
                         <li class="listPaging__item">10</li>
                         <li class="listPaging__item">>></li>
                     </ul>
                 </div>
             </div>
         </div>
     </div>

     <!-- Active: .active -->
     <div class="overload"></div>

    <!-- Javascript -->
    <script type="text/javascript" src="<c:url value='views/common/js/listTable.js'/>"></script>
    <script type="text/javascript" src="<c:url value='views/admin/js/listBill.js'/>"></script>
</body>
</html>