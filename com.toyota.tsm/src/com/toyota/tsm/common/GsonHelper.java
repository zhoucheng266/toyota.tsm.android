package com.toyota.tsm.common;

import java.text.MessageFormat;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class GsonHelper {

	private static final String TAG = "GsonHelper";

	/**
	 * 将对象转换为Json
	 * 
	 * @param srcObj
	 *            对象
	 * @return Json字符串
	 * */
	public static String objectToJson(Object srcObj) throws Exception {
		String strJson = "";
		try {
			if (srcObj == null || TextUtils.isEmpty(srcObj.toString()))
				throw new Exception("传进来的参数不能为空！");

			// "定义转换器
			GsonBuilder gsonBuilder = new GsonBuilder();
			// 不转换没有@Expose注解的字段
			gsonBuilder.excludeFieldsWithoutExposeAnnotation();
//			gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
			Gson gson = gsonBuilder.create();
			// 转换
			strJson = gson.toJson(srcObj);
		} catch (Exception ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", TAG,
					"objectToJson", ex.getMessage()));
			ex.printStackTrace();
			throw ex;
		}
		return strJson;
	}

	/**
	 * 将Json格式的字符串转换为对象
	 * 
	 * @param json
	 *            Json格式字符串
	 * @param clazz
	 *            实体对象
	 * */
	public static <T> T jsonToObject(String json, Class<T> clazz)
			throws Exception {
		try {
			if (json == null || json.equals(""))
				return null;
			// 定义转换器
			GsonBuilder gsonBuilder = new GsonBuilder();
			// 不转换没有@Expose注解的字段
			gsonBuilder.excludeFieldsWithoutExposeAnnotation();
//			gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
			Gson gson = gsonBuilder.create();
			// 转换
			T t = gson.fromJson(json, clazz);
			return t;
		} catch (Exception ex) {
			Log.e(TAG, MessageFormat.format("{0}->{1}方法发生异常：{2}", TAG,
					"jsonToObject", ex.getMessage()));
			ex.printStackTrace();
			throw ex;
		}
	}

	/**
	 * 将Json格式的字符串转换为泛型集合
	 * 
	 * @param json
	 *            Json格式字符串
	 * */
	public static <T> T jsonToObject(String json, TypeToken<T> token)
			throws Exception {
		try {
			if (json == null || json.equals(""))
				return null;
			// 定义转换器
			GsonBuilder gsonBuilder = new GsonBuilder();
			// 不转换没有@Expose注解的字段
			gsonBuilder.excludeFieldsWithoutExposeAnnotation();
//			gsonBuilder.setLongSerializationPolicy( LongSerializationPolicy.STRING );
			Gson gson = gsonBuilder.create();
			// 转换
			T t = gson.fromJson(json, token.getType());
			return t;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		}
	}

}