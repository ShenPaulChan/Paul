/**
 * @Description: 
 * @ClassName: com.paul.demo.common.utils.json.JsonUtils
 * @author: Paul Chen
 * @date: 2016年6月14日 下午4:18:24 
 */
package com.paul.demo.common.utils.json;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * @Description:
 * @ClassName: com.paul.demo.common.utils.json.JsonUtils
 * @author: Paul Chen
 * @date: 2016年6月14日 下午4:18:24
 *
 */
public class JsonUtils {

	private static ObjectMapper mapper = new ObjectMapper();

	static {
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
	}

	private JsonUtils() {
	}

	/**
	 * 将对象转换为JSON字符串
	 * 
	 * @param value
	 *            对象
	 * @return JSOn字符串
	 */
	public static String toJson(Object value) {
		try {
			return mapper.writeValueAsString(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param valueType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, Class<T> valueType) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			return mapper.readValue(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象 可配置时间格式
	 * 
	 * @Title: toPbject
	 * @param json
	 * @param valueType
	 * @param dateFomat
	 * @return
	 * @author: Paul(Paul Chen)
	 * @date: 2016年5月27日 上午11:59:27
	 */
	public static <T> T toObject(String json, Class<T> valueType, String dateFomat) {
		Assert.hasText(json);
		Assert.notNull(valueType);
		try {
			mapper.setDateFormat(new SimpleDateFormat(dateFomat));
			return toObject(json, valueType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param typeReference
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, TypeReference<?> typeReference) {
		Assert.hasText(json);
		Assert.notNull(typeReference);
		try {
			return mapper.readValue(json, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将JSON字符串转换为对象
	 * 
	 * @param json
	 *            JSON字符串
	 * @param javaType
	 *            对象类型
	 * @return 对象
	 */
	public static <T> T toObject(String json, JavaType javaType) {
		Assert.hasText(json);
		Assert.notNull(javaType);
		try {
			return mapper.readValue(json, javaType);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将对象转换为JSON流
	 * 
	 * @param writer
	 *            writer
	 * @param value
	 *            对象
	 */
	public static void writeValue(Writer writer, Object value) {
		try {
			mapper.writeValue(writer, value);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @Title: convertList2Json
	 * @Description: 将List对象转换为Json字符串(支持泛型)
	 * @param objects
	 * @param clazz
	 * @throws IOException
	 * @return String
	 */
	@SuppressWarnings("rawtypes")
	public static <T> String convertList2Json(List<T> objects, Class clazz) throws IOException {
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper.writeValueAsString(objects);
	}

	/**
	 * 字符串转 对象集合
	 * 
	 * @param <T>
	 * @Title: toObjectList
	 * @param storeGroupIds
	 * @param class1
	 * @return
	 * 
	 */
	public static <T> List<T> toObjectList(String json, Class<T> clazz) {
		List<T> lists = new ArrayList<T>();
		if (StringUtils.isBlank(json)) {
			return lists;
		}
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> tempList = toObject(json, new ArrayList<Map<String, Object>>().getClass());
		for (Map<String, Object> tempMap : tempList) {
			try {
				lists.add(mapper.readValue(toJson(tempMap), clazz));
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return lists;
	}

}
