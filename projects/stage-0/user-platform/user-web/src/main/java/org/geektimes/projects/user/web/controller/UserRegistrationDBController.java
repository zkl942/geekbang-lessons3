package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.util.Random;

@Path("/registerdb")
public class UserRegistrationDBController implements PageController {

    // 通过 Controller -> Service -> Repository 实现（数据库实现）
    @GET
    @POST
    @Path("/") // /user/register -> UserRegistrationController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        Random ran = new Random();

        UserServiceImpl userServiceImpl = new UserServiceImpl();
        User user = new User();
        user.setEmail("asdadsa@gmail.com");
        user.setId(ran.nextLong());
        user.setName("helloworld");
        user.setPassword("randompassword");
        user.setPhoneNumber("12345678909");
        userServiceImpl.register(user);
        return "registereddb.jsp";
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
