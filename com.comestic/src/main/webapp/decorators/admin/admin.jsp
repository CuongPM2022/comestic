<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/common/css/grid.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/common/css/icon/css/all.min.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/views/admin/css/header_menu.css'/>">
    <dec:head />
</head>
<body>
	<div id="contain">
		<%@ include file="/common/template/admin/header.jsp" %>
		<%@ include file="/common/template/admin/menu.jsp" %>
		<dec:body />
	</div>
	 <!-- Javascript -->
    <script type="text/javascript" src="<c:url value='/views/admin/js/header_menu.js'/>"></script>
</body>
</html>