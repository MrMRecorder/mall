package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription
 */

import com.google.gson.Gson;
import com.recorder.mall.entity.Cart;
import com.recorder.mall.entity.CartItem;
import com.recorder.mall.entity.Furn;
import com.recorder.mall.service.FurnService;
import com.recorder.mall.service.impl.FurnServiceImpl;
import com.recorder.mall.utils.DataUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;

public class CartServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();

    //添加商品到购物车
    @Deprecated
    protected void addItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        if (null == furn) {
            //者商品不存在直接返回
            return;
        }
        if (furn.getStock() <= 0) {
            //库存为0返回购物界面
            response.sendRedirect(request.getHeader("Referer"));
        }
        //通过得到的furn来构建item对象
        CartItem cartItem = new CartItem(id, furn.getName(), furn.getPrice(), 1, furn.getPrice());//一次添加一个
        //获取session中的cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (null == cart) {
            //第一次添加的话构建cart对象
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //添加商品到session的cart中
        cart.add(cartItem);
        //System.out.println(cart);
        response.sendRedirect(request.getHeader("Referer"));


    }

    //添加商品到购物车 -> 返回json格式数据刷新购物车显示的数量
    protected void addItemByAjax(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        if (null == furn) {
            //者商品不存在直接返回
            return;
        }
        if (furn.getStock() <= 0) {
            //库存为0返回购物界面
            response.sendRedirect(request.getHeader("Referer"));
        }
        //通过得到的furn来构建item对象
        CartItem cartItem = new CartItem(id, furn.getName(), furn.getPrice(), 1, furn.getPrice());//一次添加一个
        //获取session中的cart对象
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (null == cart) {
            //第一次添加的话构建cart对象
            cart = new Cart();
            request.getSession().setAttribute("cart", cart);
        }
        //添加商品到session的cart中
        cart.add(cartItem);
        System.out.println(cart);
        //response.sendRedirect(request.getHeader("Referer"));
        HashMap<String, Object> resMap = new HashMap<>();
        resMap.put("cartTotalCount",cart.getTotalCount());
        String jsonStr = new Gson().toJson(resMap);
        //返回json格式的数据
        response.getWriter().write(jsonStr);

    }


    //更新商品数量
    protected void updateItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        int count = DataUtils.parseInt(req.getParameter("count"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.updateCount(id, count);
        }
        //返回到请求发起的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    //删除某个商品
    protected void delItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.delItem(id);
        }
        //返回到请求发起的页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    //清空购物车
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (null != cart) {
            cart.clear();
        }
        //返回到请求发起的页面
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
