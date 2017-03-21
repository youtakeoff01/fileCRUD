<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>upload file</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<body>

<form name="uploadForm" action="${pageContext.request.contextPath}/uploadFile" enctype="multipart/form-data" 
	method="post">
 	file:<input type="file" name="file"><br/>
         <input type="submit" value="提交">
</form>

</body>
</html>

