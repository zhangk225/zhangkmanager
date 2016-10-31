package com.zhangkssh.baseframe.common.util.gson;

import java.lang.reflect.Type;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {

	public static <T> T fromJson(String json, Class<T> clas) {
		Gson gson = ApiResponseGsonHelper.getBuilder().create();
		return gson.fromJson(json, clas);
	}

	public static String toJson(Object object) {
		Gson gson = ApiResponseGsonHelper.getBuilder().create();
		return gson.toJson(object);
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getStringMap(String json) {
		Gson gson = ApiResponseGsonHelper.getBuilder().create();
		Type type = new TypeToken<Map<String, String>>() {
		}.getType();
		return (Map<String, String>) gson.fromJson(json, type);
	}
}
