package com.recorder.mall.test;

import com.recorder.mall.dao.FurnDao;
import com.recorder.mall.dao.impl.FurnDaoImpl;
import com.recorder.mall.entity.Furn;
import com.recorder.mall.entity.Page;
import com.recorder.mall.service.FurnService;
import com.recorder.mall.service.impl.FurnServiceImpl;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class FurnServiceTest {

    private FurnDao furnDao = new FurnDaoImpl();
    private FurnService furnService = new FurnServiceImpl();

    @Test
    public void queryFurns() {
        System.out.println(furnDao.queryFurns());
    }

    @Test
    public void add() {
        Furn furn = new Furn(null, "小沙发", "along家具", new BigDecimal(123.50), 100, 15, "assets/images/product-image/along.jpg");
        System.out.println("添加" + furnDao.addFurn(furn) + "条数据成功！");

    }
    @Test
    public void queryFurnById(){
        Furn furn = furnDao.queryFurnById(1);
        System.out.println(furn);
    }
    @Test
    public void getPageItems(){
        System.out.println(furnDao.getPageItems(1,2));
    }
    @Test
    public void page(){
        Page<Furn> page = furnService.page(1, 3);
        System.out.println(page);
    }
    @Test
    public void getTotalRowByName(){
        int i = furnDao.getTotalRowByName("北 欧");
        System.out.println(i);
    }
    @Test
    public void getPageItemsByName(){
        System.out.println(furnDao.getPageItemsByName(0,3,"北 欧"));
    }
    @Test
    public void pageByName(){
        Page<Furn> page = furnService.pageByName(1, 3, "北 欧");
        System.out.println(page);
    }
}
