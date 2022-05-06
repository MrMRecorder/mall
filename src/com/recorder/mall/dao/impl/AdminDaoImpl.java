package com.recorder.mall.dao.impl;

import com.recorder.mall.dao.AdminDao;
import com.recorder.mall.dao.BasicDao;
import com.recorder.mall.entity.Admin;
import com.recorder.mall.entity.Member;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class AdminDaoImpl extends BasicDao<Admin> implements AdminDao {
    @Override
    public Admin queryAdminByUsernameAndPassword(String username, String password) {
        String sql = "select * from admin where username=? and password=md5(?)";
        return selectSingle(sql,Admin.class,username,password);
    }
}
