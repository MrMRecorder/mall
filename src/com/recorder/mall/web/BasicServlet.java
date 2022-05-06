package com.recorder.mall.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author 紫英
 * @version 1.0
 * @discription 模板设计模式 + 反射 + 动态绑定完成servlet简化（对if-else的优化）
 */
public abstract class BasicServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置解决中文乱码
        req.setCharacterEncoding("utf-8");

        //这里注意要让表单里action的value和要调用的方法名保持一致
        String action = req.getParameter("action");//方法名
        System.out.println("action=" + action);
        try {
            //这里的this指被调用的继承了BasicServlet的对应servlet
            Method declaredMethod = this.getClass().getDeclaredMethod(action,
                    HttpServletRequest.class, HttpServletResponse.class);
            declaredMethod.invoke(this,req,resp);//调用对应类的对应方法
        } catch (Exception e) {
            //这里将异常抛出给过滤器处理
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //重写一下doGet方法并且合并
        doPost(req,resp);
    }
}
