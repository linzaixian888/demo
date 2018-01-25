/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava;

import com.google.common.base.Splitter;

/**
 * <pre>
 * Splitter 能够将一个字符串按照指定的分隔符拆分成可迭代遍历的字符串集合，Iterable
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

public class SplitterDemo {
	public static void main(String[] args) {
	       /*
	         on():指定分隔符来分割字符串
	         limit():当分割的子字符串达到了limit个时则停止分割
	         fixedLength():根据长度来拆分字符串
	         trimResults():去掉子串中的空格
	         omitEmptyStrings():去掉空的子串
	         withKeyValueSeparator():要分割的字符串中key和value间的分隔符,分割后的子串中key和value间的分隔符默认是=
	        */
	       System.out.println(Splitter.on(",").limit(3).trimResults().split(" a,  b,  c,  d"));//[ a, b, c,d]
	       System.out.println(Splitter.fixedLength(3).split("1 2 3"));//[1 2,  3]
	       System.out.println(Splitter.on(" ").omitEmptyStrings().splitToList("1  2 3"));
	       System.out.println(Splitter.on(",").omitEmptyStrings().split("1,,,,2,,,3"));//[1, 2, 3]
	       System.out.println(Splitter.on(" ").trimResults().split("1 2 3")); //[1, 2, 3],默认的连接符是,
	       System.out.println(Splitter.on(";").withKeyValueSeparator(":").split("a:1;b:2;c:3"));//{a=1, b=2, c=3}
	   }
}
