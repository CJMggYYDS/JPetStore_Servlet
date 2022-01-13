package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.service.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UsernameIsExistServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String name = req.getParameter("username");
        resp.setContentType("text/plain");
        System.out.println("name"+name);
        Account account = new AccountService().getAccount(name);
        if(account !=null)
        {
          out.print("Exist");
        }
        else if(account ==null)
        {
            out.print("Not Exist");
        }
        out.flush();
        out.close();
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }
}
