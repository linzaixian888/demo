/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava;

import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.linzaixian.demo.guava.event.AEventListener;
import com.linzaixian.demo.guava.event.BEventListener;
import com.linzaixian.demo.guava.event.DeadEventListener;
import com.linzaixian.demo.guava.event.MyEvent;

/**
 * <pre>
 * 事件总线
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

public class EventBusDemo {
	public static void main(String[] args) {
		MyEvent event=new MyEvent("自定义消息");
		EventBus eventBus=new EventBus();
//		EventBus eventBus=new AsyncEventBus(Executors.newFixedThreadPool(3));
		eventBus.register(new BEventListener());
		eventBus.register(new AEventListener());
		eventBus.register(new DeadEventListener());
		
		eventBus.post(event);
		eventBus.post("这是一条没人消费的DeadEvent消息");
	}
}
