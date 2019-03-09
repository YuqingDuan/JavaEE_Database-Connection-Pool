package dbutils;

import ca.laurentian.domain.Account;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DBUtils只是帮我们简化了CRUD的代码，但是连接的创建以及获取工作不在它的考虑范围。
 */
public class DBUtilsDemo {
    @Test
    public void testCRUD() throws SQLException, InstantiationException, IllegalAccessException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        QueryRunner queryRunner = new QueryRunner(dataSource);
        // insert
        queryRunner.update("insert into account values (null, ?, ?)", "DBUtilsDemo01", 1000);
        // delete
        queryRunner.update("delete from account where id = ?", 5);
        // update
        queryRunner.update("update account set money = ? where id = ?", 1000000, 6);
        // query (2 ways)
        // Account account = queryRunner.query("select * from account where id = ?", new ResultSetHandler<Account>() {
        //
        //     @Override
        //     public Account handle(ResultSet resultSet) throws SQLException {
        //         Account acc = new Account();
        //         while (resultSet.next()) {
        //             acc.setName(resultSet.getString("name"));
        //             acc.setMoney(resultSet.getInt("money"));
        //         }
        //         return acc;
        //     }
        // }, 4);
        // System.out.println(account);

        Account account = queryRunner.query("select * from account where id = ?", new BeanHandler<Account>(Account.class), 8);
        System.out.println(account);
        /*List<Account> list = queryRunner.query("select * from account ",
				new BeanListHandler<Account>(Account.class));

		    for (Account account : list) {
			System.out.println(account.toString());
		}*/
    }
}
