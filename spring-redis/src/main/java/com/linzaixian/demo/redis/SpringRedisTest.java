/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.redis;
/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2018年4月13日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStringCommands.SetOption;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring.xml")
public class SpringRedisTest {
	private static final  Logger logger=LoggerFactory.getLogger(SpringRedisTest.class);
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private RedisConnectionFactory factory;
	@Test
	public void test() {
		redisTemplate.opsForValue().set("test_test", "测试", 60, TimeUnit.SECONDS);
		String result=redisTemplate.opsForValue().get("test_test");
		logger.debug("获取的缓存值是:{}",result);
		setNx("test_test2", "测试2", Expiration.from(1000, TimeUnit.MILLISECONDS));
		String result2=redisTemplate.opsForValue().get("test_test2");
		logger.debug("获取的缓存值2是:{}",result2);
	}
	private boolean setNx( String key,  String value, final Expiration expiration) {
		RedisSerializer keySerializer=redisTemplate.getKeySerializer();
		RedisSerializer valueSerializer=redisTemplate.getValueSerializer();
		logger.debug("要放到redis里的key[{}],value[{}],过期时间[单位:{},数值:{}]",key,value,expiration.getTimeUnit(),expiration.getExpirationTime());
		final byte[] keyByte = keySerializer.serialize(key);
		final byte[] valueByte = valueSerializer.serialize(value);
		return redisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				return connection.set(keyByte, valueByte, expiration, SetOption.SET_IF_ABSENT);
			}
		}, true);

	}
	
}
