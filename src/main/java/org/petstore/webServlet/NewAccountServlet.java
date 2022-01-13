package org.petstore.webServlet;

import org.petstore.domain.Account;
import org.petstore.service.AccountService;
import org.petstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewAccountServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String NEWACCOUNTFORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";

    private Account account;
    private Account account1;
    private AccountService accountService;
    private String message1;
    private String message2;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        accountService = new AccountService();

        //获得输入的验证码值
        String value1 = request.getParameter("verificationCode");
        /*获取图片的值*/
        String value2 = (String) session.getAttribute("code");
        session.removeAttribute("code");
        Boolean isSame = false;
        /*对比两个值（字母不区分大小写）*/
        if (value2.equalsIgnoreCase(value1)) {
            System.out.println("sss");
            isSame = true;
        }

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repeatPassword = request.getParameter("repeatPassword");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");
        String languagePreference = request.getParameter("languagePreference");
        String favouriteCategoryId = request.getParameter("favouriteCategoryId");
        String listOption = request.getParameter("listOption");
        String bannerOption = request.getParameter("bannerOption");

        System.out.println("1"+languagePreference+favouriteCategoryId+listOption+bannerOption);
        account1 = new Account();
        account1.setUsername(username);
        account1.setPassword(password);
        account1.setFirstName(firstName);
        account1.setLastName(lastName);
        account1.setEmail(email);
        account1.setPhone(phone);
        account1.setAddress1(address1);
        account1.setAddress2(address2);
        account1.setCity(city);
        account1.setState(state);
        account1.setZip(zip);
        account1.setCountry(country);
        account1.setLanguagePreference(languagePreference);
        account1.setFavouriteCategoryId(favouriteCategoryId);
        account1.setListOption(Boolean.parseBoolean(listOption));
        account1.setBannerOption(Boolean.parseBoolean(bannerOption));
        System.out.println(username);
        System.out.println(1);
        System.out.println(password);
        if (isSame) {
            account = accountService.getAccount(account1.getUsername());
            System.out.println(account);
            if(account == null)
            {
                if(username.equals(""))
                {
                    if(password.equals(""))
                    {
                        message2=null;
                        message1="Username and password are empty!";
                        session.setAttribute("message1",message1);
                        session.setAttribute("message2",message2);
                    }
                    else {
                        message2 = null;
                        message1 = "Username is empty!";
                        session.setAttribute("message1", message1);
                        session.setAttribute("message2",message2);
                    }
                }
                else if(password.equals(""))
                {
                    message2=null;
                    message1="Password is empty!";
                    session.setAttribute("message1",message1);
                    session.setAttribute("message2",message2);
                }
                else if(!password.equals("")&&password.equals(repeatPassword))
                {
                    accountService.insertAccount(account1);

                    if(account1 != null){
                        HttpServletRequest httpRequest= request;
                        String strBackUrl = "http://" + request.getServerName() + ":" + request.getServerPort()
                                + httpRequest.getContextPath() + httpRequest.getServletPath() + "?" + (httpRequest.getQueryString());

                        LogService logService = new LogService();
                        String logInfo = logService.logInfo(" ") + strBackUrl + " 注册新账号";
                        logService.insertLogInfo(account1.getUsername(), logInfo);
                    }

                    request.getRequestDispatcher(MAIN).forward(request,response);
                }
                else {
                    message2=null;
                    message1 = "Passwords are not the same!";
                    session.setAttribute("message1",message1);
                    session.setAttribute("message2",message2);
                }

            }
            else {
                message2=null;
                message1 = "The username has been registered!";
                session.setAttribute("message1",message1);
                session.setAttribute("message2",message2);
            }
        }
        else {
            message1=null;
            message2 = "VerificationCode is wrong!";
            session.setAttribute("message1",message1);
            session.setAttribute("message2",message2);
        }
        request.getRequestDispatcher(NEWACCOUNTFORM).forward(request,response);
    }


}
