package com.recorder.mall.entity;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */

import java.math.BigDecimal;

/**
 * `id` INT UNSIGNED PRIMARY KEY AUTO_INCREMENT, #id
 * `name` VARCHAR(64) NOT NULL, #家居名
 * `maker` VARCHAR(64) NOT NULL, #制造商
 * `price` DECIMAL(11,2) NOT NULL , #价格 定点数
 * `sales` INT UNSIGNED NOT NULL, #销量
 * `stock` INT UNSIGNED NOT NULL, #库存
 * `img_path` VARCHAR(256) NOT NULL #存放图片的路径
 */
public class Furn {
    private Integer id;
    private String name;
    private String maker;
    private BigDecimal price;
    private Integer sales;
    private Integer stock;
    private String imgPath = "assets/images/product-image/default.jpg";

    public Furn() {
    }

    public Furn(Integer id, String name, String maker, BigDecimal price, Integer sales, Integer stock, String imgPath) {
        this.id = id;
        this.name = name;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if (!(null == imgPath || "".equals(imgPath))) {
            //由于前端暂时没有传图片路径，而imgPath是非空导致执行sql的时候会出错
            this.imgPath = imgPath;
        }
        //这里引出Javabean属性名和数据库中表字段名不一致的问题
        //解决方式：在查询的时候使用别名（使之和JavaBean中属性名保持一致）来解决
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Override
    public String toString() {
        return "Furn{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", maker='" + maker + '\'' +
                ", price=" + price +
                ", sales=" + sales +
                ", stock=" + stock +
                ", imgPath='" + imgPath + '\'' +
                '}';
    }
}
