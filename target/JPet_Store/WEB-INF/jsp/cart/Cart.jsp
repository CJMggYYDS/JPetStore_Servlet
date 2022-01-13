<%@ include file="../common/IncludeTop.jsp"%>

<div id="BackLink">
    <a href="main">Return to Main Menu</a>
</div>
<script>


</script>
<div id="Cart">
    <h2>Shopping Cart</h2>
        <table>
            <tr>
                <th><b>Item ID</b></th>
                <th><b>Product ID</b></th>
                <th><b>Description</b></th>
                <th><b>In Stock?</b></th>
                <th><b>Quantity</b></th>
                <th><b>List Price</b></th>
                <th><b>Total Cost</b></th>
                <th>&nbsp;</th>
            </tr>

            <c:if test="${sessionScope.cart.numberOfItems==0}">
                <tr>
                    <td colspan="8"><b>Your cart is empty.</b></td>
                </tr>
            </c:if>
            <c:forEach var="cartItem" items="${sessionScope.cart.cartItems}">
                <tr>
                    <td >
                        <a href="Item?itemId=${cartItem.item.itemId}">${cartItem.item.itemId}</a>
                    </td>
                    <td>
                            ${cartItem.item.product.productId}
                    </td>
                    <td>
                        ${cartItem.item.attribute1} ${cartItem.item.attribute2}
                        ${cartItem.item.attribute2} ${cartItem.item.attribute4}
                        ${cartItem.item.attribute5} ${cartItem.item.product.name}
                    </td>
                    <td>
                        ${cartItem.inStock}
                    </td>
                    <td valign="top">
<%--                        添加了div,span等标签---By XY--%>
                        <div>
                            <span>
                                <button  id="sub" value="-" style="display:inline;background-repeat:no-repeat;width: 30px; height: 30px; border-radius:50%;border: none" >-</button>
                            </span>
                            <span>
                                <input type="text"  style="display:inline;top:5px;width: 80px;height: 30px" id="quantity" name="${cartItem.item.itemId}" value="${cartItem.quantity}">
                            </span>
                            <span>
                               <button  id="add" value="+" style="display:inline;background-repeat:no-repeat;width: 30px; height: 30px; border-radius:50%;border: none" >+</button>
                            </span>
                        </div>
                    </td>
                    <td>
                        <fmt:formatNumber value="${cartItem.item.listPrice}" pattern="$#,##0.00"/>
                    </td>
                    <td>
                        <label id="total"><fmt:formatNumber value="${cartItem.total}" pattern="$#,##0.00" /></label>

                    </td>
                    <td>
                        <a class="Button" href="removeItemFromCart?workingItemId=${cartItem.item.itemId}">Remove</a>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7">
                    Sub Total: <label id="subtotal"><fmt:formatNumber value="${sessionScope.cart.subTotal}" pattern="$#,##0.00" /></label>

                </td>
                <td> </td>
            </tr>
        </table>

    <c:if test="${sessionScope.cart.numberOfItems > 0}">
        <a class="Button" href="newOrderForm">Proceed to CheckOut</a>
    </c:if>
</div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>