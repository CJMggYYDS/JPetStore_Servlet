package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.Order;
import org.petstore.service.LogService;
import org.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class OrderServlet extends HttpServlet {
    private static final String VIEW_ORDER="/WEB-INF/jsp/order/ViewOrder.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";

    private Order order;
    private OrderService orderService;
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        order=(Order)session.getAttribute("order");
        cart=(Cart)session.getAttribute("cart");
        Account account = (Account) session.getAttribute("account");
        if(order!=null)
        {
            orderService=new OrderService();
            orderService.insertOrder(order);
            session.setAttribute("order",order);
            //确认后购物车清空
            cart=null;
            new OrderService().deleteAll(account);
            session.setAttribute("cart",cart);
            session.setAttribute("message0","Thank you, your order has been submitted.");
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 查看订单 " + order;
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(VIEW_ORDER).forward(req,resp);
        }
        else
        {
            session.setAttribute("message0","An error occurred processing your order (order was null).");
            req.getRequestDispatcher(ERROR).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
