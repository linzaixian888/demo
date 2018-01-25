/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava;

import com.google.common.base.Optional;

/**
 * <pre>
 * guava的Optional类似于Java 8新增的Optional类，都是用来处理null的，不过guava的是抽象类，其实现类为Absent和Present，而java.util的是final类。其中一部分方法名是相同的。
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

public class OptionalDemo {
	public static void main(String[] args) {
	       Integer value1=null;
	       Integer value2=10;
	       /*创建指定引用的Optional实例，若引用为null则快速失败返回absent()
	         absent()创建引用缺失的Optional实例
	        */
	       Optional<Integer> a=Optional.fromNullable(value1);
	       Optional<Integer> b=Optional.of(value2); //返回包含给定的非空引用Optional实例
	       System.out.println(sum(a,b));
	   }

	   private static Integer sum(Optional<Integer> a,Optional<Integer> b){
	       //isPresent():如果Optional包含非null的引用（引用存在），返回true
	       System.out.println("First param is present: "+a.isPresent());
	       System.out.println("Second param is present: "+b.isPresent());
	       Integer value1=a.or(0);  //返回Optional所包含的引用,若引用缺失,返回指定的值
	       Integer value2=b.get(); 
	       return value1+value2;
	   }
}
