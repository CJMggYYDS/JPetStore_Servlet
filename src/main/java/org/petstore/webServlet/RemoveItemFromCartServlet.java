package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.CartItem;
import org.petstore.domain.Item;
import org.petstore.service.LogService;
import org.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveItemFromCartServlet extends HttpServlet {
    private static final String CART="/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR="/WEB-INF/jsp/common/Error.jsp";

    private String workingItemId;
    private Cart cart;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId=req.getParameter("workingItemId");
        HttpSession session=req.getSession();
        Account account =(Account)session.getAttribute("account");
        cart=(Cart)session.getAttribute("cart");

        CartItem item=cart.removeItemById(workingItemId);
        if(item==null)
        {
            session.setAttribute("message0","Attempted to remove null CartItem from Cart.");
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 物品为空，不能移除";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(ERROR).forward(req,resp);
        }
        else{
            new OrderService().deleteItemFromCartShop(account,item);

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " " + item + " 已从购物车中移除";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(CART).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }
}
