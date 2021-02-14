package com.seongbindb.recipe.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <pre>
 * 날짜를 포맷에 맞게 바꾸기 위한 클래스
 * </pre>
 *
 * @Company : SeongbinDB
 * @Author : Seongbin Ko
 * @Date : 2021. 2. 14. 오전 11:31:54
 * @Version : 1.0
 */
public class DateUtils {

    private static final SimpleDateFormat normalDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat yearDateFormat = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat monthDateFormat = new SimpleDateFormat("MM");
    private static final SimpleDateFormat fullDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


    /**
     * <pre>
     * 년 월 일로 문자열 반환
     * </pre>
     *
     * @param date
     * @return yyyy-MM-dd
     */
    public static String dateToString(Date date) {
        if (date == null) {
            return "";
        }
        return normalDateFormat.format(date);
    }

    /**
     * <pre>
     * 년 월 일 시 분 초 문자열 반환
     * </pre>
     *
     * @param date
     * @return "yyyy-MM-dd HH:mm"
     */
    public static String dateToStringWithTime(Date date) {
        if (date == null) {
            return "";
        }
        return fullDateFormat.format(date);
    }

    /**
     * <pre>
     * 문자열을 yyyy-MM-dd의 날짜로 변환
     * </pre>
     *
     * @param dateString
     * @return date(yyyy - MM - dd)
     * @throws Exception
     */
    public static Date stringToDate(String dateString) throws Exception {
        if (dateString == null) {
            return null;
        }
        Date date = normalDateFormat.parse(dateString);
        return date;
    }

    /**
     * <pre>
     * 문자열을 월의 날짜로 변환
     * </pre>
     *
     * @param dateString
     * @return 월
     * @throws Exception
     */
    public static Date stringToDate2(String dateString) throws Exception {
        if (dateString == null) {
            return null;
        }
        Date date = monthDateFormat.parse(dateString);
        return date;
    }

    /**
     * <pre>
     * 문자열을 년의 날짜로 변환
     * </pre>
     *
     * @param date
     * @return 년
     */
    public static String dateToYear(Date date) {
        if (date == null) {
            return "";
        }
        return yearDateFormat.format(date);
    }
}
