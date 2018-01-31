/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.RateLimiter;

/**
 * <pre>
 * 令牌桶限流
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2018年1月31日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class RateLimiterDemo {
	private static final Logger logger=LoggerFactory.getLogger(RateLimiterDemo.class);
	public static void main(String[] args) {
		//设置每秒最大的许可数，会以1/size秒时间速度存入一个令牌
		RateLimiter rateLimiter=RateLimiter.create(3);
		//取走一个令牌，没有的话则堵塞
		rateLimiter.acquire();
		//取走一个令牌，不堵塞，取的到的话返回true,没取到的话返回false;
		rateLimiter.tryAcquire();
	}
}
