package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Item;
import org.petstore.domain.Product;
import org.petstore.service.CatelogService;
import org.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchProductsServlet extends HttpServlet {
    private static final String SEARCH = "/WEB-INF/jsp/catalog/SearchProducts.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String KETWORDS = request.getParameter("keyword");
        CatelogService catelogService = new CatelogService();
        List<Product> productList = catelogService.searchProductList(KETWORDS);
        HttpSession session = request.getSession();
        session.setAttribute("productList",productList);

        Account account = (Account)session.getAttribute("account");

        if(account != null){
            HttpServletRequest httpRequest= request;
            String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                    + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

            LogService logService = new LogService();
            String logInfo = logService.logInfo(" ") + strBackUrl + " 查找商品" + "  " + productList;
            logService.insertLogInfo(account.getUsername(), logInfo);
        }
        request.getRequestDispatcher(SEARCH).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
