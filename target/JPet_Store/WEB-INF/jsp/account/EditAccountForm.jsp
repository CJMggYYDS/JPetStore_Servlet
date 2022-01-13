<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/11/3
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <form action="saveAccount" method="post">

    <h3>User Information</h3>

    <table>
        <tr>
            <td>User ID:</td>
            <td>${sessionScope.account.username}</td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><input type="text"  name="password" /></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="text"  name="repeatedPassword" /></td>
        </tr>
    </table>
    <%@ include file="IncludeAccountFields.jsp"%>

    <input type="submit" name="editAccount" value="Save Account Information" />

    </form><a href="viewListOrder?username=${sessionScope.account.username}">My Order</a></div>

<%@ include file="../common/IncludeBottom.jsp"%>
