package com.frame.template.common.utils.dateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class DateToCompareUtils {

    public static final String ONE = "1";
    public static final String TWO = "2";
    public static final String THREE = "3";

    public static synchronized ToCompareState DateToCompareUtils(Instant date1, String date2, SimpleDateFormat dateFormat, String state) {
        String substring = date1.toString().substring(0, 10) + " " + date1.toString().substring(11, 19);
        return toCompareSwith(substring, date2, dateFormat, state);
    }

    public static synchronized ToCompareState DateToCompareUtils(Calendar date1, Calendar date2, SimpleDateFormat dateFormat, String state) {
        return toCompareSwith(date1.getCalendarType(), date1.getCalendarType(), dateFormat, state);
    }

    public static synchronized ToCompareState DateToCompareUtils(String date1, String date2, SimpleDateFormat dateFormat, String state) {
        return toCompareSwith(date1, date2, dateFormat, state);
    }

    private static ToCompareState toCompareSwith(String date1, String date2, SimpleDateFormat dateFormat, String state) {
        switch (state) {
            case ONE:
            case THREE:
                return null;
            case TWO:
                return comMpareMethodTwo(date1, date2, dateFormat);
        }
        return new ToCompareState();
    }

    /**
     * 日期比较方法1
     * 通过Date自带的Compare比较
     *
     * @param date1
     * @param date2
     * @param dateFormat
     */
    private static int comMpareMethodOne(String date1, String date2, SimpleDateFormat dateFormat) {
        int result = 200;
        try {
            Date parse = dateFormat.parse(date1);
            Date parse1 = dateFormat.parse(date2);
            result = parse.compareTo(parse1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 日期比较方法2
     * 通过Date自带的before()或者after()方法比较
     *
     * @param date1
     * @param date2
     * @param dateFormat
     */
    private static ToCompareState comMpareMethodTwo(String date1, String date2, SimpleDateFormat dateFormat) {
        ToCompareState toCompareState = new ToCompareState();
        try {
            Date parse = dateFormat.parse(date1);
            Date parse1 = dateFormat.parse(date2);
            //是否小于
            boolean before = parse.before(parse1);
            //是否大于
            boolean after = parse.after(parse1);
            boolean equals = parse.equals(parse1);
            toCompareState.setBefore(before);
            toCompareState.setAfter(after);
            toCompareState.setEquals(equals);
            return toCompareState;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return toCompareState;
    }

    /**
     * 日期比较3
     * 通过日期时间戳进行比较
     *
     * @param date1
     * @param date2
     * @param dateFormat
     */
    private static void comMpareMethodThree(String date1, String date2, SimpleDateFormat dateFormat) {
        try {
            Date parse = dateFormat.parse(date1);
            Date parse1 = dateFormat.parse(date2);
            long time = parse.getTime();
            long time1 = parse1.getTime();
            System.out.println(time > time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
