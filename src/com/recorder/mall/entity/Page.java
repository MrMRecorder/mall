package com.recorder.mall.entity;

import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription 分页的数据模型（JavaBean）
 */
//泛型用来对不同的JavaBean来分页显示
public class Page<T> {
    public static final Integer PAGE_SIZE = 4;//每页显示几条记录-常量
    private Integer pageSize = PAGE_SIZE;//每页显示几条记录
    private Integer pageNo;//当前页(前端发送)
    private Integer pageTotalCount;//总共多少页(通过pageSize和totalRow计算得到)
    private Integer totalRow;//总共多少数据(行)-db
    private List<T> items;//当前页显示的数据集合-db
    private String url;//分页导航的字符串
    //db相关的数据获取放在dao层

    public Page() {
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotalCount() {
        return pageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        this.pageTotalCount = pageTotalCount;
    }

    public Integer getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(Integer totalRow) {
        this.totalRow = totalRow;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
