<%--
  Created by IntelliJ IDEA.
  User: 86176
  Date: 2021/10/28
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="../common/IncludeTop.jsp"%>


    <div id="Welcome">
        <div id="WelcomeContent">
            Welcome to MyPetStore!
        </div>
    </div>

    <div id="Main">
        <div id="Sidebar">
            <div id="SidebarContent">
                <a href="Category?categoryId=FISH"><img src="images/fish_icon.gif" /></a>
                <br/> Saltwater, Freshwater <br/>
                <a href="Category?categoryId=DOGS"><img src="images/dogs_icon.gif" /></a>
                <br /> Various Breeds <br />
                <a href="Category?categoryId=CATS"><img src="images/cats_icon.gif" /></a>
                <br /> Various Breeds, Exotic Varieties <br />
                <a href="Category?categoryId=REPTILES"><img src="images/reptiles_icon.gif" /></a>
                <br /> Lizards, Turtles, Snakes <br />
                <a href="Category?categoryId=BIRDS"><img src="images/birds_icon.gif" /></a>
                <br /> Exotic Varieties
            </div>
        </div>


        <div id="MainImage">
            <div id="MainImageContent">

                <map name="estoremap" id="map">
                    <area alt="Birds" id="birds" coords="72,2,280,250" href="Category?categoryId=BIRDS" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                    <area alt="Fish" id="fish" coords="2,180,72,250" href="Category?categoryId=FISH" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                    <area alt="Dogs" id="dogs"  coords="60,250,130,320" href="Category?categoryId=DOGS" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                    <area alt="Reptiles" id="reptiles" coords="140,270,210,340" href="Category?categoryId=REPTILES" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                    <area alt="Cats" id="cats" coords="225,240,295,310" href="Category?categoryId=CATS" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)"/>
                    <area alt="Birds" id="bird" coords="280,180,350,250" href="Category?categoryId=BIRDS" shape="rect" onmouseover="showInform(alt);" onmouseout="hiddenInform(event)" />
                </map>

                <img height="355" src="images/splash.gif" align="middle" usemap="#estoremap" width="350" />
            </div>
        </div>
        <div id="inform" class="inform" style="display: none" ></div>
        <div id="Separator">&nbsp;</div>
    </div>


<%@ include file="../common/IncludeBottom.jsp"%>