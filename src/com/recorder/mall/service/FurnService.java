package com.recorder.mall.service;

import com.recorder.mall.dao.FurnDao;
import com.recorder.mall.entity.Furn;
import com.recorder.mall.entity.Page;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public interface FurnService {

    public List<Furn> queryFurns();
    public int addFurn(Furn furn);
    public int delFurnById(int id);
    public Furn queryFurnById(int id);
    public int updateFurn(Furn furn);
    public Page<Furn> page(int pageNo,int pageSize);
    public Page<Furn> pageByName(int pageNo,int pageSize,String name);



}
