package com.cbf.dao;

import java.sql.*;

/** jdbc 连接数据库
 * 作者：chenbingfeng
 * 日期: 2020/11/4 15:01
 * 描述:
 */
public class DBConnection {

    private static String url = "jdbc:mysql://localhost:3306/haogege?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";// 使用jdbc 连接数据库
    private static String userName = "root";//数据库 用户名
    private static String passWord = "123456";//数据库 密码

//    com.mysql.cj.jdbc.Driver

    //    jdbc驱动器名称
    private static String driverName = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(driverName); //加载驱动程序
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {

        try {//与数据库交互
            Connection connection = DriverManager.getConnection(url, userName, passWord);
            System.out.println("数据库连接成功");
            return connection;
        } catch (SQLException e) {
            System.out.println("新建数据库连接失败");
            e.printStackTrace();
            return null;
        }


    }

    /**
     * 关闭接口
     * @param connection
     * @param statement
     * @param resultSet
     */
    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connection != null) {
                connection.close();
            }

        } catch (Exception e) {

        }

    }


}