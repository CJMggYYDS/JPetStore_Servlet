package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.CartItem;
import org.petstore.service.AccountService;
import org.petstore.service.LogService;
import org.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class LoginServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private OrderService orderService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderService = new OrderService();
        HttpSession session = req.getSession();
        String code = (String) session.getAttribute("code");
        session.removeAttribute("code");
        AccountService accountService = new AccountService();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkcode = req.getParameter("checkcode");
        String message = null;
        String errorcode=null;
        if(checkcode .equals(code) )
        {
            Account account;
            account = accountService.getAccount(username,password);

            if(account != null)
            {
                session.setAttribute("account",account);
                Cart cart=(Cart)session.getAttribute("cart");
                if(cart==null)
                {
                    cart=new Cart();
                    try {
                        List<CartItem> cartItems = new OrderService().getCartShopItems(account);
                        cart.addItem(cartItems);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    session.setAttribute("cart",cart);
                }
                else {
                    Iterator<CartItem> cartItemIterator = cart.getAllCartItems();
                    while(cartItemIterator.hasNext())
                    {
                        CartItem cartItem= cartItemIterator.next();
                        String itemId=cartItem.getItem().getItemId();
                        orderService.insertIntoCartShop(account,cart.getCarItem(itemId));
                    }
                }
                if(account != null){
                    HttpServletRequest httpRequest= req;
                    String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                            + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                    LogService logService = new LogService();
                    String logInfo = logService.logInfo(" ") + strBackUrl + " 用户登录";
                    logService.insertLogInfo(account.getUsername(), logInfo);
                }
                req.getRequestDispatcher(MAIN).forward(req,resp);
            }
            else {
                if(username == null&&password!=null)
                {
                    message= "username is empty!";
                }
                if(password == null&&username!=null)
                {
                    message = "password is empty!";
                }
                else {
                    message = "username or password is wrong!Please try again!";
                }

                session.setAttribute("message",message);
            }

        }
        else {
            errorcode = "Your checkcode is wrong!Please try again!";

        }session.setAttribute("errorcode",errorcode);
        req.getRequestDispatcher(SIGNON).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
