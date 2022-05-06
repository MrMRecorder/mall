package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription
 */

import com.recorder.mall.entity.Cart;
import com.recorder.mall.entity.Member;
import com.recorder.mall.entity.Order;
import com.recorder.mall.entity.OrderItem;
import com.recorder.mall.service.OrderService;
import com.recorder.mall.service.impl.OrderServiceImpl;
import com.recorder.mall.utils.DataUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public class OrderServlet extends BasicServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 生成订单
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void saveOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (null == cart || cart.getItems().size() == 0){
            //如果购物车为空返回首页继续购买
            //判断一下size防止清空购物车后生成空订单
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            return;
        }
        Member user = (Member) request.getSession().getAttribute("user");
        if (null == user) {
            //如果没有登录转发到登陆页面
            request.getRequestDispatcher("/views/member/login.jsp").forward(request, response);
            return;
        }
        Integer id = user.getId();
        String orderId = orderService.saveOrder(cart, id);
        request.getSession().setAttribute("orderId",orderId);
        response.sendRedirect(request.getContextPath() + "/views/order/checkout.jsp");

    }
    //根据用户id显示订单列表
    protected void orderList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = DataUtils.parseInt(req.getParameter("id"), 0);
        List<Order> orders = orderService.queryOrderByMemberId(id);
        req.getSession().setAttribute("orders",orders);
        req.getRequestDispatcher("views/order/order.jsp").forward(req,resp);

    }
    protected void orderItemList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String orderId = req.getParameter("orderId");
        List<OrderItem> orderItems = orderService.queryOrderItemByOrderId(orderId);
        int totalCount = 0;
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderItem orderItem : orderItems) {

            Integer count = orderItem.getCount();
            totalCount += count;
            System.out.println("totalPrice" + orderItem.getTotalPrice());
            BigDecimal i = orderItem.getPrice().multiply(new BigDecimal(count));
            totalPrice=totalPrice.add(i);

        }
        req.getSession().setAttribute("orderItemsTotalCount",totalCount);
        req.getSession().setAttribute("orderItemsTotalPrice",totalPrice);
        req.getSession().setAttribute("orderItems",orderItems);
        req.getRequestDispatcher("views/order/order_detail.jsp").forward(req,resp);

    }
}
