package com.recorder.mall.web; /**
 * @author 紫英
 * @version 1.0
 * @discription 后台家具管理界面相关
 */

import com.recorder.mall.entity.Furn;
import com.recorder.mall.entity.Page;
import com.recorder.mall.service.FurnService;
import com.recorder.mall.service.impl.FurnServiceImpl;
import com.recorder.mall.utils.DataUtils;
import com.recorder.mall.utils.WebUtils;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class FurnServlet extends BasicServlet {
    private FurnService furnService = new FurnServiceImpl();

    /**
     * 返回家具信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("list 被调用");
        List<Furn> furns = furnService.queryFurns();
        //将集合放入request域中
        request.setAttribute("furn", furns);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }

    //添加家具
    protected void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Furn furn = new Furn();
        if (ServletFileUpload.isMultipartContent(request)) {
            //2. 创建 DiskFileItemFactory 对象, 用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //3. 创建一个解析上传数据的工具对象
            ServletFileUpload servletFileUpload =
                    new ServletFileUpload(diskFileItemFactory);
            //解决接收到文件名是中文乱码问题
            servletFileUpload.setHeaderEncoding("utf-8");

            //4. 关键的地方, servletFileUpload 对象可以把表单提交的数据text / 文件
            //   将其封装到 FileItem 文件项中
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //遍历，并分别处理=> 自然思路
                for (FileItem fileItem : list) {
                    //判断是不是一个文件=> 你是OOP程序员
                    if (fileItem.isFormField()) {//如果是true就是文本 input text
                        String fieldName = fileItem.getFieldName();
                        String name = fileItem.getString("utf-8");
                        if ("name".equals(fieldName)) {
                            furn.setName(name);
                        } else if ("maker".equals(fieldName)) {
                            furn.setMaker(name);
                        } else if ("price".equals(fieldName)) {
                            furn.setPrice(new BigDecimal(name));
                        } else if ("sales".equals(fieldName)) {
                            furn.setSales(new Integer(name));
                        } else if ("stock".equals(fieldName)) {
                            furn.setStock(new Integer(name));
                        }

                    } else {
                        String name = fileItem.getName();
                        if (name != null) {
                            String filePath = "/" + WebUtils.FURN_IMG_PATH;
                            //2. 获取到完整目录 [io/servlet基础]
                            //  这个目录是和你的web项目运行环境绑定的. 是动态.
                            String fileRealPath =
                                    request.getServletContext().getRealPath(filePath);
                            //System.out.println("fileRealPath=" + fileRealPath);

                            //3. 创建这个上传的目录=> 创建目录?=> Java基础
                            File fileRealPathDirectory = new File(fileRealPath + "/" + WebUtils.getYearMonthDay());
                            if (!fileRealPathDirectory.exists()) {//不存在，就创建
                                fileRealPathDirectory.mkdirs();//创建
                            }

                            //4. 将文件拷贝到fileRealPathDirectory目录
                            //   构建一个上传文件的完整路径 ：目录+文件名
                            //   对上传的文件名进行处理, 前面增加一个前缀，保证是唯一即可, 不错
                            name = UUID.randomUUID().toString() + "_" + name;
                            String fileFullPath = fileRealPathDirectory + "/" + name;
                            furn.setImgPath(WebUtils.FURN_IMG_PATH + "/" + WebUtils.getYearMonthDay() + name);
                            fileItem.write(new File(fileFullPath));//保存文件
                            fileItem.getOutputStream().close();//关闭流
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println(furn);
        furnService.addFurn(furn);
        response.sendRedirect(request.getContextPath() + "/manage/furn?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    protected void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        furnService.delFurnById(id);
        System.out.println("=============================");
        response.sendRedirect(request.getContextPath() + "/manage/furn?action=page&pageNo=" + request.getParameter("pageNo"));
    }

    /**
     * 修改时的家具信息回显
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void showFurn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        //将得到的对象放入到request域中
        request.setAttribute("furn", furn);
        request.getRequestDispatcher("/views/manage/furn_update.jsp").forward(request, response);
    }

    /**
     * 修改家具信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = DataUtils.parseInt(request.getParameter("id"), 0);
        Furn furn = furnService.queryFurnById(id);
        if (null == furn) return;
        //1. 判断是不是文件表单(enctype="multipart/form-data")
        if (ServletFileUpload.isMultipartContent(request)) {
            //2. 创建 DiskFileItemFactory 对象, 用于构建一个解析上传数据的工具对象
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            //3. 创建一个解析上传数据的工具对象
            ServletFileUpload servletFileUpload =
                    new ServletFileUpload(diskFileItemFactory);
            //解决接收到文件名是中文乱码问题
            servletFileUpload.setHeaderEncoding("utf-8");

            //4. 关键的地方, servletFileUpload 对象可以把表单提交的数据text / 文件
            //   将其封装到 FileItem 文件项中
            try {
                List<FileItem> list = servletFileUpload.parseRequest(request);
                //遍历，并分别处理=> 自然思路
                for (FileItem fileItem : list) {
                    //判断是不是一个文件=> 你是OOP程序员
                    if (fileItem.isFormField()) {//如果是true就是文本 input text
                        String fieldName = fileItem.getFieldName();
                        String name = fileItem.getString("utf-8");
                        if ("name".equals(fieldName)) {
                            furn.setName(name);
                        } else if ("maker".equals(fieldName)) {
                            furn.setMaker(name);
                        } else if ("price".equals(fieldName)) {
                            furn.setPrice(new BigDecimal(name));
                        } else if ("sales".equals(fieldName)) {
                            furn.setSales(new Integer(name));
                        } else if ("stock".equals(fieldName)) {
                            furn.setStock(new Integer(name));
                        }

                    } else {
                        String name = fileItem.getName();
                        if (name != null) {
                            String filePath = "/" + WebUtils.FURN_IMG_PATH;
                            //2. 获取到完整目录 [io/servlet基础]
                            //  这个目录是和你的web项目运行环境绑定的. 是动态.
                            String fileRealPath =
                                    request.getServletContext().getRealPath(filePath);
                            //System.out.println("fileRealPath=" + fileRealPath);

                            //3. 创建这个上传的目录=> 创建目录?=> Java基础
                            File fileRealPathDirectory = new File(fileRealPath + "/" + WebUtils.getYearMonthDay());
                            if (!fileRealPathDirectory.exists()) {//不存在，就创建
                                fileRealPathDirectory.mkdirs();//创建
                            }

                            //4. 将文件拷贝到fileRealPathDirectory目录
                            //   构建一个上传文件的完整路径 ：目录+文件名
                            //   对上传的文件名进行处理, 前面增加一个前缀，保证是唯一即可, 不错
                            name = UUID.randomUUID().toString() + "_" + name;
                            String fileFullPath = fileRealPathDirectory + "/" + name;
                            furn.setImgPath(WebUtils.FURN_IMG_PATH + "/" + WebUtils.getYearMonthDay() + name);


                            fileItem.write(new File(fileFullPath));//保存文件
                            fileItem.getOutputStream().close();//关闭流
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        furnService.updateFurn(furn);
        response.sendRedirect(request.getContextPath() + "/manage/furn?action=page&pageNo=" + request.getParameter("pageNo"));

    }

    /**
     * 分页功能
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int pageNo = DataUtils.parseInt(request.getParameter("pageNo"), 1);
        int pageSize = DataUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Furn> page = furnService.page(pageNo, pageSize);
        int pageTotalCount = page.getPageTotalCount();
        int totalRow = page.getTotalRow();
        //System.out.println("pageNo-" + pageNo + " pagesize-"+pageSize+" pageTotalCount-"+pageTotalCount+" totalRow-"+totalRow);
        //如果删掉了每页的最后一个则返回显示上一页
        if (totalRow % pageSize == 0 && pageNo > pageTotalCount) pageNo -= 1;
        page = furnService.page(pageNo, pageSize);
        request.setAttribute("page", page);
        request.getRequestDispatcher("/views/manage/furn_manage.jsp").forward(request, response);
    }


}
