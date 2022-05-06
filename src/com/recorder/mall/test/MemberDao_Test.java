package com.recorder.mall.test;

import com.recorder.mall.dao.MemberDao;
import com.recorder.mall.dao.impl.MemberDaoImpl;
import com.recorder.mall.entity.Member;
import org.junit.jupiter.api.Test;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class MemberDao_Test {

    private MemberDao memberDao = new MemberDaoImpl();

    @Test
    public void queryMemberByUsername() {
        System.out.println(memberDao.queryMemberByUsername("along"));
    }

    @Test
    public void saveMember() {
        Member member = new Member(null, "小丁猫", "zy", "aaa@qq.com");
        if ( memberDao.saveMember(member) > 0){
            System.out.println("添加成功！");
        }else {
            System.out.println("添加失败！");
        }

    }

}
