package org.petstore.webServlet;

import org.petstore.domain.Product;
import org.petstore.service.CatelogService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ShowInformServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String categoryId = req.getParameter("categoryId");
        System.out.println("categotyId= "+categoryId);
        CatelogService catelogService = new CatelogService();
        List<Product> productList = catelogService.getProductListByCategory(categoryId);
        String response = "ProductID            Name\n";
        int i = 0 ;
        for(;i<productList.size();i++)
        {
            Product product = productList.get(i);
            response+=product.getProductId()+"        "+product.getName()+"\n";

        }
        System.out.println(response);
        resp.setContentType("text/xml");
        PrintWriter out = resp.getWriter();
        out.write(response);
    }
    @Override
    protected void doPost(HttpServletRequest request , HttpServletResponse response) throws IOException {
        doGet(request,response);
    }
}
