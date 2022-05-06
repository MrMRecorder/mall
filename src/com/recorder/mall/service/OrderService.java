package com.recorder.mall.service;

import com.recorder.mall.entity.Cart;
import com.recorder.mall.entity.Order;
import com.recorder.mall.entity.OrderItem;

import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface OrderService {
    public String saveOrder(Cart cart,int memberId);
    public List<Order> queryOrderByMemberId(int memberId);
    public List<OrderItem> queryOrderItemByOrderId(String OrderId);

}
