package com.recorder.mall.dao.impl;

import com.recorder.mall.dao.BasicDao;
import com.recorder.mall.dao.OrderItemDao;
import com.recorder.mall.entity.OrderItem;

import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class OrderItemImpl extends BasicDao<OrderItem> implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO `order_item`(`id`,`name`,`price`,`count`,`total_price`,`order_id`)" +
                "VALUES(null,?,?,?,?,?)";
        return dml(sql, orderItem.getName(), orderItem.getPrice(), orderItem.getCount(), orderItem.getTotalPrice(), orderItem.getOrderId());
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String OrderId) {
        String sql = "select `id`,`name`,`price`,`count`,`total_price` totalPrice,`order_id` from `order_item` where order_id=?";
        return selectMulti(sql,OrderItem.class,OrderId);
    }
}
