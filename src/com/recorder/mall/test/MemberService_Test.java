package com.recorder.mall.test;

import com.recorder.mall.entity.Member;
import com.recorder.mall.service.MemberService;
import com.recorder.mall.service.impl.MemberServiceImpl;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.junit.jupiter.api.Test;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class MemberService_Test {
    private MemberService memberService = new MemberServiceImpl();

    @Test
    public void ifUsernameExists() {
        if (memberService.ifUsernameExists("along")) {
            System.out.println("用户已存在");
        } else {
            System.out.println("用户不存在");
        }
    }

    @Test
    public void registerMember() {
        Member member = new Member(null, "小丁猫", "zy", "aaa@qq.com");
        if (memberService.registerMember(member)) {
            System.out.println("用户注册成功");
        } else {
            System.out.println("用户注册失败");
        }

    }

}
