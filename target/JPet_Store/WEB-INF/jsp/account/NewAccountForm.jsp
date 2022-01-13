<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/11/3
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Catalog">
    <form action="newAccount"method="post">

    <h3>User Information</h3>

    <table>
        <tr>
            <td>User ID:</td>
            <td><input type="text" name="username" id="username" /></td>
            <td style="background: white"><div id="isExistInfo"></div></td>
        <tr>
            <td>New password:</td>
            <td><input type="password" name="password" id="password"/></td>
            <td style="background: white"><div id="isPwd"></div></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><input type="password" name="repeatPassword" id="repeatPassword"/></td>
            <td style="background: white"><div id="isRePwd"></div></td>
        </tr>
        <tr>
            <td>VerificationCode:</td>
            <td><input type="text" name="verificationCode" /></td>
            <td><img id="img" src="verify"  alt=""></td>
        </tr>
        <tr> <font color='red'>${sessionScope.message2}</font></tr>
        <tr><font color='red'>${sessionScope.message1}</font></tr>
    </table>

    <%@ include file="IncludeAccountFields.jsp"%>

    <input type="submit" name="newAccount" value="register" />
</form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>