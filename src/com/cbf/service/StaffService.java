package com.cbf.service;

import com.cbf.entity.Staff;

import java.util.List;

/**
 * 作者：chenbingfeng
 * 日期: 2020/11/4 18:15
 * 描述:
 */
public interface StaffService {
    public boolean insert(Staff staff);
    public List<Staff> querystaff(String userName, String password,String userTypeStr);
    public boolean deleteId(int userNo);
    public boolean updateStaff(Staff staff);
    public List<Staff>  getList(Staff staff);
    public List<Staff> querystaff2(String realname);
    public List<Staff> querystaffbyId(Integer id);
    public List<Staff> queryStaffbystaff(Staff staffIn);
    public List<Staff> querystaff6(String str);
    public List<Staff> querystaff10(String userName);
    public List<Staff> querystaff12(String str,Integer pageSize,Integer pageNum);
}
