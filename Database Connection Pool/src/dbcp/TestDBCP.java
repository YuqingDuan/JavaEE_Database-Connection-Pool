package dbcp;

import ca.laurentian.util.JDBCUtil;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class TestDBCP {
    @Test
    public void testDBCP01() {
        // 代码方式
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 构建数据源对象
            BasicDataSource dataSource = new BasicDataSource();
            // 连的是什么类型的数据库，访问的是哪个数据库，用户名，密码。。
            // jdbc:mysql://localhost/bank 主协议：子协议 ://本地/数据库
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost/bank");
            dataSource.setUsername("root");
            dataSource.setPassword("Devilhunter9527");

            // 2. 得到连接对象
            conn = dataSource.getConnection();
            String sql = "insert into account values(null , ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "admin");
            ps.setInt(2, 1000);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps);
        }
    }

    @Test
    public void testDBCP02() {
        // 配置文件方式
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1. 构建数据源工厂对象
            BasicDataSourceFactory factory = new BasicDataSourceFactory();
            Properties properties = new Properties();
            InputStream is = new FileInputStream("dbcpconfig.properties");
            properties.load(is);
            DataSource dataSource = factory.createDataSource(properties);

            //2. 得到连接对象
            conn = dataSource.getConnection();
            String sql = "insert into account values(null , ? , ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "liangchaowei");
            ps.setInt(2, 100);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.release(conn, ps);
        }
    }
}
