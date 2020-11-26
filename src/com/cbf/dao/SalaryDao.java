package com.cbf.dao;


import com.cbf.entity.Salary;
import com.cbf.entity.SalaryWithUser;
import com.cbf.entity.Staff;
import org.apache.commons.lang3.StringUtils;


import java.sql.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

/**
 * salary 的dao方法
 * 作者：chenbingfeng
 * 日期: 2020/11/4 15:00
 * 描述:
 */
public class SalaryDao {
    /**
     * 传入一个salary对象插入数据
     *
     * @param salary 参入一个salary对象
     */
    public void insert(Salary salary) {
        Connection connection = DBConnection.getConn();
        PreparedStatement preparedStatement = null;//初始化
        System.out.println(connection);
        try {
            String strsql = "INSERT INTO salary(id,staffId,creatUserId,updateUserId,dateTime,basicSalary,postSalary,senioritySalary,communication,transportation,individualTaxPayment,socialSecurityPayment,housingProvidentFund,realSalary,totalSalary) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            preparedStatement = connection.prepareStatement(strsql);//先执行sql 语句
            //对每个参数设置值
            preparedStatement.setInt(1, salary.getId());
            preparedStatement.setString(2, salary.getStaffId());
            preparedStatement.setInt(3, salary.getCreatUserId());
            preparedStatement.setInt(4, salary.getUpdateUserId());
            preparedStatement.setString(5, salary.getYear());
            preparedStatement.setFloat(6, salary.getBasicSalary());
            preparedStatement.setFloat(7, salary.getPostSalary());
            preparedStatement.setFloat(8, salary.getSenioritySalary());
            preparedStatement.setFloat(9, salary.getCommunication());
            preparedStatement.setFloat(10, salary.getTransportation());
            preparedStatement.setFloat(11, salary.getIndividualTaxPayment());
            preparedStatement.setFloat(12, salary.getSocialSecurityPayment());
            preparedStatement.setFloat(13, salary.getHousingProvidentFund());
            preparedStatement.setFloat(14, salary.getRealSalary());
            preparedStatement.setFloat(15, salary.getTotalSalary());
            preparedStatement.setString(16, salary.getMonth());
            Integer integer = preparedStatement.executeUpdate();//用于执行返回多个结果集、多个更新计数或二者组合的语句
            System.out.println(integer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, preparedStatement, null);//关闭所有连接
        }

    }

    /**
     * 通过传入realname对salarywithuser进行查询
     *
     * @return
     */
    public List<SalaryWithUser> querySalarybystaffId(String realname) {
        Connection connection = DBConnection.getConn();
        Statement statement = null;
        ResultSet resultSet = null;
        List<SalaryWithUser> list = new ArrayList<>(); //创建一个salarywithuser泛型的list集合
        try {
            statement = connection.createStatement();
            //查找staff和salary表中，关联id的数据。输出所有
            String strsql = "SELECT distinct s.realName,sa.* from salary sa,staff s where sa.staffid=s.id ";
            //拼接，如果realname不为空 输出指定realname的数据
            if (StringUtils.isNotEmpty(realname)) {
                strsql += "and s.realName like '%" + realname;
            }
            //后执行sql，得到结果集
            resultSet = statement.executeQuery(strsql);
//            Salary salary = null;
//            SalaryWithUser salaryWithUser = new SalaryWithUser();
            SalaryWithUser salaryWithUser = null;//初始化
            //分解 结果集
            while (resultSet.next()) {
//                salary = new Salary();
                salaryWithUser = new SalaryWithUser();
                //保存 提取的值
                salaryWithUser.setId(resultSet.getInt("id"));
                salaryWithUser.setRealName(resultSet.getString("realname"));
                salaryWithUser.setStaffId(resultSet.getInt("staffid"));
                salaryWithUser.setCreatUserId(resultSet.getInt("createUserId"));
                salaryWithUser.setUpdateUserId(resultSet.getInt("updateUserId"));
                salaryWithUser.setYear(resultSet.getString("year"));
                salaryWithUser.setBasicSalary(resultSet.getFloat("basicSalary"));
                salaryWithUser.setPostSalary(resultSet.getFloat("postSalary"));
                salaryWithUser.setSenioritySalary(resultSet.getFloat("senioritySalary"));
                salaryWithUser.setCommunication(resultSet.getFloat("communication"));
                salaryWithUser.setTransportation(resultSet.getFloat("transportation"));
                salaryWithUser.setIndividualTaxPayment(resultSet.getFloat("individualTaxPayment"));
                salaryWithUser.setSocialSecurityPayment(resultSet.getFloat("socialSecurityPayment"));
                salaryWithUser.setHousingProvidentFund(resultSet.getFloat("housingProvidentFund"));
                salaryWithUser.setRealSalary(resultSet.getFloat("realSalary"));
                salaryWithUser.setTotalSalary(resultSet.getFloat("totalSalary"));
                salaryWithUser.setMonth(resultSet.getString("month"));
                //放到list里
                list.add(salaryWithUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }
        return list;
    }

    /**
     * 通过传入3个参数进行查询
     *
     * @param str   传入参数可以是realname，或者是staffid
     * @param year  年份
     * @param month 月份
     * @return
     */
    public List<SalaryWithUser> querySalary3(String str, String year, String month) {
        Connection connection = DBConnection.getConn();
        Statement statement = null;
        ResultSet resultSet = null;
//        str = salaryWithUser.getRealName();
//        str = salaryWithUser.getStaffId();
//创建一个salarywithuser泛型的list
        List<SalaryWithUser> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String strsql = "SELECT distinct s.realName,sa.* from salary sa,staff s where sa.staffid=s.id ";
            //拼接，通过传入realname 或者staffid 进行条件查询
            if (StringUtils.isNotEmpty(str)) {
                strsql += "and (s.realName like '%" + str + "%' or sa.staffId like '%" + str + "%')";
                //传入year 拼接起来进行条件查询
            }
            if (StringUtils.isNotEmpty(year)) {
                strsql += "and sa.year ='" + year + "'";
                //传入month 拼接起来进行条件查询
            }
            if (StringUtils.isNotEmpty(month)) {
                strsql += "and sa.month ='" + month + "'";
            }
//执行sql语句
            resultSet = statement.executeQuery(strsql);
//            Salary salary = null;
//            SalaryWithUser salaryWithUser = new SalaryWithUser();
            //初始化
            SalaryWithUser salaryWithUser = null;
            //把结果集 提出来
            while (resultSet.next()) {
//                salary = new Salary();
                salaryWithUser = new SalaryWithUser();
                //设置值
                salaryWithUser.setId(resultSet.getInt("id"));
                salaryWithUser.setRealName(resultSet.getString("realname"));
                salaryWithUser.setStaffId(resultSet.getInt("staffid"));
                salaryWithUser.setCreatUserId(resultSet.getInt("createUserId"));
                salaryWithUser.setUpdateUserId(resultSet.getInt("updateUserId"));
                salaryWithUser.setYear(resultSet.getString("year"));
                salaryWithUser.setBasicSalary(resultSet.getFloat("basicSalary"));
                salaryWithUser.setPostSalary(resultSet.getFloat("postSalary"));
                salaryWithUser.setSenioritySalary(resultSet.getFloat("senioritySalary"));
                salaryWithUser.setCommunication(resultSet.getFloat("communication"));
                salaryWithUser.setTransportation(resultSet.getFloat("transportation"));
                salaryWithUser.setIndividualTaxPayment(resultSet.getFloat("individualTaxPayment"));
                salaryWithUser.setSocialSecurityPayment(resultSet.getFloat("socialSecurityPayment"));
                salaryWithUser.setHousingProvidentFund(resultSet.getFloat("housingProvidentFund"));
                salaryWithUser.setRealSalary(resultSet.getFloat("realSalary"));
                salaryWithUser.setTotalSalary(resultSet.getFloat("totalSalary"));
                salaryWithUser.setMonth(resultSet.getString("month"));
                list.add(salaryWithUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }//返回list
        return list;
    }

    /**
     * 通过传入3个参数进行查询
     *
     * @param str   integer 传入staffid
     * @param year  年份
     * @param month 月份
     * @return
     */
    public List<SalaryWithUser> querySalary9(Integer str, String year, String month) {
        Connection connection = DBConnection.getConn();//建立连接
        //初始化
        Statement statement = null;
        ResultSet resultSet = null;
//        str = salaryWithUser.getRealName();
//        str = salaryWithUser.getStaffId();
//创建一个salarywithuser泛型的list
        List<SalaryWithUser> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            String strsql = "SELECT distinct s.realName,sa.* from salary sa,staff s where sa.staffid=s.id ";
            //拼接 ，对staffid 进行条件查询
            if (null != str) {
                strsql += "and (s.realName like '%" + str + "%' or sa.staffId like '%" + str + "%')";
            }// 拼接， 对year 进行条件查询
            if (StringUtils.isNotEmpty(year)) {
                strsql += "and sa.year ='" + year + "'";
            }// 拼接， 对month 进行条件查询
            if (StringUtils.isNotEmpty(month)) {
                strsql += "and sa.month ='" + month + "'";
            }
//运行sql语句
            resultSet = statement.executeQuery(strsql);
//            Salary salary = null;
//            SalaryWithUser salaryWithUser = new SalaryWithUser();
            SalaryWithUser salaryWithUser = null;//初始化
            //提取结果集
            while (resultSet.next()) {
//                salary = new Salary();
                salaryWithUser = new SalaryWithUser();
                //设置值
                salaryWithUser.setId(resultSet.getInt("id"));
                salaryWithUser.setRealName(resultSet.getString("realname"));
                salaryWithUser.setStaffId(resultSet.getInt("staffid"));
                salaryWithUser.setCreatUserId(resultSet.getInt("createUserId"));
                salaryWithUser.setUpdateUserId(resultSet.getInt("updateUserId"));
                salaryWithUser.setYear(resultSet.getString("year"));
                salaryWithUser.setBasicSalary(resultSet.getFloat("basicSalary"));
                salaryWithUser.setPostSalary(resultSet.getFloat("postSalary"));
                salaryWithUser.setSenioritySalary(resultSet.getFloat("senioritySalary"));
                salaryWithUser.setCommunication(resultSet.getFloat("communication"));
                salaryWithUser.setTransportation(resultSet.getFloat("transportation"));
                salaryWithUser.setIndividualTaxPayment(resultSet.getFloat("individualTaxPayment"));
                salaryWithUser.setSocialSecurityPayment(resultSet.getFloat("socialSecurityPayment"));
                salaryWithUser.setHousingProvidentFund(resultSet.getFloat("housingProvidentFund"));
                salaryWithUser.setRealSalary(resultSet.getFloat("realSalary"));
                salaryWithUser.setTotalSalary(resultSet.getFloat("totalSalary"));
                salaryWithUser.setMonth(resultSet.getString("month"));
                list.add(salaryWithUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }//返回list
        return list;
    }

    /**
     * 根据staffid进行删除
     *
     * @param staffId
     */
    public void deleteId(int staffId) {
        //初始化
        Connection connection = null;
        Statement statement = null;
        connection = DBConnection.getConn();//建立连接
        try {
            statement = connection.createStatement();
            String deletestr = "delete from salary where staffId =" + staffId;
            //使用deletenum暂存
            int deleteNum = statement.executeUpdate(deletestr);
            System.out.println(deleteNum);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, statement, null);//关闭连接
        }
    }

    /**
     * 传入一个salary对象进行修改
     *
     * @param
     * @param
     * @return
     */
    public boolean updateSalary(Salary salary) {
        //初始化
        Connection connection = null;
        Statement statement = null;
        boolean b = false;//初始化 b的值为false
        connection = DBConnection.getConn();//建立连接
        //穿件一个salary泛型的list
        List<Salary> list = new ArrayList<>();

        try {
            statement = connection.createStatement();
            String updateStr = "UPDATE salary SET ";
//对salary的所有值进行查询，如果不为空就拼接在sql语句上
            if (salary.getStaffId() != null) {
                updateStr += " staffId=" + salary.getStaffId() + ",";
            }
            if (salary.getCreatUserId() != null) {
                updateStr += " createUserId=" + salary.getCreatUserId() + ",";
            }
            if (salary.getUpdateUserId() != null) {
                updateStr += " updateUserId=" + salary.getCreatUserId() + ",";
            }
            if (salary.getBasicSalary() != null) {
                updateStr += " basicSalary=" + salary.getBasicSalary() + ",";
            }
            if (salary.getPostSalary() != null) {
                updateStr += " postSalary=" + salary.getPostSalary() + ",";
            }
            if (salary.getSenioritySalary() != null) {
                updateStr += " senioritySalary=" + salary.getSenioritySalary() + ",";
            }
            if (salary.getCommunication() != null) {
                updateStr += " communication=" + salary.getCommunication() + ",";
            }
            if (salary.getTransportation() != null) {
                updateStr += " transportation=" + salary.getTransportation() + ",";
            }
            if (salary.getIndividualTaxPayment() != null) {
                updateStr += " individualTaxPayment=" + salary.getIndividualTaxPayment() + ",";
            }
            if (salary.getSocialSecurityPayment() != null) {
                updateStr += " socialSecurityPayment=" + salary.getSocialSecurityPayment() + ",";
            }
            if (salary.getHousingProvidentFund() != null) {
                updateStr += " housingProvidentFund=" + salary.getHousingProvidentFund() + ",";
            }
            if (salary.getRealSalary() != null) {
                updateStr += " realSalary=" + salary.getRealSalary() + ",";
            }
            if (salary.getTotalSalary() != null) {
                updateStr += " totalSalary=" + salary.getTotalSalary() + ",";
            }

            updateStr = updateStr.substring(0, updateStr.length() - 1);//截取最后一个逗号
            updateStr += " where id=" + salary.getId();//指定id 查询
            int updateNum = statement.executeUpdate(updateStr);//通过statement 执行一个sql 语句 并暂存在updatenum里
//如果>0则存在
            if (updateNum > 0) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, statement, null);//关闭连接
        }//返回布尔类型
        return b;
    }

    /**
     *拼接sql 进行插入
     * @param salary
     * @return
     */
    public boolean insert2(Salary salary) {
        boolean b = false;
        try {

            //设置空值
            String columnName = ""; // sql 语句
            String columnValue = "";// 对应的值

            // 对传入的salary进行查询不为空 ，然后拼接sql
            if (StringUtils.isNotEmpty(salary.getStaffId())) {
                columnName += "staffid,";
                columnValue += "'" + salary.getStaffId() + "',";
            }

            if (null != salary.getBasicSalary()) {
                columnName += "basicSalary,";
                columnValue += "'" + salary.getBasicSalary() + "',";
            }

            if (StringUtils.isNotEmpty(salary.getMonth())) {
                columnName += "month,";
                columnValue += "'" + salary.getMonth() + "',";
            }
            if (StringUtils.isNotEmpty(salary.getYear())) {
                columnName += "year,";
                columnValue += "'" + salary.getYear() + "',";
            }

            if (null != salary.getHousingProvidentFund()) {
                columnName += "housingProvidentFund,";
                columnValue += "'" + salary.getHousingProvidentFund() + "',";
            }

            if (null != salary.getRealSalary()) {
                columnName += "realSalary,";
                columnValue += "'" + salary.getRealSalary() + "',";
            }

            if (null != salary.getTotalSalary()) {
                columnName += "totalSalary,";
                columnValue += "'" + salary.getTotalSalary() + "',";
            }

            if (null != salary.getSocialSecurityPayment()) {
                columnName += "socialSecurityPayment,";
                columnValue += "'" + salary.getSocialSecurityPayment() + "',";
            }

            if (null != salary.getIndividualTaxPayment()) {
                columnName += "individualTaxPayment,";
                columnValue += salary.getIndividualTaxPayment() + ",";
            }
            if (null != salary.getTransportation()) {
                columnName += "transportation,";
                columnValue += salary.getTransportation() + ",";
            }
            if (null != salary.getCommunication()) {
                columnName += "communication,";
                columnValue += salary.getCommunication() + ",";
            }
            if (null != salary.getSenioritySalary()) {
                columnName += "senioritySalary,";
                columnValue += salary.getSenioritySalary() + ",";
            }
            if (null != salary.getPostSalary()) {
                columnName += "postSalary,";
                columnValue += salary.getPostSalary() + ",";
            }

            columnName = columnName.substring(0, columnName.length() - 1);//截取最后一个逗号
            columnValue = columnValue.substring(0, columnValue.length() - 1);//截取最后一个逗号

            //最终拼接sql
            String sql = "INSERT INTO salary(" + columnName + " ) VALUES (" + columnValue + ")";

            Connection connection = DBConnection.getConn();//建立连接

            Statement statement = connection.createStatement();

            int num = statement.executeUpdate(sql);//执行sql
//如果num存在，则返回true
            if (num > 0) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }//返回布尔类型
        return b;
    }

    /**
     * 通过传入id 进行查询
     * @param id
     * @return
     */
    public List<Salary> querysalarybyid(Integer id) {
        Connection connection = DBConnection.getConn();//建立连接
        Statement statement = null;//初始化
        ResultSet resultSet = null;
        List<Salary> list = new ArrayList<>();//建立一个salary泛型的list
        try {
            statement = connection.createStatement();
            String strsql =
                    "SELECT  * from salary sa ";
            //判断，如果id不为空就根据id去查询
            if (null != id) {
                strsql += " where id=" + id + "";
            }
            // 执行sql
            resultSet = statement.executeQuery(strsql);
            Salary salary = new Salary();
            // 提取结果集
            while (resultSet.next()) {
                salary = new Salary();
                salary.setId(resultSet.getInt("id"));
                salary.setStaffId(resultSet.getString("staffId"));
                salary.setYear(resultSet.getString("year"));
                salary.setMonth(resultSet.getString("month"));
                salary.setBasicSalary(resultSet.getFloat("basicSalary"));
                salary.setPostSalary(resultSet.getFloat("postSalary"));
                salary.setSenioritySalary(resultSet.getFloat("senioritySalary"));
                salary.setCommunication(resultSet.getFloat("communication"));
                salary.setTransportation(resultSet.getFloat("transportation"));
                salary.setIndividualTaxPayment(resultSet.getFloat("individualTaxPayment"));
                salary.setSocialSecurityPayment(resultSet.getFloat("socialSecurityPayment"));
                salary.setHousingProvidentFund(resultSet.getFloat("housingProvidentFund"));
                salary.setRealSalary(resultSet.getFloat("realSalary"));
                salary.setTotalSalary(resultSet.getFloat("totalSalary"));
                //存到list
                list.add(salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }//返回list
        return list;

    }

    /**
     *
     * @param
     * @param
     * @param
     * @return
     */

    public List<SalaryWithUser> querySalary111(String str, String year, String month,Integer pageSize,Integer pageNum) {
        Connection connection = DBConnection.getConn();
        Statement statement = null;
        ResultSet resultSet = null;
//        str = salaryWithUser.getRealName();
//        str = salaryWithUser.getStaffId();
//创建一个salarywithuser泛型的list
        List<SalaryWithUser> list = new ArrayList<>();
        try {
            statement = connection.createStatement();
            Integer from=(pageSize-1)*pageNum;
            String strsql = "SELECT distinct s.realName,sa.* from salary sa,staff s where sa.staffid=s.id ";
            //拼接，通过传入realname 或者staffid 进行条件查询
            if (StringUtils.isNotEmpty(str)) {
                strsql += "and (s.realName like '%" + str + "%' or sa.staffId like '%" + str + "%')";
                //传入year 拼接起来进行条件查询
            }
            if (StringUtils.isNotEmpty(year)) {
                strsql += "and sa.year ='" + year + "'";
                //传入month 拼接起来进行条件查询
            }
            if (StringUtils.isNotEmpty(month)) {
                strsql += "and sa.month ='" + month + "'";
            }
            strsql+="limit "+from+","+pageNum;
//执行sql语句
            resultSet = statement.executeQuery(strsql);
//            Salary salary = null;
//            SalaryWithUser salaryWithUser = new SalaryWithUser();
            //初始化
            SalaryWithUser salaryWithUser = null;
            //把结果集 提出来
            while (resultSet.next()) {
//                salary = new Salary();
                salaryWithUser = new SalaryWithUser();
                //设置值
                salaryWithUser.setId(resultSet.getInt("id"));
                salaryWithUser.setRealName(resultSet.getString("realname"));
                salaryWithUser.setStaffId(resultSet.getInt("staffid"));
                salaryWithUser.setCreatUserId(resultSet.getInt("createUserId"));
                salaryWithUser.setUpdateUserId(resultSet.getInt("updateUserId"));
                salaryWithUser.setYear(resultSet.getString("year"));
                salaryWithUser.setBasicSalary(resultSet.getFloat("basicSalary"));
                salaryWithUser.setPostSalary(resultSet.getFloat("postSalary"));
                salaryWithUser.setSenioritySalary(resultSet.getFloat("senioritySalary"));
                salaryWithUser.setCommunication(resultSet.getFloat("communication"));
                salaryWithUser.setTransportation(resultSet.getFloat("transportation"));
                salaryWithUser.setIndividualTaxPayment(resultSet.getFloat("individualTaxPayment"));
                salaryWithUser.setSocialSecurityPayment(resultSet.getFloat("socialSecurityPayment"));
                salaryWithUser.setHousingProvidentFund(resultSet.getFloat("housingProvidentFund"));
                salaryWithUser.setRealSalary(resultSet.getFloat("realSalary"));
                salaryWithUser.setTotalSalary(resultSet.getFloat("totalSalary"));
                salaryWithUser.setMonth(resultSet.getString("month"));
                list.add(salaryWithUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {//关闭接口
            DBConnection.close(connection, statement, resultSet);//关闭所有连接
        }//返回list
        return list;
    }
}


