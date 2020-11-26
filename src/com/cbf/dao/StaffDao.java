package com.cbf.dao;

import com.cbf.entity.Staff;
import com.cbf.view.Main;
import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**staff的dao方法
 * 作者：chenbingfeng
 * 日期: 2020/11/4 15:00
 * 描述:
 */
public class StaffDao {

    /**
     * 插入员工信息
     *
     * @param staff
     */
//    public boolean insert(Staff staff) {
//        Connection connection = DBConnection.getConn();
//        PreparedStatement preparedStatement = null;
//        System.out.println(connection);
//        try {
//            String strsql = "INSERT INTO staff(userNo,userType,realName,passWord,userName,idCardNumber,sex,bornDate,telephone,email) VALUES(?,?,?,?,?,?,?,?,?,?)";
//            preparedStatement = connection.prepareStatement(strsql);
//            preparedStatement.setString(1, staff.getUserNo());
//            preparedStatement.setInt(2, staff.getUserType());
//            preparedStatement.setString(3, staff.getRealName());
//            preparedStatement.setString(4, staff.getPassWord());
//            preparedStatement.setString(5, staff.getUserName());
//            preparedStatement.setString(6, staff.getIdCardNumber());
//            preparedStatement.setString(7, staff.getSex());
//            preparedStatement.setString(8, staff.getBornDate());
//            preparedStatement.setString(9, staff.getTelephone());
//            preparedStatement.setString(10, staff.getEmail());
//            Integer integer = preparedStatement.executeUpdate();
//            System.out.println(integer);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            DBConnection.close(connection, preparedStatement, null);//关闭所有连接
//        }
//        return false;

    /**
     * 传入一个staff 对象 插入数据 返回布尔值
     *
     * @param staff
     * @return
     */
    public boolean insert(Staff staff) {
        boolean b = false;//初始化
        try {

            String columnName = "";//拼接sql
            String columnValue = "";// 值
//如果传入的staff对象中  属性存在时， 拼接sql
            if (StringUtils.isNotEmpty(staff.getUserName())) {
                columnName += "userName,";
                columnValue += "'" + staff.getUserName() + "',";
            }

            if (StringUtils.isNotEmpty(staff.getBornDate())) {
                columnName += "bornDate,";
                columnValue += "'" + staff.getBornDate() + "',";
            }

            if (StringUtils.isNotEmpty(staff.getTelephone())) {
                columnName += "telephone,";
                columnValue += "'" + staff.getTelephone() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getEmail())) {
                columnName += "email,";
                columnValue += "'" + staff.getEmail() + "',";
            }

            if (StringUtils.isNotEmpty(staff.getSex())) {
                columnName += "sex,";
                columnValue += "'" + staff.getSex() + "',";
            }

            if (StringUtils.isNotEmpty(staff.getIdCardNumber())) {
                columnName += "idCardNumber,";
                columnValue += "'" + staff.getIdCardNumber() + "',";
            }

            if (StringUtils.isNotEmpty(staff.getPassWord())) {
                columnName += "passWord,";
                columnValue += "'" + staff.getPassWord() + "',";
            }

            if (StringUtils.isNotEmpty(staff.getRealName())) {
                columnName += "realName,";
                columnValue += "'" + staff.getRealName() + "',";
            }

            if (null != staff.getUserType()) {
                columnName += "userType,";
                columnValue += "'" + staff.getUserType() + "',";
            }
            if (null != staff.getUserNo()) {
                columnName += "userNo,";
                columnValue += "'" + staff.getUserNo() + "',";
            }
            if (null != staff.getCreatUserId()) {
                columnName += "createUserId,";
                columnValue += "'" + staff.getCreatUserId() + "',";
            }
            if (null != staff.getUpdateUserId()) {
                columnName += "updateUserId,";
                columnValue += "'" + staff.getUpdateUserId() + "',";
            }

            //截取最后一个逗号
            columnName = columnName.substring(0, columnName.length() - 1);
            columnValue = columnValue.substring(0, columnValue.length() - 1);

            // 拼接sql
            String sql = "INSERT INTO staff(" + columnName + " ) VALUES (" + columnValue + ")";

            //创建连接
            Connection connection = DBConnection.getConn();

            Statement statement = connection.createStatement();

            //运行sql 并存在num
            int num = statement.executeUpdate(sql);
//如果num>0 则返回true
            if (num > 0) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }

    /**
     * 传入用户名，密码，用户类型 进行查询
     *
     * @return
     */
    public List<Staff> querystaff(String userName, String password, String userType) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//默认值
        ResultSet resultSet = null;
        List<Staff> list = new ArrayList<>();//创建一个staff泛型的list
        try {
            statement = connection.createStatement();
            //拼接sql
            String strsql =
                    "SELECT s.id,s.userNo,s.userType,s.realName,s.passWord,s.userName,s.idCardNumber,s.sex,s.bornDate,s.telephone,s.email from staff s where userName= '" + userName + "' and passWord ='" + password + "' and userType ='" + userType + "'";
            //运行sql ，存在resultset
            resultSet = statement.executeQuery(strsql);
            Staff staff = new Staff();
            //将结果集 一个个取出
            while (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setUserNo(resultSet.getString("userNo"));
                staff.setUserType(resultSet.getString("userType"));
                staff.setRealName(resultSet.getString("realName"));
                staff.setPassWord(resultSet.getString("passWord"));
                staff.setUserName(resultSet.getString("userName"));
                staff.setIdCardNumber(resultSet.getString("idCardNumber"));
                staff.setSex(resultSet.getString("sex"));
                staff.setBornDate(resultSet.getString("bornDate"));
                staff.setTelephone(resultSet.getString("telephone"));
                staff.setEmail(resultSet.getString("email"));
                //暂存在list
                list.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }
        return list;
    }


    /**
     * 传入id 进行删 返回布尔值
     *
     * @param id
     */
    public boolean deleteId(Integer id) {
//默认值
        Connection connection = null;
        Statement statement = null;
        connection = DBConnection.getConn();//建立连接
        try {
            statement = connection.createStatement();
            //拼接 sql 语句
            String deletestr = "delete from staff where id =" + id;
            // 运行sql 存在deltnum
            int deleteNum = statement.executeUpdate(deletestr);
            // 如果deletnum 存在返回true
            if (deleteNum > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, null);//关闭连接
        }
        return false;
    }

    /**
     * 传入一个staff对象进行修改
     *
     * @param
     * @param
     * @return
     */
    public boolean updateStaff(Staff staff) {
        Connection connection = null;//默认值
        Statement statement = null;
        boolean b = false;
        connection = DBConnection.getConn();//建立连接
        List<Staff> list = new ArrayList<>();//创建一个staff泛型的list
        try {
            statement = connection.createStatement();
            String updateStr = "UPDATE staff SET ";
            //如果传入的staff存在属性不为空，就拼接到sql
            if (StringUtils.isNotEmpty(staff.getUserNo())) {
                updateStr += " userNo='" + staff.getUserNo() + "',";
            }
            if (staff.getUserType() != null) {
                updateStr += " userType=" + staff.getUserType() + ",";
            }
            if (StringUtils.isNotEmpty(staff.getRealName())) {
                updateStr += " realName='" + staff.getRealName() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getPassWord())) {
                updateStr += " passWord='" + staff.getPassWord() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getUserName())) {
                updateStr += " userName='" + staff.getUserName() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getIdCardNumber())) {
                updateStr += " idCardNumber='" + staff.getIdCardNumber() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getSex())) {
                updateStr += " sex='" + staff.getSex() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getBornDate())) {
                updateStr += " bornDate='" + staff.getBornDate() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getTelephone())) {
                updateStr += " telephone='" + staff.getTelephone() + "',";
            }
            if (StringUtils.isNotEmpty(staff.getEmail())) {
                updateStr += " email='" + staff.getEmail() + "',";
            }
            //截取最后一个逗号
            updateStr = updateStr.substring(0, updateStr.length() - 1);
            //根据id 去删除
            updateStr += " where id=" + staff.getId();
            //运行sql
            int updateNum = statement.executeUpdate(updateStr);//通过statement 执行一个sql 语句 ，不为空返回true
            if (updateNum > 0) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, null);//关闭连接
        }
        return b;
    }

    //导入daoutil
    DaoUtil daoUtil = new DaoUtil();

    //传入一个staff对象 进行查询返回list
    public List<Staff> getList(Staff staff) {
        List<Staff> staffList = null;//默认值
        try {
            staffList = daoUtil.select(staff);//查询
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }//返回list
        return staffList;
    }

    /**
     * 传入一个realname进行查询
     *
     * @param realname
     * @return
     */
    public List<Staff> querystaff2(String realname) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//默认值
        ResultSet resultSet = null;
        List<Staff> list = new ArrayList<>();//创建一个staff泛型的list
        try {
            statement = connection.createStatement();
            //拼接sql
            String strsql =
                    "SELECT s.id,s.userNo,s.userType,s.realName,s.passWord,s.userName,s.idCardNumber,s.sex,s.bornDate,s.telephone,s.email from staff s ";
            //如果传入的realname 不为空， 就加到sql语句中
            if (StringUtils.isNotEmpty(realname)) {
                strsql += " where realname='" + realname + "'";
            }
//运行sql ，存在结果集中
            resultSet = statement.executeQuery(strsql);
            Staff staff = null;//默认值
            //将结果集的东西取出来
            while (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setUserNo(resultSet.getString("userNo"));
                staff.setUserType(resultSet.getString("userType"));
                staff.setRealName(resultSet.getString("realName"));
                staff.setPassWord(resultSet.getString("passWord"));
                staff.setUserName(resultSet.getString("userName"));
                staff.setIdCardNumber(resultSet.getString("idCardNumber"));
                staff.setSex(resultSet.getString("sex"));
                staff.setBornDate(resultSet.getString("bornDate"));
                staff.setTelephone(resultSet.getString("telephone"));
                staff.setEmail(resultSet.getString("email"));
                //暂存到list
                list.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }
        return list;
    }

    /**
     * 根据传入的str进行拼接 查询
     *
     * @param str
     * @return
     */
    public List<Staff> querystaff6(String str) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//默认值
        ResultSet resultSet = null;
        List<Staff> list = new ArrayList<>();//闯进啊一个staff泛型的list
        try {
            statement = connection.createStatement();
            //拼接sql
            String strsql =
                    "SELECT s.id,s.userNo,s.userType,s.realName,s.passWord,s.userName,s.idCardNumber,s.sex,s.bornDate,s.telephone,s.email from staff s ";
            // 如果传入的str 不为空，判断他是realname 或者是id 然后进行拼接查询
            if (StringUtils.isNotEmpty(str)) {
                strsql += "where (s.realName like '%" + str + "%' or s.id like '%" + str + "%')";
            }
            // 运行sql 并存在结果集中
            resultSet = statement.executeQuery(strsql);
            Staff staff = null;// 默认值
            // 将结果集的东西取出来
            while (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setUserNo(resultSet.getString("userNo"));
                staff.setUserType(resultSet.getString("userType"));
                staff.setRealName(resultSet.getString("realName"));
                staff.setPassWord(resultSet.getString("passWord"));
                staff.setUserName(resultSet.getString("userName"));
                staff.setIdCardNumber(resultSet.getString("idCardNumber"));
                staff.setSex(resultSet.getString("sex"));
                staff.setBornDate(resultSet.getString("bornDate"));
                staff.setTelephone(resultSet.getString("telephone"));
                staff.setEmail(resultSet.getString("email"));
                // 暂存在list
                list.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }
        return list;
    }

    /**
     * 传入id 进行查询
     *
     * @param id
     * @return
     */
    public List<Staff> querystaffbyId(Integer id) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//设置默认值
        ResultSet resultSet = null;
        List<Staff> list = new ArrayList<>();// 创建一个staff泛型的list
        try {
            statement = connection.createStatement();
            //拼接sql
            String strsql =
                    "SELECT s.id,s.userNo,s.userType,s.realName,s.passWord,s.userName,s.idCardNumber,s.sex,s.bornDate,s.telephone,s.email from staff s ";
            //如果id存在， 就指定查询
            if (null != id) {
                strsql += " where id=" + id + "";
            }
//运行sql 暂存在结果集中
            resultSet = statement.executeQuery(strsql);
            Staff staff = new Staff();
            //将结果集的东西取出
            while (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setUserNo(resultSet.getString("userNo"));
                staff.setUserType(resultSet.getString("userType"));
                staff.setRealName(resultSet.getString("realName"));
                staff.setPassWord(resultSet.getString("passWord"));
                staff.setUserName(resultSet.getString("userName"));
                staff.setIdCardNumber(resultSet.getString("idCardNumber"));
                staff.setSex(resultSet.getString("sex"));
                staff.setBornDate(resultSet.getString("bornDate"));
                staff.setTelephone(resultSet.getString("telephone"));
                staff.setEmail(resultSet.getString("email"));
                //暂存在list
                list.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }
        return list;
    }

    /**
     * 传入staffin 进行查询
     *
     * @param staffIn
     * @return
     */
    public List<Staff> queryStaffbystaff(Staff staffIn) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//设置默认值
        ResultSet resultSet = null;
        List<Staff> list = new ArrayList<>();//创建一个staff泛型的list
        try {
            statement = connection.createStatement();// 获取到一个 statement
            String strSQL = "select * from staff  where 1=1 ";//查询
            //判断staffin 中的属性为不为空 ，并拼接sql
            if (StringUtils.isNotEmpty(staffIn.getUserNo())) {
                strSQL += " and  userNo = '" + staffIn.getUserNo() + "'";
            }

            if (null != staffIn.getUserType()) {
                strSQL += " and  userType =" + staffIn.getUserType();
            }

            if (StringUtils.isNotEmpty(staffIn.getRealName())) {

                strSQL += " and  realName like '%" + staffIn.getRealName() + "%'";

            }
            if (StringUtils.isNotEmpty(staffIn.getPassWord())) {
                strSQL += " and  passWord = '" + staffIn.getPassWord() + "'";
            }
            if (StringUtils.isNotEmpty(staffIn.getUserName())) {
                strSQL += " and  userName = '" + staffIn.getUserName() + "'";
            }
            if (StringUtils.isNotEmpty(staffIn.getIdCardNumber())) {
                strSQL += " and  idCardNumber = '" + staffIn.getIdCardNumber() + "'";
            }
            if (StringUtils.isNotEmpty(staffIn.getSex())) {
                strSQL += " and  sex = '" + staffIn.getSex() + "'";
            }
            if (StringUtils.isNotEmpty(staffIn.getBornDate())) {
                strSQL += " and  bornDate = '" + staffIn.getBornDate() + "'";
            }
            if (StringUtils.isNotEmpty(staffIn.getTelephone())) {
                strSQL += " and  telephone = '" + staffIn.getTelephone() + "'";
            }
            if (StringUtils.isNotEmpty(staffIn.getEmail())) {
                strSQL += " and  email = '" + staffIn.getEmail() + "'";
            }

//运行sql 存在结果集中
            resultSet = statement.executeQuery(strSQL);//通过statement 执行一个sql 语句
            Staff staff = null;
            //将结果集的东西取出
            while (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));

                String userNoStr = resultSet.getString("userNo");
                staff.setUserNo(userNoStr);

                String userTypeStr = resultSet.getString("userType");
                staff.setUserType(userTypeStr);

                String realName = resultSet.getString("realName");
                staff.setRealName(realName);

                String passWordStr = resultSet.getString("passWord");
                staff.setPassWord(passWordStr);

                String userNameStr = resultSet.getString("userName");
                staff.setUserName(userNameStr);

                String idCardNumberStr = resultSet.getString("idCardNumber");
                staff.setUserName(idCardNumberStr);

                String sexStr = resultSet.getString("sex");
                staff.setUserName(sexStr);

                String bornDateStr = resultSet.getString("bornDate");
                staff.setUserName(bornDateStr);

                String telephoneStr = resultSet.getString("telephone");
                staff.setUserName(telephoneStr);

                String email = resultSet.getString("email");
                staff.setUserName(email);
//暂存在list
                list.add(staff);

            }
        } catch (SQLException e) {
            System.out.println("");
        } finally {
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }
//返回lsit
        return list;
    }

    /**
     * 传入username 查询
     *
     * @param userName 用户名
     * @return
     */
    public List<Staff> querystaff10(String userName) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//设置默认值
        ResultSet resultSet = null;
        List<Staff> list = new ArrayList<>();//创建一个staff泛型的list
        try {
            statement = connection.createStatement();
            //通过username 查询全部
            String strsql =
                    "SELECT s.* from staff s where s.userName= '" + userName + "' ";
            resultSet = statement.executeQuery(strsql);//运行sql存在结果集
            Staff staff = new Staff();
            //讲结果集的东西提出来
            while (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setUserNo(resultSet.getString("userNo"));
                staff.setUserType(resultSet.getString("userType"));
                staff.setRealName(resultSet.getString("realName"));
                staff.setPassWord(resultSet.getString("passWord"));
                staff.setUserName(resultSet.getString("userName"));
                staff.setIdCardNumber(resultSet.getString("idCardNumber"));
                staff.setSex(resultSet.getString("sex"));
                staff.setBornDate(resultSet.getString("bornDate"));
                staff.setTelephone(resultSet.getString("telephone"));
                staff.setEmail(resultSet.getString("email"));
                //插入到list
                list.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }//返回list
        return list;
    }

    /**
     * 分页， 根据realname 或者id 查询
     * @param str
     * @return
     */
    public List<Staff> querystaff12(String str,Integer pageSize,Integer pageNum) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//默认值
        ResultSet resultSet = null;
        List<Staff> list = new ArrayList<>();//闯进啊一个staff泛型的list
        try {
            statement = connection.createStatement();
            //拼接sql
            Integer from=(pageSize-1)*pageNum;//
            String strsql =
                    "SELECT s.id,s.userNo,s.userType,s.realName,s.passWord,s.userName,s.idCardNumber,s.sex,s.bornDate,s.telephone,s.email from staff s ";
            // 如果传入的str 不为空，判断他是realname 或者是id 然后进行拼接查询
            if (StringUtils.isNotEmpty(str)) {
                strsql += "where (s.realName like '%" + str + "%' or s.id like '%" + str + "%')";
            }
            strsql+="limit "+from+","+pageNum;//
            // 运行sql 并存在结果集中
            resultSet = statement.executeQuery(strsql);
            Staff staff = null;// 默认值
            // 将结果集的东西取出来
            while (resultSet.next()) {
                staff = new Staff();
                staff.setId(resultSet.getInt("id"));
                staff.setUserNo(resultSet.getString("userNo"));
                staff.setUserType(resultSet.getString("userType"));
                staff.setRealName(resultSet.getString("realName"));
                staff.setPassWord(resultSet.getString("passWord"));
                staff.setUserName(resultSet.getString("userName"));
                staff.setIdCardNumber(resultSet.getString("idCardNumber"));
                staff.setSex(resultSet.getString("sex"));
                staff.setBornDate(resultSet.getString("bornDate"));
                staff.setTelephone(resultSet.getString("telephone"));
                staff.setEmail(resultSet.getString("email"));
                // 暂存在list
                list.add(staff);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }
        return list;
    }

}


