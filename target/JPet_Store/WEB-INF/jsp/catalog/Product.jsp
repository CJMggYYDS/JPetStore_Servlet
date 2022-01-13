<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/10/29
  Time: 11:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
    <a href="Category?categoryId=${sessionScope.items[0].product.categoryId}">Return
        to Category</a>
</div>

<div id="Catalog">

    <h2>${sessionScope.items[0].product.name}</h2>

    <table>
        <tr>
            <th>Item ID</th>
            <th>Product ID</th>
            <th>Description</th>
            <th>List Price</th>
            <th>&nbsp;</th>
        </tr>
<c:forEach var="item" items="${sessionScope.items}">
        <tr>
            <td><a href="Item?itemId=${item.itemId}">${item.itemId}</a></td>
            <td>${item.product.productId}</td>
            <td>${item.attribute1}${item.product.name}</td>
            <td>${item.listPrice}</td>
            <td>
                <a class="Button" href="addItemToCart?workingItemId=${item.itemId}">Add to Cart</a>
            </td>
        </tr>
        <tr>
            <td></td>
        </tr>
</c:forEach>
    </table>
</div>
<%@include file="../common/IncludeBottom.jsp"%>