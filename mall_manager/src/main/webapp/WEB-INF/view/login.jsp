<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="position: absolute;left: 50%;top: 30%;transform: translate(-50%,-50%)">
 <form action="/ja" method="post" id="dataFrom" enctype="multipart/form-data">
     <h1 align="center">word英译日</h1>
     <label>上传翻译文件:</label>&nbsp;&nbsp;
     <input type="file" name="uploadFile" id="uploadFile" multiple="multiple" >
     <button type="submit">翻译</button>
 </form>
</div>
</body>
</html>