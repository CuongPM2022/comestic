<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/admin/css/dashboard.css'/>">
</head>
<body>
    <div class="body">
        <div class="statistic turnover">
            <div class="header__contain">
                <p class="text__header">THỐNG KÊ DOANH THU</p>
                <div class="filter">
                    <!-- Show: .show -->
                    <div class="filter__select">
                        <div>
                            <input type="date" class="minDay">
                        </div>

                        <div>
                            <input type="date" class="maxDay">
                        </div>

                        <button class="select__day">Áp dụng</button>
                    </div>

                    <!-- active: .active -->
                    <div class="sort">
                        <div class="sort__header">
                            <span class="sort__header__text">Lọc theo</span>
                            <i class="fa-solid fa-caret-down"></i>
                        </div>
                        <div class="sort__list">
                            <!-- active: .active -->
                            <div class="sort__item active" data-sort="day">
                                <i class="fa-solid fa-check"></i>
                                <span>Hôm nay</span>
                            </div>
                            <div class="sort__item" data-sort="week">
                                <i class="fa-solid fa-check"></i>
                                <span>Tuần này</span>
                            </div>
                            <div class="sort__item" data-sort="month">
                                <i class="fa-solid fa-check"></i>
                                <span>Tháng này</span>
                            </div>
                            <div class="sort__item other" data-sort="other">
                                <i class="fa-solid fa-check"></i>
                                <span>Tự chọn</span>
                            </div>
                        </div>
                    </div>

                </div>
            </div>

            <div class="statistic__list">
                <div class="grid">
                    <div class="row">
                        <div class="col p-3">
                            <div class="statistic__item newbill">
                                <span class="statistic__icon">
                                    <i class="fa-solid fa-cart-plus"></i>
                                </span>
                                <div class="statistic__item__body">
                                    <p class="statistic__body__text">Đơn hàng mới</p>
                                    <p class="statistic__body__number"></p>
                                </div>
                            </div>
                        </div>

                        <div class="col p-3">
                            <div class="statistic__item totalbill">
                                <span class="statistic__icon">
                                    <i class="fa-solid fa-file-invoice-dollar"></i>
                                </span>
                                <div class="statistic__item__body">
                                    <p class="statistic__body__text">Tổng đơn hàng</p>
                                    <p class="statistic__body__number"></p>
                                </div>
                            </div>
                        </div>

                        <div class="col p-3">
                            <div class="statistic__item total_statistic">
                                <span class="statistic__icon">
                                    <i class="fa-solid fa-hand-holding-dollar"></i>
                                </span>
                                <div class="statistic__item__body">
                                    <p class="statistic__body__text">Tổng doanh thu</p>
                                    <p class="statistic__body__number"></p>
                                </div>
                            </div>
                        </div>

                        <div class="col p-3">
                            <div class="statistic__item deletebill">
                                <span class="statistic__icon">
                                    <i class="fa-solid fa-trash"></i>
                                </span>
                                <div class="statistic__item__body">
                                    <p class="statistic__body__text">Đơn hàng đã hủy</p>
                                    <p class="statistic__body__number"></p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- end .turnover -->

        <div class="statistic access">
            <div class="header__contain">
                <p class="text__header">THỐNG KÊ LƯỢNG TRUY CẬP</p>
            </div>

            <div class="statistic__list">
                <div class="grid">
                    <div class="row">
                        <div class="col p-3">
                            <div class="statistic__item total_access">
                                <span class="statistic__icon">
                                    <i class="fa-solid fa-earth-americas"></i>
                                </span>
                                <div class="statistic__item__body">
                                    <p class="statistic__body__text">Tổng lượt truy cập</p>
                                    <p class="statistic__body__number"></p>
                                </div>
                            </div>
                        </div>

                        <div class="col p-3">
                            <div class="statistic__item online">
                                <span class="statistic__icon">
                                    <i class="fa-solid fa-passport"></i>
                                </span>
                                <div class="statistic__item__body">
                                    <p class="statistic__body__text">Đang online</p>
                                    <p class="statistic__body__number">2</p>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <!-- end .access -->

        <div class="list_new_bill">
            <div class="header__contain">
                <p class="text__header">ĐƠN HÀNG MỚI NHẬN</p>
            </div>

            <table class="listTable">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>Mã</th>
                        <th>Khách hàng</th>
                        <th>Tổng tiền</th>
                        <th>Thời gian</th>
                        <th class="tbCenter">Thao tác</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
        </div>

    </div>

    <!-- Active: .active -->
    <div class="overload"></div>
    <script type="text/javascript" src="<c:url value='/views/admin/js/dashboard.js'/>"></script>
</body>
</html>