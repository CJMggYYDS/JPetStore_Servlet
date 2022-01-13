package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Item;
import org.petstore.domain.Product;
import org.petstore.service.CatelogService;
import org.petstore.service.LogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", value = "/ProductServlet")
public class ProductServlet extends HttpServlet {
    private static final String PRODUCT = "/WEB-INF/jsp/catalog/Product.jsp";
    private String PTRDUCTID;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PTRDUCTID = request.getParameter("productID");
        System.out.println(PTRDUCTID);
        CatelogService catelogService = new CatelogService();
        List<Item> items = catelogService.getItemListByProduct(PTRDUCTID);
        Product product=catelogService.getProduct(PTRDUCTID);
        System.out.println(items);
        HttpSession session = request.getSession();
        session.setAttribute("items",items);

        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 查看产品 " + product;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        request.getRequestDispatcher(PRODUCT).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }
}
