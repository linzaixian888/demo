/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Joiner;

/**
 * <pre>
 * Joiner 提供了各种方法来处理字符串加入操作，对象等。
	Joiner的实例不可变的，因此是线程安全的。
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2018年1月24日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class JoinerDemo {
	public static void main(String[] args) {
		/*
		 * on:制定拼接符号，如：test1-test2-test3 中的 “-“ 符号 skipNulls()：忽略NULL,返回一个新的Joiner实例
		 * useForNull(“Hello”)：NULL的地方都用字符串”Hello”来代替
		 */
		StringBuilder sb = new StringBuilder();
		Joiner.on(",").skipNulls().appendTo(sb, "Hello", "guava");
		System.out.println(sb);
		System.out.println(Joiner.on(",").useForNull("none").join(1, null, 3));
		System.out.println(Joiner.on(",").skipNulls().join(Arrays.asList(1, 2, 3, 4, null, 6)));
		Map<String, String> map = new HashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		System.out.println(Joiner.on(",").withKeyValueSeparator("=").join(map));
	}
}
