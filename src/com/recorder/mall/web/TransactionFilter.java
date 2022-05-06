package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription
 */

import com.recorder.mall.utils.JDBCUtilsByDruid;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        try {
            chain.doFilter(request, response);
            JDBCUtilsByDruid.commit();
            System.out.println("事务成功提交");
        } catch (Exception e) {
            JDBCUtilsByDruid.rollback();
            System.out.println("提交失败，已回滚");
            //这里将异常抛给tomcat，让tomcat根据状态码来显示对应的错误信息页面
            throw new RuntimeException(e);
        }
    }
}
