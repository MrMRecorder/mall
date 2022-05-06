package com.recorder.mall.service.impl;

import com.recorder.mall.dao.FurnDao;
import com.recorder.mall.dao.impl.FurnDaoImpl;
import com.recorder.mall.entity.Furn;
import com.recorder.mall.entity.Page;
import com.recorder.mall.service.FurnService;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class FurnServiceImpl implements FurnService {

    private FurnDao furnDao = new FurnDaoImpl();

    @Override
    public List<Furn> queryFurns() {
        return furnDao.queryFurns();
    }

    @Override
    public int addFurn(Furn furn) {
        return furnDao.addFurn(furn);
    }

    @Override
    public int delFurnById(int id) {
        return furnDao.delFurnById(id);
    }

    @Override
    public Furn queryFurnById(int id) {
        return furnDao.queryFurnById(id);
    }

    @Override
    public int updateFurn(Furn furn) {
        return furnDao.updateFurn(furn);
    }

    @Override
    public Page<Furn> page(int pageNo, int pageSize) {
        Page<Furn> page = new Page<>();
        //private Integer pageSize = PAGE_SIZE;//每页显示几条记录
        page.setPageSize(pageSize);
        //private Integer pageNo;//当前页(前端发送)
        page.setPageNo(pageNo);
        //private Integer totalRow;//总共多少数据(行)-db
        int totalRow = furnDao.getTotalRow();
        page.setTotalRow(totalRow);
        //private Integer pageTotalCount;//总共多少页(通过pageSize和totalRow计算得到)
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize != 0) pageTotalCount += 1;
        page.setPageTotalCount(pageTotalCount);
        //private List<T> items;//当前页显示的数据集合-db
        int begin = (pageNo - 1) * pageSize;
        List<Furn> pageItems = furnDao.getPageItems(begin, pageSize);
        page.setItems(pageItems);
        //private String url;//分页导航的字符串

        return page;
    }

    /**
     * 根据名字返回page对象
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public Page<Furn> pageByName(int pageNo, int pageSize, String name) {
        Page<Furn> page = new Page<>();
        //private Integer pageSize = PAGE_SIZE;//每页显示几条记录
        page.setPageSize(pageSize);
        //private Integer pageNo;//当前页(前端发送)
        page.setPageNo(pageNo);
        //private Integer totalRow;//总共多少数据(行)-db
        int totalRow = furnDao.getTotalRowByName(name);
        page.setTotalRow(totalRow);
        //private Integer pageTotalCount;//总共多少页(通过pageSize和totalRow计算得到)
        int pageTotalCount = totalRow / pageSize;
        if (totalRow % pageSize != 0) pageTotalCount += 1;
        page.setPageTotalCount(pageTotalCount);
        //private List<T> items;//当前页显示的数据集合-db
        int begin = (pageNo - 1) * pageSize;
        List<Furn> pageItems = furnDao.getPageItemsByName(begin, pageSize,name);
        page.setItems(pageItems);
        //private String url;//分页导航的字符串
        return page;
    }

}
