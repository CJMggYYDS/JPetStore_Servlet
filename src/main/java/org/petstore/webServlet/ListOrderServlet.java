package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Order;
import org.petstore.service.LogService;
import org.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOrderServlet extends HttpServlet {
    private static final String VIEW_LIST_ORDERS="/WEB-INF/jsp/order/ListOrders.jsp";

    private String username;
    private OrderService orderService;
    private List<Order> orderList=new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username=req.getParameter("username");
        orderService=new OrderService();
        orderList=orderService.getOrderByUsername(username);

        HttpSession session=req.getSession();
        session.setAttribute("orderList",orderList);

        Account account=(Account)session.getAttribute("account");
        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 查看订单 " + orderList;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }

        req.getRequestDispatcher(VIEW_LIST_ORDERS).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
