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

public class ConfirmOrderServlet extends HttpServlet {
    private static final String CONFIRM_ORDER_FROM="/WEB-INF/jsp/order/ConfirmOrder.jsp";
    private static final String SHIPPING_FORM="/WEB-INF/jsp/order/ShippingForm.jsp";

    private String shippingAddressRequired;
    private Order order;
    private OrderService orderService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        shippingAddressRequired=req.getParameter("shippingAddressRequired");
        order=new Order();

        HttpSession session=req.getSession();
        order=(Order)session.getAttribute("order");
        Account account=(Account)session.getAttribute("account");

        if(shippingAddressRequired==null)
        {
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 确认生成订单 ";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(CONFIRM_ORDER_FROM).forward(req,resp);
        }
        else
        {
            shippingAddressRequired=null;
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 更改收货地址";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(SHIPPING_FORM).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
