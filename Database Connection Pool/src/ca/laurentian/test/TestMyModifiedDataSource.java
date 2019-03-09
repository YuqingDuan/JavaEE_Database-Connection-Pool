package ca.laurentian.test;

import ca.laurentian.util.JDBCUtil;
import ca.laurentian.util.MyModifiedDataSource;
import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMyModifiedDataSource {
    @Test
    public void testMyModifiedDataSource() {
        Connection connection = null;
        PreparedStatement ps = null;
        DataSource dataSource = new MyModifiedDataSource();

        try {
            connection = dataSource.getConnection();
            String sql = "insert into account values (null , 'kelindun' , 20)";
            ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            // 归还连接
            JDBCUtil.release(connection, ps);
        }
    }
}
