package com.recorder.mall.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription 购物车JavaBean——用来存放具体的家具项目（CartItem）
 * 没有使用数据库，是存在session中的
 */
public class Cart {
    //存放家具项的hashmap
    private HashMap<Integer, CartItem> items = new HashMap<>();

    public HashMap<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(HashMap<Integer, CartItem> items) {
        this.items = items;
    }


    public List<CartItem> getItemsList() {
        List<CartItem> cartItems = new ArrayList<>();
        for (Integer i : items.keySet()) {
            CartItem cartItem = items.get(i);
            cartItems.add(cartItem);
        }
        return cartItems;
    }

    public Integer getTotalCount() {
        //因为在同一次会话中cart对象是不变的所以需要每次重置一下
        int totalCount = 0;
        for (Integer i : items.keySet()) {
            Integer count = items.get(i).getCount();
            totalCount += count;
        }
        return totalCount;
    }

    //购物车总价
    public BigDecimal getTotalPrice() {
        //因为在同一次会话中cart对象是不变的所以需要每次重置一下
        BigDecimal totalPrice = new BigDecimal(0);
        for (Integer i : items.keySet()) {
            CartItem cartItem = items.get(i);
            totalPrice = totalPrice.add(cartItem.getTotalPrice());//记得累加
        }
        return totalPrice;
    }

    public void add(CartItem cartItem) {

        //先判断一下要添加的商品是否已经存在
        CartItem item = items.get(cartItem.getId());
        if (null == item) {

            //如果不存在就直接添加
            items.put(cartItem.getId(), cartItem);

        } else {
            //如果已经存在就增加数量和总价格(在通过id获取到的item里添加传进来的cartItem的数值)
            item.setCount(item.getCount() + 1);
            //bigdecimal类型的数据只能通过方法来计算
            item.setTotalPrice(item.getTotalPrice().add(cartItem.getPrice()));
        }
    }

    /**
     * 修改购物车中指定商品的数量
     *
     * @param id
     * @param count
     */
    public void updateCount(int id, int count) {

        CartItem item = items.get(id);
        if (null != item) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }

    }

    //删除某个商品
    public void delItem(int id) {
        items.remove(id);
    }

    //清空购物车
    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}
