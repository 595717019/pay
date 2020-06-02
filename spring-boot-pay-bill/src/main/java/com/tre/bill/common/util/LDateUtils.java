package com.tre.bill.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description: 日期转换工具类
 * @author: 10097454
 **/
public class LDateUtils {

    private static final Logger logger = LoggerFactory.getLogger(LDateUtils.class);
    private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
    private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");

    /**
     * 获取YYYY格式
     */
    public static String getSdfTimes() {
        return sdfTimes.format(new Date());
    }

    /**
     * 获取YYYY格式
     */
    public static String getYear() {
        return sdfYear.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD格式
     */
    public static String getDay() {
        return sdfDay.format(new Date());
    }

    /**
     * 获取YYYYMMDD格式
     */
    public static String getDays() {
        return sdfDays.format(new Date());
    }

    /**
     * 获取YYYY-MM-DD HH:mm:ss格式
     */
    public static String getTime() {
        return sdfTime.format(new Date());
    }

    /**
     * @return {@link null}
     * @Author 10097454
     * @Date 2020/05/21
     * @Description 日期比较 ， 如果s > e 返回true 否则返回false
     **/
    public static boolean compareDate(String s, String e) {
        if (fomatDate(s) == null || fomatDate(e) == null) {
            return false;
        }
        return fomatDate(s).getTime() > fomatDate(e).getTime();
    }

    /**
     * 格式化日期
     *
     * @return 2020-05-22
     */
    public static String fomatDateString(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.format(fmt.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date fomatDate(String date) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return fmt.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验日期是否合法
     *
     * @return
     */
    public static boolean isValidDate(String s) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            fmt.parse(s);
            return true;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return false;
        }
    }

    /**
     * @param startTime
     * @param endTime
     * @return
     */
    public static int getDiffYear(String startTime, String endTime) {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        try {
            int years = (int) (((fmt.parse(endTime).getTime() - fmt.parse(startTime).getTime()) / (1000 * 60 * 60 * 24)) / 365);
            return years;
        } catch (Exception e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            return 0;
        }
    }

    /**
     * <li>功能描述：时间相减得到天数
     *
     * @param beginDateStr
     * @param endDateStr
     * @return long
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr, String endDateStr) {
        long day = 0;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = null;
        Date endDate = null;

        try {
            beginDate = format.parse(beginDateStr);
            endDate = format.parse(endDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = (endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
        //System.out.println("相隔的天数="+day);

        return day;
    }

    /**
     * 得到n天之后的日期
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 得到n天之后是周几
     */
    public static String getAfterDayWeek(String days) {
        int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * @param str
     * @param format
     * @return
     */
    public static Date stringToDate(String str, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            logger.error("stringToDate", e);
        }
        return date;
    }

    /**
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        String strDate = null;
        try {
            if (date != null) {
                strDate = dateFormat.format(date);
            }
        } catch (Exception e) {
            logger.error("stringToDate", e);
        }
        return strDate;
    }

    /**
     * 例：2017/11/12 23:00:23==>2017/11/12 00:00:00
     *
     * @return current time of a day at 0:00
     */
    public static Date getStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        return start;
    }

    /**
     * 例：2017/11/12 23:00:23==>2017/11/13 00:00:00
     *
     * @return current time of a day at 23:59
     */
    public static Date getEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date end = calendar.getTime();
        return end;
    }

    public static String nextDate(Integer days, String _date, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Calendar cl = Calendar.getInstance();
        Date date = null;

        try {
            date = (Date) sdf.parse(_date);
        } catch (ParseException e) {
            logger.error("stringToDate", e);
        }
        cl.setTime(date);

        cl.add(Calendar.DAY_OF_YEAR, days);

        date = cl.getTime();
        return sdf.format(date);
    }

    public static String preDate(Integer days, String _date, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Calendar cl = Calendar.getInstance();
        Date date = null;

        try {
            date = (Date) sdf.parse(_date);
        } catch (ParseException e) {
            logger.error("stringToDate", e);
        }
        cl.setTime(date);

        // 时间减一天
        cl.add(Calendar.DAY_OF_MONTH, -days);

        date = cl.getTime();
        return sdf.format(date);
    }

    public static Date nextDate(Integer days, Date _date) {

        Calendar cl = Calendar.getInstance();
        Date date = null;

        date = _date;
        cl.setTime(date);

        // 时间减一天
        cl.add(Calendar.DAY_OF_MONTH, days);

        date = cl.getTime();
        return date;
    }

    public static Date preDate(Integer days, Date _date) {

        Calendar cl = Calendar.getInstance();
        Date date = null;

        date = _date;
        cl.setTime(date);

        // 时间减一天
        cl.add(Calendar.DAY_OF_MONTH, -days);

        date = cl.getTime();
        return date;
    }

    public static int getDiscrepantDays(String dateStart, String dateEnd, String type) {
        SimpleDateFormat sdf = new SimpleDateFormat(type);
        Date dateStartD = new Date();
        Date dateEndD = new Date();
        try {
            dateStartD = (Date) sdf.parse(dateStart);
            dateEndD = (Date) sdf.parse(dateEnd);
        } catch (ParseException e) {
            logger.error("stringToDate", e);
        }

        return (int) ((dateEndD.getTime() - dateStartD.getTime()) / 1000 / 60 / 60 / 24);
    }
}
