package cn.commodityManagement.controller;

import cn.commodityManagement.dao.CategoriesDao;
import cn.commodityManagement.dao.CommodityDao;
import cn.commodityManagement.domain.Categories;
import cn.commodityManagement.domain.Commodity;
import com.alibaba.fastjson.JSON;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "CommodityServlet", value = "/CommodityServlet")
public class CommodityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        CommodityDao dao = new CommodityDao();
        String method = request.getParameter("method");

        if(method.equals("findAll")){
            findAll(request, response, dao);
        }
        else if(method.equals("addCommodity")){
            addCommodity(request, response, dao);
        }
        else if(method.equals("updateCommodity")){
            updateCommodity(request, response, dao);
        }
        else if(method.equals("deleteCommodity")){
            int id = Integer.parseInt(request.getParameter("id"));
            Commodity commodity = new Commodity(id);
            dao.deleteCommodity(commodity);
            findAll(request, response, dao);
        }
        else if(method.equals("priceSearch")){
            priceSearch(request, response, dao);
        }
        else if(method.equals("nameSearch")){
            String categoryid = request.getParameter("categoryid");
            String name = request.getParameter("name");

            String json = JSON.toJSONString(dao.nameSearch(Integer.parseInt(categoryid), name));
            response.getWriter().write(json);
        }
    }

    private void priceSearch(HttpServletRequest request, HttpServletResponse response, CommodityDao dao) throws IOException {
        String categoryid = request.getParameter("categoryid");
        int minPrice = Integer.parseInt(request.getParameter("minPrice"));
        int maxPrice = Integer.parseInt(request.getParameter("maxPrice"));

        String json = JSON.toJSONString(dao.priceSearch(Integer.parseInt(categoryid), minPrice, maxPrice));
        response.getWriter().write(json);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response, CommodityDao dao) throws IOException {
        String categoryid = request.getParameter("categoryid");
        String json = null;
        json = JSON.toJSONString(dao.findAll(Integer.parseInt(categoryid)));
        response.getWriter().write(json);
    }

    private void updateCommodity(HttpServletRequest request, HttpServletResponse response, CommodityDao dao) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String keyWord = request.getParameter("keyWord");
        String describe = request.getParameter("describe");
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        Commodity commodity = new Commodity(id, name, price, describe, keyWord, categoryid);
        dao.updateCommodity(commodity);
        findAll(request, response, dao);
    }

    private void addCommodity(HttpServletRequest request, HttpServletResponse response, CommodityDao dao) throws IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String keyWord = request.getParameter("keyWord");
        String describe = request.getParameter("describe");
        int categoryid = Integer.parseInt(request.getParameter("categoryid"));
        Commodity commodity = new Commodity(name, price, describe, keyWord, categoryid);
        dao.addCommodity(commodity);
        findAll(request, response, dao);
    }
}
