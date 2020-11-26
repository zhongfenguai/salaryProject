package com.cbf.service.impl;

import com.cbf.dao.SalaryDao;
import com.cbf.entity.Salary;
import com.cbf.entity.SalaryWithUser;
import com.cbf.service.SalaryService;

import java.util.List;

/**
 * 作者：chenbingfeng
 * 日期: 2020/11/4 18:15
 * 描述:
 */
public class SalaryServiceImpl implements SalaryService {
    SalaryDao salaryDao=new SalaryDao();
    @Override
    public void insert(Salary salary) {

    }
    @Override
    public void deleteId(int staffId) {
    }

    @Override
    public boolean updateSalary(Salary salary) {
        return salaryDao.updateSalary(salary);
    }

    @Override
    public List<SalaryWithUser> querySalarybystaffId(String realname) {
        return salaryDao.querySalarybystaffId(realname);
    }

    @Override
    public List<SalaryWithUser> querySalary3(String str, String year, String month) {
        return salaryDao.querySalary3(str,year,month);
    }

    @Override
    public boolean insert2(Salary salary) {
        return salaryDao.insert2(salary);
    }


    @Override
    public List<Salary> querysalarybyid(Integer id) {
        return salaryDao.querysalarybyid(id);
    }

    @Override
    public List<SalaryWithUser> querySalary9(Integer str, String year, String month) {
        return salaryDao.querySalary9(str,year,month);
    }

    @Override
    public List<SalaryWithUser> querysalary111(String str, String year, String month, Integer pageSize, Integer pageNum) {
        return salaryDao.querySalary111(str,year,month,pageSize,pageNum);
    }
}
