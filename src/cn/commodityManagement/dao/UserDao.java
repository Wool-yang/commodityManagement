package cn.commodityManagement.dao;

import cn.commodityManagement.domain.Commodity;
import cn.commodityManagement.domain.User;
import cn.commodityManagement.utils.DBCPUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDao {
    public Boolean check(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DBCPUtils.getDataSource());
        String sql = "select * from user where name = ? and password = ?";
        List list = (List) queryRunner.query(sql, new BeanListHandler<>(User.class), new Object[] {user.getName(), user.getPassword()});
        if(!list.isEmpty())
            return true;
        else
            return false;
    }
}
