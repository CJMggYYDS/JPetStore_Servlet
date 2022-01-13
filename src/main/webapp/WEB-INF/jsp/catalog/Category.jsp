<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/10/29
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@include file="../common/IncludeTop.jsp"%>
<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>

<div id="Catalog">

    <h2>${sessionScope.category.name}</h2>

    <table>
        <tr>
            <th>Product ID</th>
            <th>Name</th>
        </tr>
       <c:forEach var="product" items="${sessionScope.products}">
        <tr>
            <td><a href="Product?productID=${product.productId}" name="productID">${product.productId}</a>
            </td>
            <td>${product.name}</td>
        </tr>
          </c:forEach>
    </table>

</div>

<%@ include file="../common/IncludeBottom.jsp"%>
