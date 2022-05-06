package com.recorder.mall.utils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @author 紫英
 * @version 1.0
 * @discription
 */
public class WebUtils {

    public static String FURN_IMG_PATH = "assets/images/product-image";

    //判断是否是ajax请求
    //X-Requested-With: XMLHttpRequest
    public static boolean isAjax(HttpServletRequest request){

        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));

    }

    public static String getYearMonthDay() {
        //如何得到当前的日期-> java基础 日期 三代类
        LocalDateTime ldt = LocalDateTime.now();
        int year = ldt.getYear();
        int monthValue = ldt.getMonthValue();
        int dayOfMonth = ldt.getDayOfMonth();
        String yearMonthDay = year + "/" + monthValue + "/" + dayOfMonth + "/";
        return yearMonthDay;
    }
}
