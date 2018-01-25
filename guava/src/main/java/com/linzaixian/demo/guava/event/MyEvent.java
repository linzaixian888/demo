/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava.event;
/**
 * <pre>
 * 事件
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

public class MyEvent {
	private String message;
	
	public MyEvent(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MyEvent [message=" + message + "]";
	}
	
}
