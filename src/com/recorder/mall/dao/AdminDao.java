package com.recorder.mall.dao;

import com.recorder.mall.entity.Admin;
import com.recorder.mall.entity.Member;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface AdminDao {

    //管理员登陆验证
    public Admin queryAdminByUsernameAndPassword(String username, String password);

}
