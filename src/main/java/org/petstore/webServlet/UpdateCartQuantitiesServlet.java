package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.CartItem;
import org.petstore.service.CatelogService;
import org.petstore.service.LogService;
import org.petstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;

public class UpdateCartQuantitiesServlet extends HttpServlet {
    private static final String CART="/WEB-INF/jsp/cart/Cart.jsp";
    private String workingItemId;
    private Cart cart;
    private OrderService orderService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        workingItemId=req.getParameter("workingItemId");
        orderService=new OrderService();
        HttpSession session=req.getSession();
        cart=(Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");
        Iterator<CartItem> cartItemIterator=cart.getAllCartItems();
        while(cartItemIterator.hasNext())
        {
            CartItem cartItem= cartItemIterator.next();
            String itemId=cartItem.getItem().getItemId();
            try{
                int quantity=Integer.parseInt(req.getParameter(itemId));
                cart.setQuantityByItemId(itemId,quantity);
                orderService.updateItemQuantity(account,cart.getCarItem(cartItem.getItem().getItemId()));
                if(quantity<1){
                    cartItemIterator.remove();
                    orderService.deleteItemFromCartShop(account,cart.getCarItem(cartItem.getItem().getItemId()));
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        session.setAttribute("cart",cart);

        if(account != null){
            HttpServletRequest httpRequest= req;
            String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 更新购物车商品数量";
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        req.getRequestDispatcher(CART).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
