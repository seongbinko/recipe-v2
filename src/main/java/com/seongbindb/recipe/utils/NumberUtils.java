package com.seongbindb.recipe.utils;

import java.text.DecimalFormat;

/**
 * <pre>
 * 숫자처리를 하기위한 클래스
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:32:52
 * @Version : 1.0
 */
public class NumberUtils {

    private static final DecimalFormat numberWithComma = new DecimalFormat("#,###");

    /**
     * <pre>
     * 천단위마다 숫자에 콤마를 붙여 문자열로 반환.
     * </pre>
     *
     * @param number
     * @return numberWithComma.format(number);
     */
    public static String numberWithComma(Integer number) {
        if (number == null) {
            return null;
        }
        return numberWithComma.format(number);
    }

    /**
     * <pre>
     * 페이지네이션을 위한 ceil값을 구하는 것
     * </pre>
     *
     * @param num1
     * @param num2
     * @return (int) Math.ceil(num1/num2);
     */
    public static int ceilForPagination(double num1, double num2) {
        return (int) Math.ceil(num1 / num2);
    }
}
