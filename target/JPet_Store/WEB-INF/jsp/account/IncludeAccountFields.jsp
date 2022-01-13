<%@ page import="org.petstore.domain.Category" %>
<%@ page import="java.lang.reflect.AccessibleObject" %>
<%@ page import="org.petstore.domain.Account" %><%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/11/3
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<h3>Account Information</h3>

<table>
    <tr>
        <td>First name:</td>
        <td><input type="text" name="firstname" value="${sessionScope.account.firstName}"/>
        </td>
    </tr>
    <tr>
        <td>Last name:</td>
        <td><input type="text" name="lastname" value="${sessionScope.account.lastName}"/></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><input type="text" name="email" value="${sessionScope.account.email}" /></td>
    </tr>
    <tr>
        <td>Phone:</td>
        <td><input type="text" name="phone" value="${sessionScope.account.phone}"/></td>
    </tr>
    <tr>
        <td>Address 1:</td>
        <td><input type="text" name="address1" value="${sessionScope.account.address1}"/></td>
    </tr>
    <tr>
        <td>Address 2:</td>
        <td><input type="text" name="address2"value="${sessionScope.account.address2}" /></td>
    </tr>
    <tr>
        <td>City:</td>
        <td><input type="text" name="city" value="${sessionScope.account.city}"/></td>
    </tr>
    <tr>
        <td>State:</td>
        <td><input type="text" name="state" value="${sessionScope.account.state}"/></td>
    </tr>
    <tr>
        <td>Zip:</td>
        <td> <input type="text" name="zip"value="${sessionScope.account.zip}"/>
        </td>
    </tr>
    <tr>
        <td>Country:</td>
        <td><input type="text" name="country" value="${sessionScope.account.country}"/></td>
    </tr>
</table>

<h3>Profile Information</h3>

<table>
    <tr>
        <td>Language Preference:</td>
        <td> <select name="languagePreference">
            <c:if test="${sessionScope.account==null}">
                <option value="English"
                >English</option>
                <option value="Chinese">Chinese</option>
            </c:if>
            <c:if test="${sessionScope.account!=null}">
                <c:if test="${sessionScope.account.languagePreference=='English'}">
                    <option value="English"
                    >English</option>
                    <option value="Chinese">Chinese</option>
                </c:if>
                <c:if test="${sessionScope.account.languagePreference=='Chinese'}">
                    <option value="Chinese">Chinese</option>
                    <option value="English"
                    >English</option>
                </c:if>
            </c:if>
        </select>
        </td>
    </tr>
    <tr>
        <td>Favourite Category:</td>
        <td><select name="favouriteCategoryId">
            <c:if test="${sessionScope.account!=null}">
                <option value="${sessionScope.account.favouriteCategoryId}" >
                        ${sessionScope.account.favouriteCategoryId}</option>
                <c:forEach var="category" items="${sessionScope.categories}">
                    <c:choose>
                        <c:when test="${sessionScope.account.favouriteCategoryId.equals(category.categoryId)}"></c:when>
                        <c:otherwise>   <option value="${category.categoryId}">${category.categoryId}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
            <c:if test="${sessionScope.account==null}">
                <c:forEach var="category" items="${sessionScope.categories}">
                    <option value="${category.categoryId}">${category.categoryId}</option>
                </c:forEach>
            </c:if>

        </select></td>
    </tr>
    <tr>
        <td>Enable MyList</td>
        <td> <input type="checkbox" id="listOption" value="listOption"></td>
    </tr>
    <tr>
        <td>Enable MyBanner</td>
        <td> <input type="checkbox" id="bannerOption" value="bannerOption"></td>
    </tr>

</table>
