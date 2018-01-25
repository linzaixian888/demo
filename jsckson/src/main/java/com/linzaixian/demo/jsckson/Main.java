/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.jsckson;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2018年1月15日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class Main {
	/**
	 * 线程安全，可在多线程环境中使用
	 */
	private static final ObjectMapper objectMapper=new ObjectMapper();
	private static final Date now=new Date();
	public static void main(String[] args) throws Exception {
		mapToJson();
		beanToJson();
		beanToMap();
		mapToBean();
	}
	private static void setSetting() {
		//null属性不进行序列化
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//取消对时间timestamps形式
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		//忽略类中不存在的字段
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	private static void mapToJson()throws Exception {
		Map map=new HashMap<>();
		map.put("name", "名字");
		map.put("createTime", now);
		map.put("updateTime", now);
		String json=objectMapper.writeValueAsString(map);
		System.out.println(json);
	}
	private static void beanToJson() throws Exception {
		Bean bean=  new Bean();
		bean.setCreateTime(now);
		bean.setUpdateTime(now);
		String json=objectMapper.writeValueAsString(bean);
		System.out.println(json);
	}
	private static void beanToMap()throws Exception {
		Bean bean=  new Bean();
		bean.setCreateTime(now);
		bean.setUpdateTime(now);
		Map map=objectMapper.convertValue(bean, Map.class);
		System.out.println(map.get("createTime").getClass());
		System.out.println(map.get("updateTime").getClass());
		System.out.println(map);
	}
	private static void mapToBean() throws Exception {
		Map map=new HashMap<>();
		map.put("name", "名字");
		map.put("createTime", now);
		map.put("updateTime", now);
		Bean bean=objectMapper.convertValue(map, Bean.class);
		System.out.println(bean);
	}
}
