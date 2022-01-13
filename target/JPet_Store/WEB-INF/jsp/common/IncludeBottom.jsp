<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/10/28
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
</div>

<div id="Footer">

    <div id="PoweredBy">&nbsp<a href="www.csu.edu.cn">www.csu.edu.cn</a>
    </div>

    <div id="Banner">
        <c:if test="${sessionScope.account!=null}">
            ${sessionScope.account.bannerName}
        </c:if>
    </div>

</div>

</body>
</html>
