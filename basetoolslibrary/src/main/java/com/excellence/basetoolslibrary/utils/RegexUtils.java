package com.excellence.basetoolslibrary.utils;

/**
 * Created by ZhangWei on 2017/1/23.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������ʽ���
 */
public class RegexUtils
{
    /******************** ������س��� ********************/
    /**
     * ��������
     */
    public static final String REGEX_NUM = "[^0-9]";

    /**
     * �����ֻ��ţ��򵥣�
     */
    public static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    /**
     * �����ֻ��ţ���ȷ��
     * <p>�ƶ���134(0-8)��135��136��137��138��139��147��150��151��152��157��158��159��178��182��183��184��187��188</p>
     * <p>��ͨ��130��131��132��145��155��156��175��176��185��186</p>
     * <p>���ţ�133��153��173��177��180��181��189</p>
     * <p>ȫ���ǣ�1349</p>
     * <p>������Ӫ�̣�170</p>
     */
    public static final String REGEX_MOBILE_EXACT  = "^((13[0-9])|(14[5,7])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";
    /**
     * ���򣺵绰����
     */
    public static final String REGEX_TEL           = "^0\\d{2,3}[- ]?\\d{7,8}";
    /**
     * �������֤����15λ
     */
    public static final String REGEX_ID_CARD15     = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$";
    /**
     * �������֤����18λ
     */
    public static final String REGEX_ID_CARD18     = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
    /**
     * ��������
     */
    public static final String REGEX_EMAIL         = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
    /**
     * ����URL
     */
    public static final String REGEX_URL           = "[a-zA-z]+://[^\\s]*";
    /**
     * ���򣺺���
     */
    public static final String REGEX_ZH            = "^[\\u4e00-\\u9fa5]+$";
    /**
     * �����û�����ȡֵ��ΧΪa-z,A-Z,0-9,"_",���֣�������"_"��β,�û���������6-20λ
     */
    public static final String REGEX_USERNAME      = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";
    /**
     * ����yyyy-MM-dd��ʽ������У�飬�ѿ���ƽ����
     */
    public static final String REGEX_DATE          = "^(?:(?!0000)[0-9]{4}-(?:(?:0[1-9]|1[0-2])-(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])-(?:29|30)|(?:0[13578]|1[02])-31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)-02-29)$";
    /**
     * ����IP��ַ
     */
    public static final String REGEX_IP            = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /************** ����ժ��http://tool.oschina.net/regex **************/
    /**
     * ����˫�ֽ��ַ�(������������)
     */
    public static final String REGEX_DOUBLE_BYTE_CHAR     = "[^\\x00-\\xff]";
    /**
     * ���򣺿հ���
     */
    public static final String REGEX_BLANK_LINE           = "\\n\\s*\\r";
    /**
     * ����QQ��
     */
    public static final String REGEX_TENCENT_NUM          = "[1-9][0-9]{4,}";
    /**
     * �����й���������
     */
    public static final String REGEX_ZIP_CODE             = "[1-9]\\d{5}(?!\\d)";
    /**
     * ����������
     */
    public static final String REGEX_POSITIVE_INTEGER     = "^[1-9]\\d*$";
    /**
     * ���򣺸�����
     */
    public static final String REGEX_NEGATIVE_INTEGER     = "^-[1-9]\\d*$";
    /**
     * ��������
     */
    public static final String REGEX_INTEGER              = "^-?[1-9]\\d*$";
    /**
     * ���򣺷Ǹ�����(������ + 0)
     */
    public static final String REGEX_NOT_NEGATIVE_INTEGER = "^[1-9]\\d*|0$";
    /**
     * ���򣺷��������������� + 0��
     */
    public static final String REGEX_NOT_POSITIVE_INTEGER = "^-[1-9]\\d*|0$";
    /**
     * ������������
     */
    public static final String REGEX_POSITIVE_FLOAT       = "^[1-9]\\d*\\.\\d*|0\\.\\d*[1-9]\\d*$";
    /**
     * ���򣺸�������
     */
    public static final String REGEX_NEGATIVE_FLOAT       = "^-[1-9]\\d*\\.\\d*|-0\\.\\d*[1-9]\\d*$";

    /************** If u want more please visit http://toutiao.com/i6231678548520731137/ **************/

    /**
     * ��֤�ֻ��ţ��򵥣�
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isMobileSimple(CharSequence input) {
        return isMatch(REGEX_MOBILE_SIMPLE, input);
    }

    /**
     * ��֤�ֻ��ţ���ȷ��
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isMobileExact(CharSequence input) {
        return isMatch(REGEX_MOBILE_EXACT, input);
    }

    /**
     * ��֤�绰����
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isTel(CharSequence input) {
        return isMatch(REGEX_TEL, input);
    }

    /**
     * ��֤���֤����15λ
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isIDCard15(CharSequence input) {
        return isMatch(REGEX_ID_CARD15, input);
    }

    /**
     * ��֤���֤����18λ
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isIDCard18(CharSequence input) {
        return isMatch(REGEX_ID_CARD18, input);
    }

    /**
     * ��֤����
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isEmail(CharSequence input) {
        return isMatch(REGEX_EMAIL, input);
    }

    /**
     * ��֤URL
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isURL(CharSequence input) {
        return isMatch(REGEX_URL, input);
    }

    /**
     * ��֤����
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isZh(CharSequence input) {
        return isMatch(REGEX_ZH, input);
    }

    /**
     * ��֤�û���
     * <p>ȡֵ��ΧΪa-z,A-Z,0-9,"_",���֣�������"_"��β,�û���������6-20λ</p>
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isUsername(CharSequence input) {
        return isMatch(REGEX_USERNAME, input);
    }

    /**
     * ��֤yyyy-MM-dd��ʽ������У�飬�ѿ���ƽ����
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isDate(CharSequence input) {
        return isMatch(REGEX_DATE, input);
    }

    /**
     * ��֤IP��ַ
     *
     * @param input ����֤�ı�
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isIP(CharSequence input) {
        return isMatch(REGEX_IP, input);
    }

    /**
     * �ж��Ƿ�ƥ������
     *
     * @param regex ������ʽ
     * @param input Ҫƥ����ַ���
     * @return {@code true}: ƥ��<br>{@code false}: ��ƥ��
     */
    public static boolean isMatch(String regex, CharSequence input) {
        return input != null && input.length() > 0 && Pattern.matches(regex, input);
    }

    /**
     * ��ȡ����ƥ��Ĳ���
     *
     * @param regex ������ʽ
     * @param input Ҫƥ����ַ���
     * @return ����ƥ��Ĳ���
     */
    public static List<String> getMatches(String regex, CharSequence input) {
        if (input == null) return null;
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }

    /**
     * ��ȡ����ƥ�����
     *
     * @param input Ҫ������ַ���
     * @param regex ������ʽ
     * @return ����ƥ�����
     */
    public static String[] getSplits(String input, String regex) {
        if (input == null) return null;
        return input.split(regex);
    }

    /**
     * �滻����ƥ��ĵ�һ����
     *
     * @param input       Ҫ�滻���ַ���
     * @param regex       ������ʽ
     * @param replacement ������
     * @return �滻����ƥ��ĵ�һ����
     */
    public static String getReplaceFirst(String input, String regex, String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceFirst(replacement);
    }

    /**
     * �滻��������ƥ��Ĳ���
     *
     * @param input       Ҫ�滻���ַ���
     * @param regex       ������ʽ
     * @param replacement ������
     * @return �滻��������ƥ��Ĳ���
     */
    public static String getReplaceAll(String input, String regex, String replacement) {
        if (input == null) return null;
        return Pattern.compile(regex).matcher(input).replaceAll(replacement);
    }
}
