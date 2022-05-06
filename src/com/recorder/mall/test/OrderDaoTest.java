package com.recorder.mall.test;

import com.recorder.mall.dao.OrderDao;
import com.recorder.mall.dao.impl.OrderDaoImpl;
import com.recorder.mall.entity.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class OrderDaoTest {
    private OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder(){
        Order order = new Order("sno00002", new Date(), new BigDecimal(200), 0, 8);
        int i = orderDao.saveOrder(order);
        System.out.println(i);
    }
}
