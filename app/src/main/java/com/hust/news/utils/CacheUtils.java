package com.hust.news.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.AvoidXfermode.Mode;

/*
 * 缓存类 
 * 所有的数据都是采用SharedPreference方式存储或获取
 * 
 * */

public class CacheUtils {

	private static final String CACHE_FILE_NAME_STRING = "flag";
	private static SharedPreferences mSharedPreferences;

	/**
	 * 获取一个Boolean类型的数据
	 * 
	 * @param context
	 * @param key 要取的数据的键
	 * @param defValue 缺省值
	 * @return
	 */
	public static boolean getBoolean(Context context, String key,
			boolean defValue) {
		if (mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(
					CACHE_FILE_NAME_STRING, Context.MODE_PRIVATE);
		}

		return mSharedPreferences.getBoolean(key, defValue);

	}

	/**
	 * 存储一个Boolean类型的数据
	 * 
	 * @param context
	 * @param key 要取的数据的键
	 * @param value
	 */
	public static void putBoolean(Context context, String key, Boolean value) {
		if (mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(
					CACHE_FILE_NAME_STRING, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putBoolean(key, value).commit();

	}


	/**
	 * 存储一个String类型的数据
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void putString(Context context, String key, String value) {
		if(mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME_STRING, Context.MODE_PRIVATE);
		}
		mSharedPreferences.edit().putString(key, value).commit();
	}

	/**
	 * 根据key取出一个String类型的值
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static String getString(Context context, String key, String defValue) {
		if(mSharedPreferences == null) {
			mSharedPreferences = context.getSharedPreferences(CACHE_FILE_NAME_STRING, Context.MODE_PRIVATE);
		}
		return mSharedPreferences.getString(key, defValue);
	}
}


