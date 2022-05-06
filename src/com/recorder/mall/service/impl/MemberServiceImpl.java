package com.recorder.mall.service.impl;

import com.recorder.mall.dao.MemberDao;
import com.recorder.mall.dao.impl.MemberDaoImpl;
import com.recorder.mall.entity.Member;
import com.recorder.mall.service.MemberService;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class MemberServiceImpl implements MemberService {

    private MemberDao memberDao = new MemberDaoImpl();

    /**
     * 判断用户是否存在
     *
     * @param username 用户名
     * @return 存在返回true, 不存在返回false
     */
    @Override
    public boolean ifUsernameExists(String username) {
        Member member = memberDao.queryMemberByUsername(username);
        return member != null;
    }

    @Override
    public boolean registerMember(Member member) {
        return memberDao.saveMember(member) > 0;
    }

    @Override
    public Member login(Member member) {
        return memberDao.queryMemberByUsernameAndPassword(member.getUsername(), member.getPassword());

    }
}
