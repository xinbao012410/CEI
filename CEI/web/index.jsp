<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/6/6
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>

  <a href="${pageContext.request.contextPath}/sourceServlet?op=goToAddSourceView">添加资源</a>
  <a href="${pageContext.request.contextPath}/sourceServlet?op=findAllSources">查询所有资源</a>
  </body>
</html>
