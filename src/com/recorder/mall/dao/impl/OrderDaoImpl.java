package com.recorder.mall.dao.impl;

import com.recorder.mall.dao.BasicDao;
import com.recorder.mall.dao.OrderDao;
import com.recorder.mall.entity.Order;

import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class OrderDaoImpl extends BasicDao<Order> implements OrderDao {
    //添加一个订单到数据库中
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `order` (`id`,`create_time`,`price`,`status`,`member_id`) VALUES(?,?,?,?,?)";
        return dml(sql,order.getId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getMemberId());
    }

    @Override
    public List<Order> queryOrderByMemberId(int memberId) {
        String sql = "select `id`,`create_time` createTime,`price`,`status`,`member_id` from `order` where `member_id`=?";
        return selectMulti(sql,Order.class,memberId);
    }
}
