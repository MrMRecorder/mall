package com.recorder.mall.dao.impl;

import com.recorder.mall.dao.BasicDao;
import com.recorder.mall.dao.MemberDao;
import com.recorder.mall.entity.Member;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class MemberDaoImpl extends BasicDao<Member> implements MemberDao {

    /**
     * 根据username查询
     * @param username 用户名
     * @return 返回一个member对象，如果不存在返回null
     */
    @Override
    public Member queryMemberByUsername(String username) {
        String sql = "select * from member where username=?";
        return selectSingle(sql, Member.class, username);
    }

    /**
     * 保存一个member对象
     * @param member 传入一个member对象
     * @return 返回受影响行数，没有返回0
     */
    @Override
    public int saveMember(Member member) {
        String sql = "insert into member(username,password,email) values(?,md5(?),?)";
        return dml(sql, member.getUsername(), member.getPassword(), member.getEmail());
    }

    @Override
    public Member queryMemberByUsernameAndPassword(String username, String password) {
        String sql = "select * from member where username=? and password=md5(?)";
        return selectSingle(sql, Member.class, username,password);
    }
}
