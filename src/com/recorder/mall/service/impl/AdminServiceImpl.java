package com.recorder.mall.service.impl;

import com.recorder.mall.dao.AdminDao;
import com.recorder.mall.dao.impl.AdminDaoImpl;
import com.recorder.mall.entity.Admin;
import com.recorder.mall.service.AdminService;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class AdminServiceImpl implements AdminService {
    private AdminDao adminDao = new AdminDaoImpl();

    @Override
    public Admin login(Admin admin) {
        return adminDao.queryAdminByUsernameAndPassword(admin.getUsername(), admin.getPassword());
    }
}
