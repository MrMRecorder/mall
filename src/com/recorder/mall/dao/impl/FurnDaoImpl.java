package com.recorder.mall.dao.impl;

import com.recorder.mall.dao.BasicDao;
import com.recorder.mall.dao.FurnDao;
import com.recorder.mall.entity.Furn;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class FurnDaoImpl extends BasicDao<Furn> implements FurnDao {
    @Override
    public List<Furn> queryFurns() {
        String sql = "select `id` , `name` , `maker` , `price` , `sales` , `stock` , `img_path` imgPath from furn";
        return selectMulti(sql, Furn.class);
    }

    @Override
    public int addFurn(Furn furn) {
        String sql = "insert into `furn` ( `id`, `name`, `maker`, `price`, `sales`, `stock`, `img_path` ) values( ?,?,?,?,?,?,?);";
        return dml(sql, furn.getId(),furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath());
    }

    @Override
    public int delFurnById(int id) {
        String sql = "delete from `furn` where id=?";
        return dml(sql, id);
    }

    @Override
    public Furn queryFurnById(int id) {
        String sql = "select `id`, `name` , `maker` , `price` , `sales` , `stock` , `img_path` imgPath from furn where id=?";
        return selectSingle(sql, Furn.class, id);
    }

    @Override
    public int updateFurn(Furn furn) {
        String sql = "update furn set name=?,maker=?,price=?,sales=?,stock=?,img_path=? where id=?";
        return dml(sql, furn.getName(), furn.getMaker(), furn.getPrice(), furn.getSales(), furn.getStock(), furn.getImgPath(), furn.getId());
    }

    @Override
    public int getTotalRow() {
        String sql = "select count(*) from `furn`";
        //因为返回值类型是long所以不能直接返回结果,使用包装类转换一下
        return ((Number) selectScalar(sql)).intValue();
    }

    @Override
    public List<Furn> getPageItems(int begin, int pageSize) {
        //不能写select * 否则图片会变成默认的（因为不加别名的话不一致）
        String sql = "select `id`, `name` , `maker` , `price` , `sales` , `stock` , `img_path` imgPath from `furn` limit ?,?";
        return selectMulti(sql, Furn.class, begin, pageSize);
    }

    @Override
    public int getTotalRowByName(String name) {
        String sql = "select count(*) from `furn` where name like ?";
        //因为返回值类型是long所以不能直接返回结果,使用包装类转换一下
        return ((Number) selectScalar(sql, "%" + name + "%")).intValue();

    }

    @Override
    public List<Furn> getPageItemsByName(int begin, int pageSize, String name) {
        String sql = "select `id`, `name` , `maker` , `price` , `sales` , `stock` , `img_path` imgPath from `furn` where name like ? limit ?,?";
        return selectMulti(sql, Furn.class, "%" + name + "%", begin, pageSize);
    }
}
