/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.guava;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.management.NotificationEmitter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * <pre>
 * 缓存相关
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

public class CacheDemo {
	private static final Logger logger=LoggerFactory.getLogger(CacheDemo.class);
	private static final String KEY="key";
	public static void main(String[] args) throws Exception {
		ListeningExecutorService backgroundRefreshPools = 
                MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		
		RemovalListener<String, Object> removalListener =new RemovalListener<String, Object>() {
			
			@Override
			public void onRemoval(RemovalNotification<String, Object> notification) {
				logger.debug("回收的key:{},value:{}",notification.getKey(),notification.getValue());
				
			}
		};
		LoadingCache<String, Object> caches = CacheBuilder.newBuilder()
				//缓存大小，接近上限时会回收
				.maximumSize(100)
				//设置固定时期后刷新,会触发reload
				.refreshAfterWrite(1, TimeUnit.SECONDS)
				//设置过期时间,会触发load
				.expireAfterWrite(2, TimeUnit.SECONDS)
				.removalListener(removalListener)
				.build(new CacheLoader<String, Object>() {
					private int  index=0;
					
					public synchronized   String getIndex() throws Exception {
						index++;
						String str="值"+index;
						logger.debug("设置当前值：{}",str);
						return str;
					}
					//同步刷新，
					@Override
					public Object load(String key) throws Exception {
						logger.debug("触发load");
						return getIndex();
					}
					//异步刷新 refresh时会调用此方法，但会返回旧值
					@Override
					public ListenableFuture<Object> reload(String key, Object oldValue) throws Exception {
						logger.debug("触发reload");
						return backgroundRefreshPools.submit(new Callable<Object>() {
							@Override
							public Object call() throws Exception {
								return getIndex();
							}
						});
					}
				});
		caches.put(KEY, "旧值");
		logger.debug("设值");
		for(int i=0;i<10;i++) {
			Thread.sleep(1000);
			logger.debug("取值：{}",caches.get(KEY));
		}
		backgroundRefreshPools.shutdown();
		
	}
}
