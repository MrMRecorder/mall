package com.recorder.mall.dao;

import com.recorder.mall.entity.Order;
import com.recorder.mall.entity.OrderItem;

import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface OrderItemDao {

    public int saveOrderItem(OrderItem orderItem);
    public List<OrderItem> queryOrderItemByOrderId(String OrderId);

}
