package com.cbf.entity;

import java.util.Date;

/**
 * 作者：chenbingfeng
 * 日期: 2020/11/4 14:39
 * 描述:员工表
 */
public class Staff {
    private Integer id;//
    private String userNo;//用户编号
    private String userType;//
    private String realName;//姓名
    private String passWord;//密码
    private String userName;//用户名
    private String idCardNumber;//身份证号码
    private String sex;//
    private String bornDate;//出生日期
    private String telephone;//电话
    private String email;//邮箱
    private Integer creatUserId;//创建人id
    private Integer updateUserId;//操作更改的人的id
    private Date creatTime;//创建时间
    private Date updateTime;//修改数据的时间

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", userNo='" + userNo + '\'' +
                ", userType='" + userType + '\'' +
                ", realName='" + realName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", userName='" + userName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", bornDate='" + bornDate + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", creatUserId=" + creatUserId +
                ", updateUserId=" + updateUserId +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBornDate() {
        return bornDate;
    }

    public void setBornDate(String bornDate) {
        this.bornDate = bornDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCreatUserId() {
        return creatUserId;
    }

    public void setCreatUserId(Integer creatUserId) {
        this.creatUserId = creatUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Staff(Integer id, String userNo, String userType, String realName, String passWord, String userName, String idCardNumber, String sex, String bornDate, String telephone, String email, Integer creatUserId, Integer updateUserId, Date creatTime, Date updateTime) {
        this.id = id;
        this.userNo = userNo;
        this.userType = userType;
        this.realName = realName;
        this.passWord = passWord;
        this.userName = userName;
        this.idCardNumber = idCardNumber;
        this.sex = sex;
        this.bornDate = bornDate;
        this.telephone = telephone;
        this.email = email;
        this.creatUserId = creatUserId;
        this.updateUserId = updateUserId;
        this.creatTime = creatTime;
        this.updateTime = updateTime;
    }

    public Staff() {
    }
}

