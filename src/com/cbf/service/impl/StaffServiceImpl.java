package com.cbf.service.impl;

import com.cbf.dao.StaffDao;
import com.cbf.entity.Staff;
import com.cbf.service.StaffService;

import java.util.List;

/**
 * 作者：chenbingfeng
 * 日期: 2020/11/4 18:15
 * 描述:
 */
public class StaffServiceImpl implements StaffService {

    StaffDao staffDao = new StaffDao();

    @Override
    public boolean insert(Staff staff) {
        return staffDao.insert(staff);
    }

    @Override
    public List<Staff> querystaff(String userName,String password,String userTypeStr) {
        return staffDao.querystaff(userName,password,userTypeStr);
    }

    @Override
    public boolean deleteId(int userNo) {

        return staffDao.deleteId(userNo);
    }

    @Override
    public boolean updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
    }

    @Override
    public List<Staff> getList(Staff staff) {
        return staffDao.getList(staff);
    }

    @Override
    public List<Staff> querystaff2(String realname) {
        return staffDao.querystaff2(realname);
    }

    @Override
    public List<Staff> querystaffbyId(Integer id) {
        return staffDao.querystaffbyId(id);
    }

    @Override
    public List<Staff> queryStaffbystaff(Staff staffIn) {
        return staffDao.queryStaffbystaff(staffIn);
    }

    @Override
    public List<Staff> querystaff6(String str) {
        return staffDao.querystaff6(str);
    }

    @Override
    public List<Staff> querystaff10(String userName) {
        return staffDao.querystaff10(userName);
    }

    @Override
    public List<Staff> querystaff12(String str, Integer pageSize, Integer pageNum) {
        return staffDao.querystaff12(str,pageSize,pageNum);
    }

}
