package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Category;
import org.petstore.domain.Product;
import org.petstore.persistence.impl.CategoryDAOImpl;
import org.petstore.persistence.impl.ProductDAOImpl;
import org.petstore.service.CatelogService;
import org.petstore.service.LogService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CatagoryServlet", value = "/CatagoryServlet")
public class CatagoryServlet extends HttpServlet {
    private static final String Catagory = "/WEB-INF/jsp/catalog/Category.jsp";
    private String CATEGORYID;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CATEGORYID =request.getParameter("categoryId");
       CatelogService catelogService = new CatelogService();
        Category category =catelogService.getCategory(CATEGORYID);
        System.out.println(category);
        List<Product> products = catelogService.getProductListByCategory(CATEGORYID);
        System.out.println(products);
        HttpSession session = request.getSession();
        session.setAttribute("category",category);
        session.setAttribute("products",products);

        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到商品种类 " + category;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        request.getRequestDispatcher(Catagory).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doGet(request,response);
    }

}
