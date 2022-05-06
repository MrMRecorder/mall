package com.recorder.mall.test;

import com.recorder.mall.entity.Cart;
import com.recorder.mall.entity.CartItem;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class CartTest {

    private Cart cart = new Cart();
    @Test
    public void add(){
        cart.add(new CartItem(1,"沙发",new BigDecimal(30.00),5,new BigDecimal(150.00)));
        cart.add(new CartItem(2,"桌子",new BigDecimal(20.00),5,new BigDecimal(100.00)));
        System.out.println(cart);
        //todo
    }

}
