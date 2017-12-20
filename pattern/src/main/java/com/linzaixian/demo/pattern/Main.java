package com.linzaixian.demo.pattern;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 
 * <pre>
 * TODO。
 * </pre>
 *
 * @author foresee@foresee.com.cn
 * @date 2017年12月20日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */
public class Main {
	private static String  regex="\\$\\{[A-Za-z]+\\}";
	public static void main(String[] args) {
		String str = "${a}{b}";
		System.out.println(getParamName(str));
	}
	
	public static Set<String> getParamName(String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		Set<String> set=new HashSet<String>();
		 while(matcher.find()) {
			 String findOne=matcher.group();
			  set.add(findOne.substring(2, findOne.length()-1));
		   }
		return set;
		
	}
	
}
