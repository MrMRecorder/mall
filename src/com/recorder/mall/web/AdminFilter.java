package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription 管理员权限验证
 */

import com.recorder.mall.entity.Admin;
import com.recorder.mall.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AdminFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        Admin admin = (Admin) req.getSession().getAttribute("admin");
        if (admin == null) {
            req.getRequestDispatcher("/views/manage/manage_login.jsp").forward(request, response);
            return;
        }
        try {
            chain.doFilter(request, response);
            JDBCUtilsByDruid.commit();
        } catch (IOException e) {
           JDBCUtilsByDruid.rollback();
           throw new RuntimeException(e);
        }


    }
}
