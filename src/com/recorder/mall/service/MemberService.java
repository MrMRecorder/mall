package com.recorder.mall.service;

import com.recorder.mall.entity.Member;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface MemberService {

    //判断用户名是否存在
    public boolean ifUsernameExists(String username);

    //注册用户
    public boolean registerMember(Member member);

    //用户登录
    public Member login(Member member);


}
