package com.cbf.dao;

/**
 * 作者：chenbingfeng
 * 日期: 2020/10/30 23:03
 * 描述:
 */




import org.apache.http.client.utils.DateUtils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 反射_sql查询
 */
public class DaoUtil {


    //根据id 获取对象
    public <T> T selectById(T t) throws SQLException, IllegalAccessException, InstantiationException {
        //Class.getSimpleName()通过泛型获取对象名
        Field[] filds = t.getClass().getDeclaredFields();
        filds[0].setAccessible(true);
        String sql = "select * from " + t.getClass().getSimpleName() + " where " + filds[0].getName() + " =" + filds[0].get(t);
        //实例化List泛型
        List<T> li = new ArrayList<T>();
        //实例化GetConnection类，通过getCon方法获取Sql的连接
        Connection con = DBConnection.getConn();
        //通过连接的prepareStatement()的方法执行sql语句
        //调用executeQuery()执行查询获取查询的结果集合
        ResultSet res = con.prepareStatement(sql).executeQuery();
        //通过resultSet.getMetadata()将结果集合转换为ResultMetaData对象
        ResultSetMetaData md = res.getMetaData();
        //获取结果的列数
        int count = md.getColumnCount();
        //获取java对象的属性数组
//        Field[] filds = t.getClass().getDeclaredFields();
        //调用next方法遍历结果集合
        while (res.next()) {
            //将泛型类实例化为对象
            T t2 = (T) t.getClass().newInstance();
            //循环泛型对象的属性值
            for (int i = 0; i < filds.length; i++) {
                //输出当前的属性
                System.out.println(filds[i]);
                //循环遍历当前结果集中的对象的属性
                for (int k = 0; k < count; k++) {
                    //获取此条信息的列的名字
                    String columnName = md.getColumnName(k + 1);
                    //判断结果集中的列名与Java类中的属性名是否对应
                    if (filds[i].getName().toUpperCase().equals(columnName.toUpperCase())) {
                        //修改类的Accessible为true，使当前属性可以修改
                        filds[i].setAccessible(true);
                        //res.getObject(列名) 获取当前这条信息的列名的数据
                        //调用Filed类的set方法，给当前对象赋值
                        filds[i].set(t2, res.getObject(columnName));
                    }
                }
            }
            //将循环赋值完成的类添加到集合中
            li.add(t2);
        }
        if (li != null && li.size() > 0) {
            return li.get(0);
        } else {
            return null;
        }
    }


    //定义接收的类型为泛型集合
    public <T> List<T> select(T t) throws SQLException, IllegalAccessException, InstantiationException {
        Field[] filds = t.getClass().getDeclaredFields();
        String tiao = "";
        try {
            for (int i = 1; i < filds.length; i++) {
                Field fild = filds[i];
                fild.setAccessible(true);
                Object o = fild.get(t);
                if (o == null) {
                    continue;
                }
                if (!"".equals(tiao)) {
                    tiao += " and ";
                }
                String value = "";
                if (o instanceof Date) {
                    value = DateUtils.formatDate((Date) o, "yyyy-MM-dd HH:mm:ss");
                } else {
                    value = o.toString();
                }

                try {
                    if ((String.class).equals(fild.getType()) || fild.getType().equals(Date.class)) {
                        tiao += fild.getName() + "='" + value + "'";
                    } else {
                        tiao += fild.getName() + "=" + value + "";
                    }
                } catch (Exception e) {
                    tiao += fild.getName() + "=" + value + "";
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql = "select * from " + t.getClass().getSimpleName();
        if (!"".equals(tiao)) {
            sql += " where " + tiao;
        }

        //实例化List泛型
        List<T> li = new ArrayList<T>();
        //实例化GetConnection类，通过getCon方法获取Sql的连接
        Connection con = DBConnection.getConn();
        //通过连接的prepareStatement()的方法执行sql语句
        //调用executeQuery()执行查询获取查询的结果集合
        ResultSet res = con.prepareStatement(sql).executeQuery();
        //通过resultSet.getMetadata()将结果集合转换为ResultMetaData对象
        ResultSetMetaData md = res.getMetaData();
        //获取结果的列数
        int count = md.getColumnCount();
        //获取java对象的属性数组
//        Field[] filds = t.getClass().getDeclaredFields();
        //调用next方法遍历结果集合
        while (res.next()) {
            //将泛型类实例化为对象
            T t2 = (T) t.getClass().newInstance();
            //循环泛型对象的属性值
            for (int i = 0; i < filds.length; i++) {
                //输出当前的属性
                System.out.println(filds[i]);
                //循环遍历当前结果集中的对象的属性
                for (int k = 0; k < count; k++) {
                    //获取此条信息的列的名字
                    String columnName = md.getColumnName(k + 1);
                    //判断结果集中的列名与Java类中的属性名是否对应
                    if (filds[i].getName().toUpperCase().equals(columnName.toUpperCase())) {
                        //修改类的Accessible为true，使当前属性可以修改
                        filds[i].setAccessible(true);
                        //res.getObject(列名) 获取当前这条信息的列名的数据
                        //调用Filed类的set方法，给当前对象赋值
                        filds[i].set(t2, res.getObject(columnName));
                    }
                }
            }
            //将循环赋值完成的类添加到集合中
            li.add(t2);
        }
        //返回结果
        return li;
    }

    /**
     * 更新
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> T updateById(T t) {
        try {
            Field[] filds = t.getClass().getDeclaredFields();
            filds[0].setAccessible(true);
            String tiao = "";
            for (int i = 1; i < filds.length; i++) {
                Field fild = filds[i];
                fild.setAccessible(true);
                if (filds[i].get(t) == null) {
                    continue;
                }

                if (fild.getType().equals(String.class)) {
                    tiao += fild.getName() + "='" + fild.get(t) + "',";
                } else if (fild.getType().equals(Date.class)) {
                    tiao += fild.getName() + "='" + DateUtils.formatDate((Date) fild.get(t), "yyyy-MM-dd HH:mm:ss") + "',";
                } else {
                    tiao += fild.getName() + "=" + fild.get(t) + ",";
                }
            }
            tiao = tiao.split(",$")[0];
            String sql = "update " + t.getClass().getSimpleName() + " set " + tiao + " where " + filds[0].getName() + " =" + filds[0].get(t);
            System.out.println(sql);
            int i = DBConnection.getConn().prepareStatement(sql).executeUpdate();
            System.out.println(i);
            if (i > 0) {
                System.out.println("Successful！");
            } else {
                System.out.println("Error！");
            }

        } catch (IllegalAccessException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 添加
     *
     * @param t
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    public <T> boolean insert(T t) throws IllegalAccessException {
        Field[] filds = t.getClass().getDeclaredFields();
        String columnName = "";
        String columnValue = "";
        for (int i = 0; i < filds.length; i++) {
            filds[i].setAccessible(true);
            if (filds[i].get(t) == null) {
                continue;
            }
            columnName += filds[i].getName() + ",";
            if (filds[i].getType().equals(String.class)) {
                columnValue += "'" + filds[i].get(t) + "'" + ",";
            } else if (filds[i].getType().equals(Date.class)) {
                columnValue += "'" + DateUtils.formatDate((Date) filds[i].get(t), "yyyy-MM-dd HH:mm:ss") + "'" + ",";
            } else {
                columnValue += filds[i].get(t) + ",";
            }
        }
        columnName = columnName.split(",$")[0];
        columnValue = columnValue.split(",$")[0];
        String sql = "insert into " + t.getClass().getSimpleName() + "(" + columnName + ") values(" + columnValue + ")";
        System.out.println(sql);
        try {
            int i = DBConnection.getConn().prepareStatement(sql).executeUpdate();
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 反射删除
     *
     * @param t
     * @param <T>
     * @return
     */
    public <T> T delete(T t) throws SQLException {

        Field[] filds = t.getClass().getDeclaredFields();
        String tiao = "";
        try {
            for (int i = 0; i < filds.length; i++) {
                Field fild = filds[i];
                fild.setAccessible(true);
                Object o = fild.get(t);
                if (o == null) {
                    continue;
                }
                if (!"".equals(tiao)) {
                    tiao += " and ";
                }
                String value = "";
                if (o instanceof Date) {
                    value = DateUtils.formatDate((Date) o, "yyyy-MM-dd HH:mm:ss");
                } else {
                    value = o.toString();
                }


                try {
                    if ((String.class).equals(fild.getType()) || fild.getType().equals(Date.class)) {
                        tiao += fild.getName() + "='" + value + "'";
                    } else {
                        tiao += fild.getName() + "=" + value + "";
                    }
                } catch (Exception e) {
                    tiao += fild.getName() + "=" + value + "";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String sql = "delete from " + t.getClass().getSimpleName();
            if (!"".equals(tiao)) {
                tiao = tiao.split(",$")[0];
                sql += " where " + tiao;
            }

            System.out.println(sql);
            int i = DBConnection.getConn().prepareStatement(sql).executeUpdate();
            if (i > 0) {
                System.out.println("Successful!");
            } else {
                System.out.println("Error!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
