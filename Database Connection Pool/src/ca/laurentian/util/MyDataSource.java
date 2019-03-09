package ca.laurentian.util;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 这是一个自定义的数据库连接池
 * 一开始先往池子里面放10个连接
 * <p>
 * 1. 开始创建10个连接。
 * 2. 来的Java程序(客户端)通过getConnection获取连接
 * 3. 用完之后，使用addBack(Connection conn)归还连接。
 * 4. 扩容。
 */
public class MyDataSource implements DataSource {
    List<Connection> connList = new ArrayList<>();

    public MyDataSource() {
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
        // remove(0)移除第一个,并且返回第一个。
        Connection conn = connList.remove(0);
        return conn;
    }

    /**
     * 用完之后，记得归还。
     *
     * @param conn
     */
    public void addBack(Connection conn) {
        connList.add(conn);
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
