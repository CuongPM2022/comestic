<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head> 
    <title>Bill Detail</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/admin/css/billDetail.css'/>">
</head>
<body>
    <div class="body">
    	<div class="info__customer">
    		<p class="text__header">THÔNG TIN KHÁCH HÀNG</p>
    		<div class="row">
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                            <p class="info-groupt__text">Họ tên khách hàng</p>
                            <p class="info-groupt__content fullname"></p>
                        </div>
                </div>
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Giới tính</p>
                        <p class="info-groupt__content gender"></p>
                    </div>
                </div>
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Số điện thoại</p>
                        <p class="info-groupt__content phone"></p>
                    </div>
                </div>
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Email</p>
                        <p class="info-groupt__content email"></p>
                    </div>
                </div>
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Địa chỉ nhận hàng</p>
                        <p class="info-groupt__content address"></p>
                    </div>
                </div>
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Yêu cầu</p>
                        <p class="info-groupt__content note"></p>
                    </div>
                </div>
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Hình thức thanh toán</p>
                        <p class="info-groupt__content method"></p>
                    </div>
                </div>
            </div>
        </div>

        <div class="bill__info">
            <p class="text__header">THÔNG TIN ĐƠN HÀNG</p>
            <div class="row">
                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Mã đơn hàng</p>
                        <p class="info-groupt__content code"></p>
                    </div>
                </div>

                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Ngày tạo</p>
                        <p class="info-groupt__content date"></p>
                    </div>
                </div>

                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Tổng tiền</p>
                        <p class="info-groupt__content totalMoney"></p>
                    </div>
                </div>

                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Giảm giá</p>
                        <p class="info-groupt__content percentDes"></p>
                    </div>
                </div>

                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Thanh toán</p>
                        <p class="info-groupt__content importMoney"></p>
                    </div>
                </div>

                <div class="col p-6 t-12 m-12">
                    <div class="info-groupt">
                        <p class="info-groupt__text">Trạng thái đơn hàng</p>
                        <div class="bill__info__state">
                            <select class="select__state">
                               
                            </select>
                            <button class="btn__state">Cập nhật trạng thái</button>
                        </div>
                    </div>
                </div>

            </div>
        </div>

    	<div class="bill__detail">
    		<p class="text__header">CHI TIẾT ĐƠN HÀNG</p>
            <table class="listTable">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã sản phẩm</th>
                        <th>Tên sản phẩm</th>
                        <th>Ảnh sản phẩm</th>
                        <th>Giá bán</th>
                        <th>Số lượng</th>
                        <th>Giảm giá</th>
                        <th>Thành tiền</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
    	</div>
    </div>
    <div class="overload"></div>
    <!-- Javascript -->
    <script type="text/javascript">
        var billId = ${billId};
    </script>
    <script type="text/javascript" src="<c:url value='/views/admin/js/billDetail.js'/>"></script>
</body>
</html>