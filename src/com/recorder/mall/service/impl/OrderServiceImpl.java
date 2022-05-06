package com.recorder.mall.service.impl;

import com.recorder.mall.dao.FurnDao;
import com.recorder.mall.dao.OrderDao;
import com.recorder.mall.dao.OrderItemDao;
import com.recorder.mall.dao.impl.FurnDaoImpl;
import com.recorder.mall.dao.impl.OrderDaoImpl;
import com.recorder.mall.dao.impl.OrderItemImpl;
import com.recorder.mall.entity.*;
import com.recorder.mall.service.OrderService;

import java.util.Date;
import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription 生成订单和订单项，需要对三个表进行操作
 */
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao =  new OrderItemImpl();
    private FurnDao furnDao = new FurnDaoImpl();
    @Override
    public String saveOrder(Cart cart, int memberId) {
        //1.根据购物车信息来生成订单对象并保存
        String orderId = System.currentTimeMillis() + "_" + memberId;
        Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, memberId);
        orderDao.saveOrder(order);
        //2.遍历购物车中的商品项来生成对应的订单项并保存
        List<CartItem> items = cart.getItemsList();
        for (CartItem item : items) {
            OrderItem orderItem = new OrderItem(null, item.getName(), item.getPrice(), item.getCount(), item.getTotalPrice(), orderId);
            //System.out.println("orderItem=" + orderItem);
            orderItemDao.saveOrderItem(orderItem);
            //3.更新对应的家具表的销量和库存字段
            Furn furn = furnDao.queryFurnById(item.getId());
            furn.setSales(furn.getSales() + item.getCount());
            furn.setStock(furn.getStock() - item.getCount());
            furnDao.updateFurn(furn);
        }
        //4.清空购物车,防止重复下单
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> queryOrderByMemberId(int memberId) {
        return orderDao.queryOrderByMemberId(memberId);
    }

    @Override
    public List<OrderItem> queryOrderItemByOrderId(String OrderId) {
        return orderItemDao.queryOrderItemByOrderId(OrderId);
    }
}
