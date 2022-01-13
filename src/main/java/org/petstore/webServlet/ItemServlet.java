package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Item;
import org.petstore.service.CatelogService;
import org.petstore.service.LogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ItemServlet", value = "/ItemServlet")
public class ItemServlet extends HttpServlet {
    private static final String ITEM = "/WEB-INF/jsp/catalog/Item.jsp";
    private String ITEMID;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ITEMID = request.getParameter("itemId");
        CatelogService catelogService = new CatelogService();
        Item item = catelogService.getItem(ITEMID);
        System.out.println(item);
        HttpSession session = request.getSession();
        session.setAttribute("item",item);

        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 查看具体商品 " + item;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        request.getRequestDispatcher(ITEM).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
