package com.excellence.basetoolslibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by ZhangWei on 2017/1/23.
 */

/**
 * �洢���
 */
public class DBUtils
{
	/**
	 * �洢�ļ���
	 */
	private static String SHAREPREFERENCES_FILE_NAME = DBUtils.class.getSimpleName();

	/**
	 * �洢String
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setStringSharedPreferences(Context context, String key, String value)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString(key, value);
		editor.apply();
	}

	/**
	 * ��ȡString
	 *
	 * @param context
	 * @param key
	 * @return
	 */
	public static String getStringSharedPreferences(Context context, String key)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getString(key, null);
	}

	/**
	 * �洢Boolean
	 *
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void setBooleanSharedPreferences(Context context, String key, boolean value)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putBoolean(key, value);
		editor.apply();
	}

	/**
	 * ��ȡBoolean
	 *
	 * @param context
	 * @param key
	 * @return
	 */
	public static boolean getBooleanSharedPreferences(Context context, String key)
	{
		SharedPreferences sharedPreferences = context.getSharedPreferences(SHAREPREFERENCES_FILE_NAME, Context.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, false);
	}
}
