package ca.laurentian.util;

import ca.laurentian.wrapper.ConnectionWrapper;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 问题：
 * 1. 需要额外记住 addBack方法
 * 2. 单例(先不考虑改进这一块)
 * 3. 无法面向接口编程
 * UserDao dao = new UserDaoImpl();
 * dao.insert();
 * <p>
 * DataSource dataSource = new MyDataSource();
 * dataSource.addBack(conn); // 报错
 */
public class MyModifiedDataSource implements DataSource {
    List<Connection> connList = new ArrayList<>();

    public MyModifiedDataSource() {
        for (int i = 0; i < 10; i++) {
            Connection conn = JDBCUtil.getConn();
            connList.add(conn);
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connList.size() == 0) {
            for (int i = 0; i < 5; i++) {
                Connection conn = JDBCUtil.getConn();
                connList.add(conn);
            }
        }
        // remove(0)移除第一个，并且返回第一个
        Connection conn = connList.remove(0);
        // 在把这个对象抛出去的时候，对这个对象进行包装
        Connection connection = new ConnectionWrapper(conn, connList);
        return connection;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
