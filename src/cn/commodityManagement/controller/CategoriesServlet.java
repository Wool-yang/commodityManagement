package cn.commodityManagement.controller;

import cn.commodityManagement.dao.CategoriesDao;
import cn.commodityManagement.domain.Categories;
import com.alibaba.fastjson.JSON;
import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonString;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "CategoriesServlet", value = "/CategoriesServlet")
public class CategoriesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        CategoriesDao dao = new CategoriesDao();
        String method = request.getParameter("method");
        if(method.equals("findAll")){
            findAll(response, dao);
        }
        else if(method.equals("addCategory")){
            addCategory(request, response, dao);
        }
        else if(method.equals("updateCategory")){
            updateCategory(request, response, dao);
        }
        else if(method.equals("deleteCategory")){
            int id = Integer.parseInt(request.getParameter("id"));
            Categories categories = new Categories(id);
            dao.deleteCategory(categories);
            findAll(response, dao);
        }
    }

    private void updateCategory(HttpServletRequest request, HttpServletResponse response, CategoriesDao dao) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Categories categories = new Categories(id, name);
        dao.updateCategory(categories);
        findAll(response, dao);
    }

    private void addCategory(HttpServletRequest request, HttpServletResponse response, CategoriesDao dao) throws IOException {
        String name = request.getParameter("name");
        Categories categories = new Categories(name);
        dao.addCategory(categories);

        findAll(response, dao);
    }

    private void findAll(HttpServletResponse response, CategoriesDao dao) throws IOException {
        String json = JSON.toJSONString(dao.findAll());
        response.getWriter().write(json);
    }
}
