package com.recorder.mall.test;

import com.recorder.mall.entity.Cart;
import com.recorder.mall.entity.CartItem;
import com.recorder.mall.service.OrderService;
import com.recorder.mall.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();
    @Test
    public void save(){

        Cart cart = new Cart();
        cart.add(new CartItem(24,"北 欧 风 格 小 桌 子",new BigDecimal(180),2,new BigDecimal(360)));
        cart.add(new CartItem(25,"北 欧 风 格 小 椅 子",new BigDecimal(180),2,new BigDecimal(360)));
        String s = orderService.saveOrder(cart, 8);
        System.out.println(s);

    }

}
