<%--
  Created by 紫英
  User: MI
  Date: 2022/4/21
  Time: 10:03
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:forward page="/customer?action=pageByName&pageNo=1"></jsp:forward>
<%--直接请求转发到CustomerFurnServlet，作为一个程序入口--%>
<%--改成都走byName的，没有name的话就模糊出所有的--%>
