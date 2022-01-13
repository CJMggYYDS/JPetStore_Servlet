package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.Order;
import org.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderFormServlet extends HttpServlet {
    //
    private static final String NEW_ORDER="/WEB-INF/jsp/order/NewOrder.jsp";//cjm
    private static final String SIGN_ON_FORM="/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";

    private Account account;
    private Order order;
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();
        account=(Account)session.getAttribute("account");
        cart=(Cart)session.getAttribute("cart");
        if(account==null)
        {
            session.setAttribute("message0","You must sign on before attempting to check out.  Please sign on and try checking out again.");
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req,resp);
        }
        else if(cart!=null)
        {
            order=new Order();
            order.initOrder(account,cart);
            session.setAttribute("order",order);

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到新订单页面";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }

            req.getRequestDispatcher(NEW_ORDER).forward(req,resp);
        }
        else
        {
            session.setAttribute("message0","An order could not be created because a cart could not be found.");

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 生成订单页面信息错误";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }

            req.getRequestDispatcher(ERROR).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
