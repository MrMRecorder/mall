package com.recorder.mall.dao;

import com.recorder.mall.entity.Member;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface MemberDao {

    //根据username返回一个member对象
    public Member queryMemberByUsername(String username);

    //保存一个member对象到数据库中
    public int saveMember(Member member);

    //登陆验证
    public Member queryMemberByUsernameAndPassword(String username, String password);
}
