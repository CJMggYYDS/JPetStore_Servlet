<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/10/28
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="StyleSheet" href="css/jpetstore.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="css/mouseEvent.css" type="text/css" media="screen">
    <link rel="StyleSheet" href="css/searchProduct.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="css/order.css" type="text/css" media="screen" />
    <link rel="StyleSheet" href="css/checkAccount.css" type="text/css" media="screen">

    <meta name="generator"
          content="HTML Tidy for Linux/x86 (vers 1st November 2002), see www.w3.org" />
    <title>JPetStore</title>
    <meta content="text/html; charset=windows-1252"
          http-equiv="Content-Type" />
    <meta http-equiv="Cache-Control" content="max-age=0" />
    <meta http-equiv="Cache-Control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="Expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="Pragma" content="no-cache" />

    <!--CJM改动-->
    <script type="text/javascript" src="https://cdn.bootcdn.net/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script type="text/javascript" src="js/showCategory.js"></script>
    <script type="text/javascript" src="js/checkCode.js"></script>
    <script type="text/javascript" src="js/checkAccount.js"></script>
    <script type="text/javascript" src="js/updateCart.js"></script>
</head>

<body>

<div id="Header">

    <div id="Logo">
        <div id="LogoContent">
            <a href="main"><img src="images/logo-topbar.gif" /></a>
        </div>
    </div>

    <div id="Menu">
        <div id="MenuContent">
            <a href="viewCart"><img align="middle" name="img_cart"
                                         src="images/cart.gif" /></a> <img align="middle"
                                                                           src="images/separator.gif" />
            <c:if test="${sessionScope.account==null}">
                <a href="account?pre=login">Sign In</a>
            </c:if>
            <c:if test="${sessionScope.account!=null}">
                <a href="account?pre=Signout">Sign
                    Out</a> <img align="middle" src="images/separator.gif" /> <a
                    href="account?pre=edit">My Account</a>
            </c:if>
            <img align="middle"
                 src="images/separator.gif" /> <a href="../help.html">?</a>
        </div>
    </div>

    <!--自动补全的改动——CJM-->
    <div id="Search">
        <div id="SearchContent">
            <form action="search" method="post">
                <input type="text" name="keyword" id="searchInput" size="20" />
                <div id="auto">
                </div>
                <input type="submit" name="searchProducts" value="Search" />
                <script type="text/javascript" src="js/searchProduct.js"></script>
            </form>
        </div>

    </div>
    <!--CJM-->

    <div id="QuickLinks">
        <a href="Category?categoryId=FISH"><img
                src="images/sm_fish.gif" /></a> <img src="images/separator.gif" />
        <a href="Category?categoryId=DOGS"><img
                src="images/sm_dogs.gif" /></a> <img src="images/separator.gif" />
        <a href="Category?categoryId=REPTILES"><img
                src="images/sm_reptiles.gif" /></a> <img
            src="images/separator.gif" />
        <a href="Category?categoryId=CATS"><img
                src="images/sm_cats.gif" /></a> <img src="images/separator.gif" />
        <a href="Category?categoryId=BIRDS"><img
                src="images/sm_birds.gif" /></a>
    </div>

</div>

<div id="Content">