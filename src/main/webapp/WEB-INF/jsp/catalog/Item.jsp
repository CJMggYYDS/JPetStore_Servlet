<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/10/29
  Time: 11:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
    <a href="Product?productID=${sessionScope.item.product.productId}">Return
        to Product</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <td>${sessionScope.item.product.description}</td>
        </tr>
        <tr>
            <td><b> ${sessionScope.item.itemId} </b></td>
        </tr>
        <tr>
            <td><b><font size="4"> ${sessionScope.item.attribute1}
                ${sessionScope.item.attribute2} ${sessionScope.item.attribute3}
                ${sessionScope.item.attribute4} ${sessionScope.item.attribute5}
                ${sessionScope.product.name} </font></b></td>
        </tr>
        <tr>
            <td>${sessionScope.product.name}</td>
        </tr>
        <tr>
            <td><c:if test="${sessionScope.item.quantity <= 0}">
                Back ordered.
            </c:if> <c:if test="${sessionScope.item.quantity > 0}">
                ${sessionScope.item.quantity} in stock.
            </c:if></td>
        </tr>
        <tr>
            <td><fmt:formatNumber value="${sessionScope.item.listPrice}"
                                  pattern="$#,##0.00" /></td>
        </tr>

        <tr>
            <td>
                <a class="Button" href="addItemToCart?workingItemId=${sessionScope.item.itemId}">Add to Cart</a>
            </td>
        </tr>
    </table>
</div>

<%@include file="../common/IncludeBottom.jsp"%>