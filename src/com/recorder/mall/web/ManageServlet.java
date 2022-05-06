package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription
 */

import com.recorder.mall.entity.Admin;
import com.recorder.mall.entity.Member;
import com.recorder.mall.service.AdminService;
import com.recorder.mall.service.MemberService;
import com.recorder.mall.service.impl.AdminServiceImpl;
import com.recorder.mall.service.impl.MemberServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ManageServlet extends BasicServlet {
    private AdminService adminService = new AdminServiceImpl();

    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = adminService.login(new Admin(null, username, password));
        if (admin != null) {
            System.out.println("管理员" + admin + "登陆成功");
            //放入session中
            request.getSession().setAttribute("admin", admin);
            request.getRequestDispatcher("/views/manage/manage_menu.jsp").forward(request, response);
        } else {
            System.out.println("登陆失败");
            request.setAttribute("msg", "用户名或密码错误");//错误信息
            request.setAttribute("username", username);//用于回显用户名
            request.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request, response);

        }
    }

    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.invalidate();//销毁当前用户的session
        resp.sendRedirect(req.getContextPath() + "/index.jsp");//重定向
    }
}
