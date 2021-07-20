package cn.commodityManagement.dao;

import cn.commodityManagement.domain.Categories;
import cn.commodityManagement.domain.Commodity;
import cn.commodityManagement.domain.User;
import cn.commodityManagement.utils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CommodityDao {
    public List findAll(int categoryid){
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "select * from commodity where `categoryid` = ?";
        try {
            List list = (List) queryRunner.query(sql, new BeanListHandler<>(Commodity.class), new Object[]{categoryid});
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List priceSearch(int categoryid, int minPrice, int maxPrice){
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "select * from commodity where `categoryid` = ? and (`price` > ? and `price` < ?)";
        try {
            List list = (List) queryRunner.query(sql, new BeanListHandler<>(Commodity.class), new Object[]{categoryid, minPrice, maxPrice});
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Boolean addCommodity(Commodity commodity){
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "insert into commodity(`name`, `price`, `describe`, `keyWord`, `categoryid`) values(?, ?, ?, ?, ?)";
        try {
            int num = queryRunner.update(sql, new Object[]{commodity.getName(), commodity.getPrice(), commodity.getDescribe(), commodity.getKeyWord(), commodity.getCategoryid()});
            if(num > 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Boolean updateCommodity(Commodity commodity) {
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "update commodity set `name` = ?, `price` = ?, `describe` = ?, `keyWord` = ?, `categoryid` = ? where `id` = ?";
        try {
            int num = queryRunner.update(sql, new Object[]{commodity.getName(), commodity.getPrice(), commodity.getDescribe(), commodity.getKeyWord(), commodity.getCategoryid(), commodity.getId()});
            if(num > 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Boolean deleteCommodity(Commodity commodity) {
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "delete from commodity where `id` = ?";
        try {
            int num = queryRunner.update(sql, new Object[]{commodity.getId()});
            if(num > 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List nameSearch(int categoryid, String name) {
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "select * from commodity where `categoryid` = ? and `name` = ?";
        try {
            List list = (List) queryRunner.query(sql, new BeanListHandler<>(Commodity.class), new Object[]{categoryid, name});
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
