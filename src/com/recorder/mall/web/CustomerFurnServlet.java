package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription 首页游客浏览的数据
 */

import com.recorder.mall.entity.Furn;
import com.recorder.mall.entity.Page;
import com.recorder.mall.service.FurnService;
import com.recorder.mall.service.impl.FurnServiceImpl;
import com.recorder.mall.utils.DataUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class CustomerFurnServlet extends BasicServlet {

    private FurnService furnService = new FurnServiceImpl();

    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furn> page = furnService.page(pageNo, pageSize);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);
    }

    protected void pageByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("pageByName被调用");
        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        String name = request.getParameter("name");
        StringBuilder url = new StringBuilder("customer?action=pageByName");
        //null 和 "" 都看成是空串 ——模糊查询时返回所有
        if (null == name) {
            name = "";
        }
        if (!"".equals(name)){
            url.append("&name=").append(name);
        }
        Page<Furn> page = furnService.pageByName(pageNo, pageSize, name);
        page.setUrl(url.toString());
        request.setAttribute("page", page);
        request.getRequestDispatcher("/views/customer/index.jsp").forward(request, response);
    }


}
