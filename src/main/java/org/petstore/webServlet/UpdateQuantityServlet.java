package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.CartItem;
import org.petstore.service.OrderService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UpdateQuantityServlet extends HttpServlet {
    Cart cart;
    Account account ;
    int  quantity;
    String itemId;
    CartItem cartItem;
    String flag;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/xml");
        PrintWriter out = resp.getWriter();
        quantity = Integer.parseInt(req.getParameter("quantity"));
        itemId = req.getParameter("itemId");
        flag = req.getParameter("flag");
        itemId = itemId.trim();
        System.out.println(flag+itemId);
        HttpSession session = req.getSession();
        cart = (Cart) session.getAttribute("cart");
        // System.out.println("cart"+cart);
        account = (Account)session.getAttribute("account");
        OrderService orderService = new OrderService();

        Iterator<CartItem> cartItemIterator = cart.getAllCartItems();

        while (cartItemIterator.hasNext()) {
            //产品数量增加
            cartItem = (CartItem) cartItemIterator.next();
            String Id = cartItem.getItem().getItemId();

            try {
                if(itemId.equals(Id))
                {
                    System.out.println(0000);
                    if(flag.equals("sub")) {
                        quantity--;
                    }
                    if(flag.equals("add"))
                        quantity++;
                    if(quantity<1)
                    {
                        cartItemIterator.remove();
                        if(account!=null)
                            orderService.deleteItemFromCartShop(account,cartItem);

                        out.write("d"+"/"+cart.getSubTotal());
                        return;
                    }
                    else {
                        cartItem.setQuantity(quantity);
                        if(account!=null)
                            orderService.updateItemQuantity(account,cartItem);
                        System.out.println(cartItem.getItem().getItemId()+"  "+cartItem.getQuantity());
                    }
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        session.setAttribute("cart", cart);


        Cart cart2 = (Cart)session.getAttribute("cart");
        String quantityAll = "";

        quantityAll += quantity + "/" + cartItem.getTotal() + "/" + cart2.getSubTotal();
        System.out.println(quantityAll+"quantityAll");

        out.write(quantityAll);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }
}
