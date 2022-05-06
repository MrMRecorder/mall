package com.recorder.mall.dao;

import com.recorder.mall.entity.Furn;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface FurnDao {

    public List<Furn> queryFurns(); //查询所有家具
    //添加家具
    public int addFurn(Furn furn);
    //删除家具
    public int delFurnById(int id);
    //修改时的家具回显
    public Furn queryFurnById(int id);
    //修改家具信息
    public int updateFurn(Furn furn);
    //获取总数据行
    public int getTotalRow();
    //当前页显示的数据集合
    //pageNo:从第页数据开始
    //pageSize:每页显示几条数据
    public List<Furn> getPageItems(int begin,int pageSize);
    //根据名字获取总记录数
    public int getTotalRowByName(String name);
    //根据名字返回PageItems
    public List<Furn> getPageItemsByName(int begin,int pageSize,String name);


}
