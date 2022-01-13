package org.petstore.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class DBUtil {
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/mypetstore?useUnicode=true&characterEncoding=UTF-8&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
           Class.forName(DRIVER_CLASS);
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        return connection;
    }

    public static void closeConnection(Connection connection) throws Exception {
        if(connection!=null){
            connection.close();
        }
    }

    public static void closeStatement(Statement statement) throws Exception {
        if(statement!=null){
            statement.close();
        }
    }

    public static void closePreparedStatement(PreparedStatement preparedStatement) throws Exception {
        if(preparedStatement!=null){
            preparedStatement.close();
        }
    }

    public static void closeResultSet(ResultSet set) throws Exception {
        if(set!=null){
            set.close();
        }
    }
  /*  public static void main(String []args) throws SQLException, ClassNotFoundException {
        Connection connection = DBUtil.getConnection();
        System.out.println(connection);
    }

   */
}
