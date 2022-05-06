package com.recorder.mall.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

/**
 * @author 紫英
 * @version 1.0
 * @discription 用于封装JavaBean
 */
public class DataUtils {

    public static <T> T copyParamToBean(Map value, T bean) {
        try {
            BeanUtils.populate(bean, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bean;
    }
    //转化为int
    public static int parseInt(String s,int defVal){
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            System.out.println("格式转换异常" + s);
        }
        return defVal;
    }

}
