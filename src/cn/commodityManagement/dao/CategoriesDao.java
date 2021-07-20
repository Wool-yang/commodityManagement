package cn.commodityManagement.dao;

import cn.commodityManagement.domain.Categories;
import cn.commodityManagement.domain.User;
import cn.commodityManagement.utils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoriesDao {
    public List findAll(){
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "select * from categories";
        try {
            List list = (List) queryRunner.query(sql, new BeanListHandler<>(Categories.class));
            return list;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
    public Boolean addCategory(Categories categories){
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "insert into categories(name) values(?)";
        try {
            int num = queryRunner.update(sql, new Object[]{categories.getName()});
            if(num > 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Boolean updateCategory(Categories categories) {
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "update categories set name = ? where id = ?";
        try {
            int num = queryRunner.update(sql, new Object[]{categories.getName(), categories.getId()});
            if(num > 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Boolean deleteCategory(Categories categories) {
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "delete from categories where id = ?";
        try {
            int num = queryRunner.update(sql, new Object[]{categories.getId()});
            if(num > 0)
                return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
