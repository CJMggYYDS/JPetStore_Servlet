package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.CartItem;
import org.petstore.domain.Item;
import org.petstore.service.CatelogService;
import org.petstore.service.LogService;
import org.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AddItemToCartServlet extends HttpServlet {
    private static final String CART="/WEB-INF/jsp/cart/Cart.jsp";

    private String workingItemId;
    private Cart cart;

    private CatelogService catelogService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId=req.getParameter("workingItemId");
        Account account;
        HttpSession session=req.getSession();
        cart=(Cart)session.getAttribute("cart");
        account=(Account) session.getAttribute("account");
        if(cart==null){
            cart=new Cart();
        }
        if(cart.containsItemId(workingItemId)){
            cart.incrementQuantityByItemId(workingItemId);
            catelogService=new CatelogService();
            new OrderService().updateItemQuantity(account,cart.getCarItem(workingItemId));
            if(account != null){
                HttpServletRequest httpRequest= (HttpServletRequest) req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                Item item = catelogService.getItem(workingItemId);
                String logInfo = logService.logInfo(" ") + strBackUrl + " " + item + "数量+1 ";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
        }
        else{

            catelogService=new CatelogService();
            boolean isInStock=catelogService.isItemInStock(workingItemId);
            Item item=catelogService.getItem(workingItemId);
            cart.addItem(item,isInStock);
            System.out.println(cart.getCarItem(workingItemId).getItem().getItemId());
            new OrderService().insertIntoCartShop(account,cart.getCarItem(workingItemId));
            session.setAttribute("cart",cart);

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 添加物品 " + item + " 到购物车";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
        }
        req.getRequestDispatcher(CART).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
