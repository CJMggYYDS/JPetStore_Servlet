package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Order;
import org.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingAddressServlet extends HttpServlet {
    private static final String CONFIRM_ORDER_FROM="/WEB-INF/jsp/order/ConfirmOrder.jsp";

    private Order order;
    private String shipToFirstName;
    private String shipToLastName;
    private String shipAddress1;
    private String shipAddress2;
    private String shipCity;
    private String shipState;
    private String shipZip;
    private String shipCountry;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        shipToFirstName=req.getParameter("shipToFirstName");
        shipToLastName=req.getParameter("shipToLastName");
        shipAddress1=req.getParameter("shipAddress1");
        shipAddress2=req.getParameter("shipAddress2");
        shipCity=req.getParameter("shipCity");
        shipState=req.getParameter("shipState");
        shipZip=req.getParameter("shipZip");
        shipCountry=req.getParameter("shipCountry");

        HttpSession session=req.getSession();
        order=(Order)session.getAttribute("order");

        order.setShipToFirstName(shipToFirstName);
        order.setShipToLastName(shipToLastName);
        order.setShipAddress1(shipAddress1);
        order.setShipAddress2(shipAddress2);
        order.setShipCity(shipCity);
        order.setShipState(shipState);
        order.setShipZip(shipZip);
        order.setCourier(shipCountry);

        session.setAttribute("order",order);

        Account account=(Account)session.getAttribute("account");
        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 订单信息录入完成";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }

        req.getRequestDispatcher(CONFIRM_ORDER_FROM).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
