package c3p0;

import ca.laurentian.util.JDBCUtil;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TestC3P0 {
    @Test
    public void testC3P001() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1. 创建datasource
            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            //2. 设置连接数据的信息
            dataSource.setDriverClass("com.mysql.jdbc.Driver");
            //忘记了---> 去以前的代码 ---> jdbc的文档
            dataSource.setJdbcUrl("jdbc:mysql://localhost/bank");
            dataSource.setUser("root");
            dataSource.setPassword("Devilhunter9527");

            //2. 得到连接对象
            conn = dataSource.getConnection();
            String sql = "insert into account values(null , ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "yduan");
            ps.setInt(2, 233);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps);
        }
    }

    @Test
    public void testC3P002() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //就new了一个对象。
            ComboPooledDataSource dataSource = new ComboPooledDataSource();

            //2. 得到连接对象
            conn = dataSource.getConnection();
            String sql = "insert into account values(null , ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "wangwu2");
            ps.setInt(2, 2600);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps);
        }
    }
}
