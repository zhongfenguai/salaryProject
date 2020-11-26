package com.cbf.service;

import com.cbf.entity.Salary;
import com.cbf.entity.SalaryWithUser;

import java.util.List;

/**
 * 作者：chenbingfeng
 * 日期: 2020/11/4 18:14
 * 描述:
 */
public interface SalaryService {
    public void insert(Salary salary);
    public void deleteId(int staffId);
    public boolean updateSalary(Salary salary);
    public List<SalaryWithUser> querySalarybystaffId(String realname);
    public List<SalaryWithUser> querySalary3(String str, String year, String month);
    public boolean insert2(Salary salary);
    public List<Salary> querysalarybyid(Integer id);
    public List<SalaryWithUser> querySalary9(Integer str, String year, String month);
    public List<SalaryWithUser> querysalary111(String str, String year, String month,Integer pageSize,Integer pageNum);
}
