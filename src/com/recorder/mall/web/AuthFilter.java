package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription 用户权限验证
 */

import com.google.gson.Gson;
import com.recorder.mall.entity.Admin;
import com.recorder.mall.entity.Member;
import com.recorder.mall.utils.WebUtils;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class AuthFilter implements Filter {
    private List<String> excludedUrls;

    public void init(FilterConfig config) throws ServletException {
        String excludedUrl = config.getInitParameter("excludedUrls");
        excludedUrls = Arrays.asList(excludedUrl.split(","));
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        //得到请求的url
        String url = req.getServletPath();
        if (!excludedUrls.contains(url)) {
            //如果不在需要放行的里面就验证
            Member user = (Member) req.getSession().getAttribute("user");
            if (user == null) {
                //判断是否是ajx请求,因为ajax请求会使请求转发失败
                //原因：ajax发来的request请求和浏览器发来的不是一个请求，
                // 浏览器无法识别ajax请求，需要在Ajax引擎中对返回的json进行处理然后请求界面
                if (!WebUtils.isAjax(req)) {
                    req.getRequestDispatcher("/views/member/login.jsp").forward(request, response);

                } else {
                    HashMap<String, Object> resMap = new HashMap<>();
                    resMap.put("url", "views/member/login.jsp");
                    String jsonStr = new Gson().toJson(resMap);
                    response.getWriter().write(jsonStr);
                }
                return;
            }
        }
        chain.doFilter(request, response);


    }
}
