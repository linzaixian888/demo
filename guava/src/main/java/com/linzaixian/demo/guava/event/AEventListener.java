/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.Subscribe;

/**
 * <pre>
 * A事件监听者
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

public class AEventListener {
	private static final  Logger logger=LoggerFactory.getLogger(AEventListener.class);
	//@Subscribe保证有且只有一个输入参数,如果你需要订阅某种类型的消息,只需要在指定的方法上加上@Subscribe注解即可@Subscribe
	@Subscribe
	public void listen(MyEvent event) {
		logger.debug("对象:{},方法名:{},事件:{}",this,"listen",event);
	}
}
