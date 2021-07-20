package cn.commodityManagement.controller;

import cn.commodityManagement.dao.UserDao;
import cn.commodityManagement.domain.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String method = request.getParameter("method");
        if(method.equals("login"))
            login(request, response);
        else if(method.equals("getUser"))
            response.getWriter().write(getUser(request));
        else if(method.equals("Logout"))
            Logout(request);
    }

    private void Logout(HttpServletRequest request) {
        request.getSession().removeAttribute("name");
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        User user = new User(name, password);
        UserDao dao = new UserDao();
        try {
            if(dao.check(user)){
                response.getWriter().write("true");
                request.getSession().setAttribute("name", name);
            }
            else {
                response.getWriter().write("false");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private String getUser(HttpServletRequest request){
        return (String) request.getSession().getAttribute("name");
    }
}
