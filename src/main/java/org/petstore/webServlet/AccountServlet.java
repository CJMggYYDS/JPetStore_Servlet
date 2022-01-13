package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.domain.Cart;
import org.petstore.domain.Category;
import org.petstore.service.CatelogService;
import org.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountServlet extends HttpServlet {
    private static final String NEW_ACCOUNT = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String EDIT_ACCOUNT = "/WEB-INF/jsp/account/EditAccountForm.jsp";
    private static final String SIGNON = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pre = req.getParameter("pre");
        if(pre.equals("register")) {
            List<Category> categories = new CatelogService().getCategoryList();
            System.out.println(categories);
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");
            session.setAttribute("categories",categories);

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到注册新账号界面";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }

            req.getRequestDispatcher(NEW_ACCOUNT).forward(req, resp);
        }
        else if(pre.equals("login"))
        {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 用户进入登录界面";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            req.getRequestDispatcher(SIGNON).forward(req,resp);
            System.out.println(111);
        }
        else if(pre.equals("edit"))
        {
            List<Category> categories = new CatelogService().getCategoryList();
            System.out.println(categories);
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");
            session.setAttribute("categories",categories);

            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 跳转到编辑账号信息界面";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }

            req.getRequestDispatcher(EDIT_ACCOUNT).forward(req,resp);
        }
        else {
            HttpSession session = req.getSession();
            Account account = (Account) session.getAttribute("account");
            if(account != null){
                HttpServletRequest httpRequest= req;
                String strBackUrl = "http://" + req.getServerName() + ":" + req.getServerPort()
                        + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                LogService logService = new LogService();
                String logInfo = logService.logInfo(" ") + strBackUrl + " 退出账号";
                logService.insertLogInfo(account.getUsername(), logInfo);
            }
            session.removeAttribute("account");
            Cart cart=(Cart) session.getAttribute("cart");
            cart=null;
            session.setAttribute("cart",cart);
            req.getRequestDispatcher(MAIN).forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

}
