/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.joda;

import java.util.function.LongToDoubleFunction;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

public class Main {
	private static final Logger logger=LoggerFactory.getLogger(Main.class);
	public static void main(String[] args) throws Exception {
		DateTime now=new DateTime();
		logger.debug("格式化输出:{}",now.toString("yyyy-MM-dd HH:mm:ss"));
		logger.debug("获取当前时间月的第一天 :{}",now.toLocalDate().withDayOfMonth(1));
		logger.debug("获取当前周的第一天 :{}",now.toLocalDate().dayOfWeek().withMinimumValue());
		logger.debug("获取当前周的最后一天 :{}",now.toLocalDate().dayOfWeek().withMaximumValue());
		logger.debug("今年是否闰年:{}",now.year().isLeap());
		logger.debug("当前月份:{}",now.monthOfYear().getAsText());
		logger.debug("当前星期:{}",now.getDayOfWeek());
		logger.debug("当前星期,文本显示:{}",now.dayOfWeek().getAsText());
		logger.debug("今天0点：{}",now.dayOfWeek().roundFloorCopy());
		logger.debug("今天最后时间：{}",now.dayOfWeek().roundFloorCopy().minusMillis(1));
		logger.debug("今天24点(相当于第二天的0点)：{}",now.dayOfWeek().roundCeilingCopy());
		logger.debug("当前的月份设为1月,分钟设为0分:{}",now.withMonthOfYear(1).withMinuteOfHour(0));
		logger.debug("当前加一月,减一天:{}",now.plusMonths(1).minusDays(1).plusHours(12));
	}
}
