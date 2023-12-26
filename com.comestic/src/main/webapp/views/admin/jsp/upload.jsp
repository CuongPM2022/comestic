<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div>
		
		<form enctype="multipart/form-data">
			
			<input type="file" name="file1" class="files" multiple />
			<input type="file" name="file2"  class="files" multiple />
			<input type="file" name="file3"  class="files" multiple />

			<button id="submit">SUbmit</button>
		</form>
	</div>

<script type="text/javascript" src="<c:url value='/views/admin/js/upload.js' />"></script>
</body>
</html>