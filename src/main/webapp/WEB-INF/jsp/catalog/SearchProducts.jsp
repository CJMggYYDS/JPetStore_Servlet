<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/10/31
  Time: 13:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <table>
        <tr>
            <th>&nbsp;</th>
            <th>Product ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var="product" items="${sessionScope.productList}">
            <tr>
                <td>
                    <a href="Product?productID=${product.productId}">
                    ${product.description}
                    </a>
               </td>
                <td>
                    <b>
                        <a href="Product?productID=${product.productId}">
                    <font color="BLACK"> ${product.productId} </font>
                        </a>
                    </b>
                </td>
                <td>${product.name}</td>
            </tr>
        </c:forEach>
        <tr>
            <td></td>
        </tr>

    </table>

</div>
<%@include file="../common/IncludeBottom.jsp"%>