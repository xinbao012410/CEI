<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/14
  Time: 8:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--jstl 标准标签库--%>
<html>
<head>
    <title>资源列表</title>
</head>
<body>
<table border="1" width="1000">
    <tr>
        <th>序号</th>
        <th>名字</th>
        <th>作者</th>
        <th>路径</th>
        <th>浏览量</th>
        <th>时间</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${list}"  var="l">
        <tr>
            <td>${l.id}</td>
            <td>${l.name}</td>
            <td>${l.author}</td>
            <td>${l.path}</td>
            <td>${l.pageView}</td>
            <td>${l.creatTime}</td>
            <td >
                <a href="JavaScript:delSource('${l.id}')">删除</a>
                <a href="${pageContext.request.contextPath}/sourceServlet?op=editSource&id=${l.id}">修改</a>
                <a href="${pageContext.request.contextPath}/sourceServlet?op=downloadSource&id=${l.id}">下载</a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
<script>
    function delSource(id) {
        var sure = confirm("确定要删除吗?");
        if(sure){
            window.location.href="${pageContext.request.contextPath}/sourceServlet?op=delSource&id="+id;
        }else {
            alert("再想想");
        }
    }
</script>