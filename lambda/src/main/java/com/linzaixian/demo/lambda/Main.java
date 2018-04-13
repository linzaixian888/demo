/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.lambda;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2018年2月9日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class Main {
	public static void main(String[] args) {
		
		new Thread(() -> System.out.println("lambda表达式")).start();
		Arrays.asList("1", "2").forEach((item) -> System.out.println(item));
		Arrays.asList(1, 2, 3, 4, 5).stream().map(item -> item * 2).collect(Collectors.toList())
				.forEach(item -> System.out.println(item));
		Arrays.asList("a c b", "a b").stream().map(item -> item.split(" ")).flatMap(item -> Arrays.stream(item))
				.forEach(item -> System.out.println(item));
		Optional<Integer> result = Arrays.asList("0 11 1", "3 4").stream().map(item -> {
			String[] array = item.split(" ");
			return Arrays.stream(array).map(childItem -> Integer.parseInt(childItem)).collect(Collectors.toList());
		}).flatMap(item -> item.stream()).reduce((acc, item) -> {
			System.out.println("acc : " + acc + ";item : " + item);
			return acc + item;
		});
		System.out.println(result.get());

	}
}
