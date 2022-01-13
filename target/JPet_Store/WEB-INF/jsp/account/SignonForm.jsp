<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/11/1
  Time: 21:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="../common/IncludeTop.jsp"%>



<div id="Catalog">
    <form action="login"method="post">
        <p>Please enter your username and password.</p>
        <script>
            <%-- 增加时间戳来更换验证码图片 --%>
            window.onload=function(){
                document.getElementById("img").onclick=function(){
                    this.src = "verify?time=" + new Date().getTime();
                }
            }
        </script>
        <p>
            Username:<input type="text" name="username" /><br /><br />
            Password:<input type="password" name="password" /><br /> <font color='red'>${sessionScope.message}</font><br />
            CheckCode:<input type="text" name="checkcode" /><br/> <font color='red'>${sessionScope.errorcode}</font><br/>
            <img id="img" src="verify"  alt="">

            <!--  Validation Code:<input type="text" name="validationCode"/>
    <img src="validation" id="imgservlet" onclick="reloadImage()"/>
    -->
            <!--  <input type="button" value="changeImage" onclick="reloadImage()" id="btn">-->
        </p>
        <input type="submit" name="signon" value="Login" />
    </form>

    Need a user name and password? <a href="account?pre=register">Register
    Now!</a>

</div>
<%@include file="../common/IncludeBottom.jsp"%>