<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/14
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/sourceServlet?op=addSource" method="post" enctype="multipart/form-data">

   作者 <input type="text" name="author">
    资源<input type="file" name="name">
    <input type="submit">
</form>
</body>
</html>
