package org.petstore.webServlet;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.petstore.domain.Product;
import org.petstore.service.CatelogService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/AutoFindProductAjax")
public class AutoFindProductAjax extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取搜索框输入的内容
        String keyword = request.getParameter("keyword");
        //向server层调用相应的业务
        CatelogService service = new CatelogService();
        List<Product> productList = service.searchProductList(keyword);

        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        request.setCharacterEncoding("utf-8");

        JSONArray searchArray=new JSONArray();
        for (Product product : productList) {
            JSONObject searchObj = new JSONObject();
            searchObj.put("name", product.getName());
            searchArray.add(searchObj);
        }

        String searchStr=searchArray.toJSONString();

        System.out.println(searchStr);
        PrintWriter out=response.getWriter();
        out.print(searchStr);

        out.flush();
        out.close();

    }
}
