package ca.laurentian.test;

import ca.laurentian.util.MyDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestMyDataSource {
    @Test
    public void testMyDataSource() {
        Connection conn = null;
        PreparedStatement ps = null;
        MyDataSource dataSource = new MyDataSource();
        try {
            conn = dataSource.getConnection();
            String sql = "insert into account values (null , 'xilali' , 10)";
            ps = conn.prepareStatement(sql);
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
            dataSource.addBack(conn);
        }
    }
}
