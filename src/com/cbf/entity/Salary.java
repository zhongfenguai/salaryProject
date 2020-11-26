package com.cbf.entity;

import java.util.Date;

/**
 * 作者：chenbingfeng
 * 日期: 2020/11/4 14:39
 * 描述:工资表
 */
public class Salary {

    private Integer id;//
    private String staffId;//关联的员工编号
    private Integer creatUserId;//
    private Integer updateUserId;//
    private Date updateTime;//
    private Date createTime;//
    private String year;//
    private String month;//
    private Float basicSalary;//基本工资
    private Float postSalary;//岗位工资
    private Float senioritySalary;//工龄工资
    private Float communication;//通讯补助
    private Float transportation;//交通补助
    private Float individualTaxPayment;//个税代缴
    private Float socialSecurityPayment;//社保代缴
    private Float housingProvidentFund;//住房公积金
    private Float realSalary;//实发工资
    private Float totalSalary;//总工资

    @Override
    public String toString() {
        return "Salary{" +
                "id=" + id +
                ", staffId='" + staffId + '\'' +
                ", creatUserId=" + creatUserId +
                ", updateUserId=" + updateUserId +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                ", year='" + year + '\'' +
                ", month='" + month + '\'' +
                ", basicSalary=" + basicSalary +
                ", postSalary=" + postSalary +
                ", senioritySalary=" + senioritySalary +
                ", communication=" + communication +
                ", transportation=" + transportation +
                ", individualTaxPayment=" + individualTaxPayment +
                ", socialSecurityPayment=" + socialSecurityPayment +
                ", housingProvidentFund=" + housingProvidentFund +
                ", realSalary=" + realSalary +
                ", totalSalary=" + totalSalary +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Float getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(Float basicSalary) {
        this.basicSalary = basicSalary;
    }

    public Float getPostSalary() {
        return postSalary;
    }

    public void setPostSalary(Float postSalary) {
        this.postSalary = postSalary;
    }

    public Float getSenioritySalary() {
        return senioritySalary;
    }

    public void setSenioritySalary(Float senioritySalary) {
        this.senioritySalary = senioritySalary;
    }

    public Float getCommunication() {
        return communication;
    }

    public void setCommunication(Float communication) {
        this.communication = communication;
    }

    public Float getTransportation() {
        return transportation;
    }

    public void setTransportation(Float transportation) {
        this.transportation = transportation;
    }

    public Float getIndividualTaxPayment() {
        return individualTaxPayment;
    }

    public void setIndividualTaxPayment(Float individualTaxPayment) {
        this.individualTaxPayment = individualTaxPayment;
    }

    public Float getSocialSecurityPayment() {
        return socialSecurityPayment;
    }

    public void setSocialSecurityPayment(Float socialSecurityPayment) {
        this.socialSecurityPayment = socialSecurityPayment;
    }

    public Float getHousingProvidentFund() {
        return housingProvidentFund;
    }

    public void setHousingProvidentFund(Float housingProvidentFund) {
        this.housingProvidentFund = housingProvidentFund;
    }

    public Float getRealSalary() {
        return realSalary;
    }

    public void setRealSalary(Float realSalary) {
        this.realSalary = realSalary;
    }

    public Float getTotalSalary() {
        return totalSalary;
    }

    public void setTotalSalary(Float totalSalary) {
        this.totalSalary = totalSalary;
    }

    public Salary(Integer id, String staffId, Integer creatUserId, Integer updateUserId, Date updateTime, Date createTime, String year, String month, Float basicSalary, Float postSalary, Float senioritySalary, Float communication, Float transportation, Float individualTaxPayment, Float socialSecurityPayment, Float housingProvidentFund, Float realSalary, Float totalSalary) {
        this.id = id;
        this.staffId = staffId;
        this.creatUserId = creatUserId;
        this.updateUserId = updateUserId;
        this.updateTime = updateTime;
        this.createTime = createTime;
        this.year = year;
        this.month = month;
        this.basicSalary = basicSalary;
        this.postSalary = postSalary;
        this.senioritySalary = senioritySalary;
        this.communication = communication;
        this.transportation = transportation;
        this.individualTaxPayment = individualTaxPayment;
        this.socialSecurityPayment = socialSecurityPayment;
        this.housingProvidentFund = housingProvidentFund;
        this.realSalary = realSalary;
        this.totalSalary = totalSalary;
    }

    public Salary() {
    }
}