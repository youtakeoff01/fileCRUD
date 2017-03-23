<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>upload file</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<body>

<form name="uploadForm" action="${pageContext.request.contextPath}/uploadFile" enctype="multipart/form-data" 
	method="post">
 	上传的文件:<input type="file" name="file"><br/>
         <input type="submit" value="提交">
</form>
<br />
<form name="checkInForm" action="${pageContext.request.contextPath}/checkIn" enctype="multipart/form-data" 
	method="post">
 	签入的文件:<input type="file" name="file"><br/>
         <input type="submit" value="签入">
</form>
<br />
<a href="${pageContext.request.contextPath}/checkOut">签出</a>
<br />
<a href="${pageContext.request.contextPath}/unCheckOut">取消签出</a>
<br />
<a href="${pageContext.request.contextPath}/removeElement">删除元素</a>
</body>
</html>

