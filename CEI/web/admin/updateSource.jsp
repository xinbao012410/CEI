<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/18
  Time: 9:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/sourceServlet?op=updateSource&id=${source.id}"method="post">
<table >
<tr>
    <td>资源名</td>
    <td>
        <input type="file" name="name" value="${source.name}">
    </td>
</tr>
    <tr>
        <td>作者</td>
        <td>
            <input type="text" name="" value="${source.author}">
        </td>
    </tr>
    <tr>
        <td>路径</td>
        <td>
            <input type="text" name="path" value="${source.path}">
        </td>
    </tr>
    <tr>
        <td>点击量</td>
        <td>
            <input type="text" name="pageView"  value="${source.pageView}"/>
        </td>
    </tr>
    <tr>
        <td>创建时间</td>
        <td>
            <input type="text" name="creatTime"  value="${source.creatTime}"/>
        </td>
    </tr>
    <tr  align="center">
        <td colspan="2">
            <input type="submit" value="修改资源"/>
        </td>
    </tr>
</table>
</form>
</body>
</html>
