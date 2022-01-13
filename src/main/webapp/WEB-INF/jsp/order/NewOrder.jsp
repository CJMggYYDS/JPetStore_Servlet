<%@ include file="../common/IncludeTop.jsp"%>

<script type="text/javascript" src="js/order.js"></script>

<div id="order-tab">
    <form action="shippingAddress" method="post">
    <ul id="tab-title">
        <li class="action">Payment Details</li>
        <li>Billing Address</li>
        <li>Shipping Address</li>
    </ul>

    <ul id="tab-content">
        <li class="action" id="content1">
        <div id="Catalog">
                <table>
                    <tr>
                        <th colspan=2>Payment Details</th>
                    </tr>
                    <tr>
                        <td>Card Type:</td>
                        <td>
                            <select>
                                <option value="Visa" selected="selected">Visa</option>
                                <option value="MasterCard">MasterCard</option>
                                <option value="American Express">American Express</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Card Number:</td>
                        <td>
                            <input type="text" name="creditCard" value="999999999999">* Use a fake number!
                        </td>
                    </tr>
                    <tr>
                        <td>Expiry Date (MM/YYYY):</td>
                        <td>
                            <input type="text" name="expiryDate" value="12/03"/>
                        </td>
                    </tr>
                </table>
        </div>
        </li>

        <li id="content2">
        <div id="Catalog1">
            <table>
                <tr>
                    <th colspan=2>Billing Address</th>
                </tr>
                <tr>
                    <td>First name:</td>
                    <td>
                        <input type="text" name="billToFirstName" value="${sessionScope.order.billToFirstName}" />
                    </td>
                </tr>
                <tr>
                    <td>Last name:</td>
                    <td>
                        <input type="text" name="billToLastName" value="${sessionScope.order.billToLastName}" />
                    </td>
                </tr>
                <tr>
                    <td>Address 1:</td>
                    <td>
                        <input type="text" size="40" name="billAddress1" value="${sessionScope.order.billAddress1}" />
                    </td>
                </tr>
                <tr>
                    <td>Address 2:</td>
                    <td>
                        <input type="text" size="40" name="billAddress2" value="${sessionScope.order.billAddress2}" />
                    </td>
                </tr>
                <tr>
                    <td>City:</td>
                    <td>
                        <input type="text" name="billCity" value="${sessionScope.order.billCity}" />
                    </td>
                </tr>
                <tr>
                    <td>State:</td>
                    <td>
                        <input type="text" size="4" name="billState" value="${sessionScope.order.billState}" />
                    </td>
                </tr>
                <tr>
                    <td>Zip:</td>
                    <td>
                        <input type="text" size="10" name="billZip" value="${sessionScope.order.billZip}" />
                    </td>
                </tr>
                <tr>
                    <td>Country:</td>
                    <td>
                        <input type="text" size="15" name="billCountry" value="${sessionScope.order.billCountry}" />
                    </td>
                </tr>

            </table>
        </div>
        </li>

        <li id="content3">
        <div id="Catalog2">
                <table>
                    <tr>
                        <th colspan=2>Shipping Address</th>
                    </tr>

                    <tr>
                        <td>First name:</td>
                        <td><input type="text" name="shipToFirstName" value="${sessionScope.order.shipToFirstName}"/></td>
                    </tr>
                    <tr>
                        <td>Last name:</td>
                        <td><input type="text" name="shipToLastName" value="${sessionScope.order.shipToLastName}"/></td>
                    </tr>
                    <tr>
                        <td>Address 1:</td>
                        <td><input type="text" size="40" name="shipAddress1" value="${sessionScope.order.shipAddress1}"/></td>
                    </tr>
                    <tr>
                        <td>Address 2:</td>
                        <td><input type="text" size="40" name="shipAddress2" value="${sessionScope.order.shipAddress2}"/></td>
                    </tr>
                    <tr>
                        <td>City:</td>
                        <td><input type="text" name="shipCity" value="${sessionScope.order.shipCity}"/></td>
                    </tr>
                    <tr>
                        <td>State:</td>
                        <td><input type="text" size="4" name="shipState" value="${sessionScope.order.shipState}"/></td>
                    </tr>
                    <tr>
                        <td>Zip:</td>
                        <td><input type="text" size="10" name="shipZip" value="${sessionScope.order.shipZip}"/></td>
                    </tr>
                    <tr>
                        <td>Country:</td>
                        <td><input type="text" size="15" name="shipCountry" value="${sessionScope.order.shipCountry}"/></td>
                    </tr>

                </table>
        </div>
        </li>
    </ul>
        <input type="submit" name="newOrder" value="Continue" style="align-self: center;position: absolute;left: 400px"/>
    </form>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>