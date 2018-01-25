/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava;

import com.google.common.base.Preconditions;

/**
 * <pre>
 * 前置条件Preconditions提供静态方法来检查方法或构造函数，被调用是否给定适当的参数。它检查的先决条件。其方法失败抛出IllegalArgumentException。
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

public class PreconditionsDemo {
	   public static void main(String[] args) {
	       try {
	           getValue(5);
	       } catch (IndexOutOfBoundsException e){
	           System.out.println(e.getMessage());
	       }

	       try {
	           sum(4,null);
	       } catch (NullPointerException e){
	           System.out.println(e.getMessage());
	       }

	       try {
	           sqrt(-1);
	       } catch (IllegalArgumentException e){
	           System.out.println(e.getMessage());
	       }

	   }

	   private static double sqrt(double input){
	       Preconditions.checkArgument(input>0.0,
	               "Illegal Argument passed: Negative value %s.",input);
	       return Math.sqrt(input);
	   }

	   private static int sum(Integer a,Integer b){
	       a=Preconditions.checkNotNull(a,
	               "Illegal Argument passed: First parameter is Null.");
	       b=Preconditions.checkNotNull(b,
	               "Illegal Argument passed: First parameter is Null.");
	       return a+b;
	   }

	   private static int getValue(int input){
	       int[] data={1,2,3,4,5};
	       int index=Preconditions.checkElementIndex(input,data.length,
	               "Illegal Argument passed: Invalid index.");
	       return data[index];
	   }
	}
