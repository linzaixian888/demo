/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
 * 表示延迟1秒后每3秒执行一次。
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2017年12月20日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class ScheduledThreadPool {
	public static void main(String[] args) {
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
		}, 1, 3, TimeUnit.SECONDS);
	}
}
