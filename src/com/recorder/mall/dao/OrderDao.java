package com.recorder.mall.dao;

import com.recorder.mall.entity.Order;

import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface OrderDao {

    public int saveOrder(Order order);
    public List<Order> queryOrderByMemberId(int memberId);

}
