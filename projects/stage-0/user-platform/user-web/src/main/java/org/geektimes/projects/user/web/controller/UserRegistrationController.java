package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("/register")
public class UserRegistrationController implements PageController {

    // 通过自研 Web MVC 框架实现（可以自己实现）一个用户注册，forward 到一个成功的页面（JSP 用法）/register
    @GET
    @POST
    @Path("/") // /user/register -> UserRegistrationController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "registered.jsp";
    }
//
//    @GET
//    @POST
//    @Path("/submit_registration") // /user/register -> UserRegistrationController
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//        return "login-form.jsp";
//    }
//
//    @GET
//    @POST
//    @Path("/login") // /user/register -> UserRegistrationController
//    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
//        return "login-form.jsp";
//    }
}
