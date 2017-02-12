package com.excellence.basetoolslibrary.utils;

/**
 * Created by ZhangWei on 2017/1/24.
 */

import android.text.format.DateUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * ʱ�����
 */
public class TimeUtils
{
    /**
     * <pre>
     *                     yyyy-MM-dd 1969-12-31
     *                     yyyy-MM-dd 1970-01-01
     *               yyyy-MM-dd HH:mm 1969-12-31 16:00
     *               yyyy-MM-dd HH:mm 1970-01-01 00:00
     *              yyyy-MM-dd HH:mmZ 1969-12-31 16:00-0800
     *              yyyy-MM-dd HH:mmZ 1970-01-01 00:00+0000
     *       yyyy-MM-dd HH:mm:ss.SSSZ 1969-12-31 16:00:00.000-0800
     *       yyyy-MM-dd HH:mm:ss.SSSZ 1970-01-01 00:00:00.000+0000
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ 1969-12-31T16:00:00.000-0800
     *     yyyy-MM-dd'T'HH:mm:ss.SSSZ 1970-01-01T00:00:00.000+0000
     *     yyyy-MM-dd HH:mm:ss E      2017-02-09 23:20:26 ����  (* E ����ϵͳ���Ըı䣬Ӣ������Thu)
     *     yyyy-MM-dd HH:mm:ss EEEE   2017-02-09 23:20:26 ������ (* EEEE ����ϵͳ���Ըı䣬Ӣ������Thursday)
     * </pre>
     */
    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public static final String DAY_PATTERN = "yyyy-MM-dd";
    public static final String WEEK_PATTERN = "EEEE";

    private static final String[] CHINESE_ZODIAC = { "��", "��", "��", "��", "��", "ţ", "��", "��", "��", "��", "��", "��" };

    private final static String[] ZODIAC = new String[] { "Ħ����", "ˮƿ��", "˫����", "������", "��ţ��", "˫����", "��з��", "ʨ����", "��Ů��", "�����", "��Ы��", "������", "Ħ����" };
    private final static int[] ZODIAC_FLAGS = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };

    public static final int SEC = 1000;
    public static final int MIN = 60 * 1000;
    public static final int HOUR = 60 * 60 * 1000;
    public static final int DAY = 24 * 60 * 60 * 1000;

    public enum TimeUnit{
        MSEC,
        SEC,
        MIN,
        HOUR,
        DAY
    }

    /**
     * ʱ���תʱ���ַ���
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param millisec ����ʱ���
     * @param pattern �Զ�ʱ���ʽ
     * @return ʱ���ַ���
     */
    public static String millisec2String(long millisec, String pattern)
    {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(new Date(millisec));
    }

    /**
     * ʱ���תʱ���ַ���
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param millisec ����ʱ���
     * @return ʱ���ַ���
     */
    public static String millisec2String(long millisec)
    {
        return millisec2String(millisec, DEFAULT_PATTERN);
    }

    /**
     * ʱ���ַ���תDate����
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param pattern �Զ�ʱ���ʽ
     * @return Date����
     */
    public static Date string2Date(String time, String pattern)
    {
        return new Date(string2Millisec(time, pattern));
    }

    /**
     * ʱ���ַ���תDate����
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @return Date����
     */
    public static Date string2Date(String time)
    {
        return string2Date(time, DEFAULT_PATTERN);
    }

    /**
     * ʱ���ַ���ת����ʱ���
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param pattern �Զ�ʱ���ʽ
     * @return ����ʱ���
     */
    public static long string2Millisec(String time, String pattern)
    {
        try
        {
            return new SimpleDateFormat(pattern, Locale.getDefault()).parse(time).getTime();
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * ʱ���ַ���ת����ʱ���
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @return ����ʱ���
     */
    public static long string2Millisec(String time)
    {
        return string2Millisec(time, DEFAULT_PATTERN);
    }

    /**
     * Dateתʱ���ַ���
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param date Date����
     * @param pattern �Զ�ʱ���ʽ
     * @return ʱ���ַ���
     */
    public static String date2String(Date date, String pattern)
    {
        return new SimpleDateFormat(pattern, Locale.getDefault()).format(date);
    }

    /**
     * Dateתʱ���ַ���
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param date Date����
     * @return ʱ���ַ���
     */
    public static String date2String(Date date)
    {
        return new SimpleDateFormat(DEFAULT_PATTERN, Locale.getDefault()).format(date);
    }

    /**
     * ��ȡ����ʱ���
     *
     * @param millisec0 ����ʱ���0
     * @param millisec1 ����ʱ���1
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpan(long millisec0, long millisec1, TimeUnit unit)
    {
        long timeSpan = Math.abs(millisec0 - millisec1);
        switch (unit)
        {
            default:
            case MSEC:
                return timeSpan;

            case SEC:
                return timeSpan / SEC;

            case MIN:
                return timeSpan / MIN;

            case HOUR:
                return timeSpan / HOUR;

            case DAY:
                return timeSpan / DAY;
        }
    }

    /**
     * ��ȡ����ʱ���
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time0 �ַ���ʱ��0
     * @param time1 �ַ���ʱ��1
     * @param pattern �Զ�ʱ���ʽ
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpan(String time0, String time1, String pattern, TimeUnit unit)
    {
        return getTimeSpan(string2Millisec(time0, pattern), string2Millisec(time1, pattern), unit);
    }

    /**
     * ��ȡ����ʱ���
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time0 �ַ���ʱ��0
     * @param time1 �ַ���ʱ��1
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpan(String time0, String time1, TimeUnit unit)
    {
        return getTimeSpan(string2Millisec(time0, DEFAULT_PATTERN), string2Millisec(time1, DEFAULT_PATTERN), unit);
    }

    /**
     * ��ȡ����ʱ���
     *
     * @param date0 Date����ʱ��0
     * @param date1 Date����ʱ��1
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpan(Date date0, Date date1, TimeUnit unit)
    {
        return getTimeSpan(date0.getTime(), date1.getTime(), unit);
    }

    /**
     * ��ȡ��ǰ����ʱ���
     *
     * @return ����ʱ���
     */
    public static long getNowTimeMillis()
    {
        return System.currentTimeMillis();
    }

    /**
     * ��ȡ��ǰDateʱ��
     *
     * @return Date����ʱ��
     */
    public static Date getNowTimeDate()
    {
        return new Date();
    }

    /**
     * ��ȡ��ǰʱ���ַ���
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param pattern �Զ�ʱ���ʽ
     * @return ʱ���ַ���
     */
    public static String getNowTimeString(String pattern)
    {
        return millisec2String(getNowTimeMillis(), pattern);
    }

    /**
     * ��ȡ��ǰʱ���ַ���
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @return ʱ���ַ���
     */
    public static String getNowTimeString()
    {
        return getNowTimeString(DEFAULT_PATTERN);
    }

    /**
     * ��ȡĳ����ʱ����뵱ǰʱ��Ĳ�
     *
     * @param millisec ����ʱ���
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpanByNow(long millisec, TimeUnit unit)
    {
        return getTimeSpan(getNowTimeMillis(), millisec, unit);
    }

    /**
     * ��ȡĳʱ���ַ����뵱ǰʱ��Ĳ�
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param pattern �Զ�ʱ���ʽ
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpanByNow(String time, String pattern, TimeUnit unit)
    {
        return getTimeSpan(getNowTimeString(pattern), time, pattern, unit);
    }

    /**
     * ��ȡĳʱ���ַ����뵱ǰʱ��Ĳ�
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpanByNow(String time, TimeUnit unit)
    {
        return getTimeSpanByNow(time, DEFAULT_PATTERN, unit);
    }

    /**
     * ��ȡĳDateʱ���뵱ǰʱ��Ĳ�
     *
     * @param date Date����
     * @param unit ��λ����
     *        <ul>
     *          <li>{@link TimeUnit.MSEC}: ����</li>
     *          <li>{@link TimeUnit.SEC}: ��</li>
     *          <li>{@link TimeUnit.MIN}: ��</li>
     *          <li>{@link TimeUnit.HOUR}: ʱ</li>
     *          <li>{@link TimeUnit.DAY}: ��</li>
     *        </ul>
     * @return ʱ���
     */
    public static long getTimeSpanByNow(Date date, TimeUnit unit)
    {
        return getTimeSpanByNow(date.getTime(), unit);
    }

    /**
     * �жϺ���ʱ����Ƿ���ͬһ��
     *
     * @param millisec0 ����ʱ���0
     * @param millisec1 ����ʱ���1
     * @return
     */
    public static boolean isSameDay(long millisec0, long millisec1)
    {
        if (millisec2String(millisec0, DAY_PATTERN).equals(millisec2String(millisec1, DAY_PATTERN)))
            return true;
        return false;
    }

    /**
     * �жϺ���ʱ����Ƿ��ǽ���
     *
     * @param millisec ʱ���
     * @return
     */
    public static boolean isToday(long millisec)
    {
        return DateUtils.isToday(millisec);
    }

    /**
     * �ж�����Ƿ�������
     *
     * @param year ���
     * @return
     */
    public static boolean isLeapYear(int year)
    {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    /**
     * �ж�Date�Ƿ�������
     *
     * @param date Date����
     * @return
     */
    public static boolean isLeapYear(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return isLeapYear(calendar.get(Calendar.YEAR));
    }

    /**
     * �жϺ���ʱ����Ƿ�������
     *
     * @param millisec ����ʱ���
     * @return
     */
    public static boolean isLeapYear(long millisec)
    {
        return isLeapYear(new Date(millisec));
    }

    /**
     * �ж��ַ���ʱ���Ƿ�������
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time �ַ���ʱ��
     * @param pattern �Զ�ʱ���ʽ
     * @return
     */
    public static boolean isLeapYear(String time, String pattern)
    {
        return isLeapYear(string2Date(time, pattern));
    }

    /**
     * �ж��ַ���ʱ���Ƿ�������
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time �ַ���ʱ��
     * @return
     */
    public static boolean isLeapYear(String time)
    {
        return isLeapYear(time, DEFAULT_PATTERN);
    }

    /**
     * ����Date��ȡ����
     *
     * @param date Date����
     * @return �����ַ���
     */
    public static String getWeek(Date date)
    {
        return new SimpleDateFormat(WEEK_PATTERN, Locale.getDefault()).format(date);
    }

    /**
     * ���ݺ���ʱ�����ȡ����
     *
     * @param millisec ����ʱ���
     * @return �����ַ���
     */
    public static String getWeek(long millisec)
    {
        return getWeek(new Date(millisec));
    }

    /**
     * ����ʱ���ַ�����ȡ����
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param pattern �Զ�ʱ���ʽ
     * @return �����ַ���
     */
    public static String getWeek(String time, String pattern)
    {
        return getWeek(string2Date(time, pattern));
    }

    /**
     * ����ʱ���ַ�����ȡ����
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @return �����ַ���
     */
    public static String getWeek(String time)
    {
        return getWeek(time, DEFAULT_PATTERN);
    }

    /**
     * ����Date��ȡ�·��еڼ���
     *
     * @param date Date����
     * @return �ڼ���
     */
    public static int getWeekOfMonth(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_MONTH);
    }

    /**
     * ���ݺ���ʱ�����ȡ�·��еڼ���
     *
     * @param millisec ����ʱ���
     * @return �ڼ���
     */
    public static int getWeekOfMonth(long millisec)
    {
        return getWeekOfMonth(new Date(millisec));
    }

    /**
     * �����ַ���ʱ���ȡ�·��еĵڼ���
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param pattern �Զ�ʱ���ʽ
     * @return �ڼ���
     */
    public static int getWeekOfMonth(String time, String pattern)
    {
        return getWeekOfMonth(string2Date(time, pattern));
    }

    /**
     * �����ַ���ʱ���ȡ�·��еĵڼ���
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @return �ڼ���
     */
    public static int getWeekOfMonth(String time)
    {
        return getWeekOfMonth(time, DEFAULT_PATTERN);
    }

    /**
     * ����Date��ȡ����еĵڼ���
     *
     * @param date Date����
     * @return �ڼ���
     */
    public static int getWeekOfYear(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * ����Date��ȡ��Ф
     *
     * @param date Date����
     * @return ��Ф
     */
    public static String getChineseZodiac(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return CHINESE_ZODIAC[calendar.get(Calendar.YEAR) % CHINESE_ZODIAC.length];
    }

    /**
     * ���ݺ���ʱ�����ȡ��Ф
     *
     * @param millisec ����ʱ���
     * @return ��Ф
     */
    public static String getChineseZodiac(long millisec)
    {
        return getChineseZodiac(new Date(millisec));
    }

    /**
     * ����ʱ���ַ�����ȡ��Ф
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param pattern �Զ�ʱ���ʽ
     * @return ��Ф
     */
    public static String getChineseZodiac(String time, String pattern)
    {
        return getChineseZodiac(string2Date(time, pattern));
    }

    /**
     * ����ʱ���ַ�����ȡ��Ф
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @return ��Ф
     */
    public static String getChineseZodiac(String time)
    {
        return getChineseZodiac(time, DEFAULT_PATTERN);
    }

    /**
     * �������ջ�ȡ����
     *
     * @param month ��
     * @param day ��
     * @return ����
     */
    public static String getZodiac(int month, int day)
    {
        return ZODIAC[day < ZODIAC_FLAGS[month - 1] ? month - 1 : month];
    }

    /**
     * ����Date��ȡ����
     * ע��:0��Ӧһ�� 11��Ӧʮ���� {@link Calendar.MONTH}
     *
     * @param date Data����
     * @return ����
     */
    public static String getZodiac(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return getZodiac(month, day);
    }

    /**
     * ���ݺ���ʱ�����ȡ����
     *
     * @param millisec ����ʱ���
     * @return ����
     */
    public static String getZodiac(long millisec)
    {
        return getZodiac(new Date(millisec));
    }

    /**
     * ����ʱ���ַ�����ȡ����
     * <p>�Զ�ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @param pattern �Զ�ʱ���ʽ
     * @return ����
     */
    public static String getZodiac(String time, String pattern)
    {
        return getZodiac(string2Date(time, pattern));
    }

    /**
     * ����ʱ���ַ�����ȡ����
     * <p>Ĭ��ʱ���ʽ</p>
     *
     * @param time ʱ���ַ���
     * @return ����
     */
    public static String getZodiac(String time)
    {
        return getZodiac(time, DEFAULT_PATTERN);
    }
}
