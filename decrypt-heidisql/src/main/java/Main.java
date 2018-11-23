import java.util.Scanner;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2018年11月23日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class Main {
	public static void main(String[] args) {
		System.out.println("请输入[HeidiSQL]加密密文:");
		Scanner scanner=new Scanner(System.in);
		String str=scanner.nextLine();
		StringBuilder sb=new StringBuilder();
		int endIndex=2;
    	int length=str.length();
    	int finalValue=Integer.parseInt(str.substring(length-1));
    	while(endIndex<length) {
    		String tmp=str.substring(endIndex-2, endIndex);
    		endIndex+=2;
    		Integer itemValue=Integer.parseInt(tmp, 16);
    		char charValue=(char) (itemValue-finalValue);
    		sb.append(charValue);
    	}
    	System.out.println("解密后的明文是:"+sb.toString());
	}
}
