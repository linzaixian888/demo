/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <pre>
 * 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
 * 因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
      定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
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

public class FixedThreadPoolDemo {
	public static void main(String[] args) {
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
		for (int i = 0; i < 10; i++) {
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					try {
						System.out.println("id:"+Thread.currentThread().getId()+";index:"+index);
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
}