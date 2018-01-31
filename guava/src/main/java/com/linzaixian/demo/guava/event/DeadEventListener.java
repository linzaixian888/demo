/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;

/**
 * <pre>
 * TODO。
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

public class DeadEventListener {
	private static final  Logger logger=LoggerFactory.getLogger(DeadEventListener.class);
	@Subscribe
	public void listen(DeadEvent event) {
		logger.warn("{}",event);
	}

}
