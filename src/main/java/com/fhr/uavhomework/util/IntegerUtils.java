package com.fhr.uavhomework.util;

/**
 * @author FanHuaran
 * @description Integer工具类
 * @create 2018-04-05 15:25
 **/
public class IntegerUtils {

    /**
     * 字符串->整形，格式不合法，返回null
     *
     * @param str
     * @return
     */
    public static Integer tryParseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return null;
        }
    }
}
