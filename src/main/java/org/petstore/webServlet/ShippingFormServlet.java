package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ShippingFormServlet extends HttpServlet {
    private static final String SHIPPING_FORM="/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String SIGN_ON_FROM="/WEB-INF/jsp/account/SignonForm.jsp";

    private Account account;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        account=(Account)session.getAttribute("account");
        if(account==null)
        {
            req.getRequestDispatcher(SIGN_ON_FROM).forward(req,resp);
        }
        else
        {
            Account account = (Account)session.getAttribute("account");

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到修改地址界面";
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
