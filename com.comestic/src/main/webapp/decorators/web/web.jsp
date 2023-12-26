<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><dec:title default="Trang chủ"></dec:title></title>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/common/css/grid.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/common/css/icon/css/all.min.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/views/web/css/header.css'/>">
<dec:head />
</head>
<body>
	<%@ include file="/common/template/web/header.jsp"%>
	<dec:body />
	<%@ include file="/common/template/web/footer.jsp"%>
</body>
</html>