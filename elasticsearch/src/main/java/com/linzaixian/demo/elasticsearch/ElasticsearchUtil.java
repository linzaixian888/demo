package com.linzaixian.demo.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class ElasticsearchUtil {
	private static  TransportClient client = null;
	private static int INSERT_BATCH_SIZE=10000;
	static {
		 try {
			Settings setting = Settings.builder()
			         .put("cluster.name", "elasticsearch")//指定集群名称  
					 .put("client.transport.sniff", true)//启动嗅探功能  
					 .build();
			 TransportClient client = new PreBuiltTransportClient(setting);// 创建client
			 client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));// 增加地址和端口
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public static IndexResponse insert(String index,String type,Map map) {
		IndexResponse response =client.prepareIndex(index, type).setSource(map).get();
		return response;
	}
	
	public static void insertBatch(String index,String type,List<Map> list) {
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		Iterator<Map> it=list.iterator();
		int position=0;
		while(it.hasNext()) {
			position++;
			bulkRequest.add(client.prepareIndex(index, type).setSource(it.next()));
			if(position>=INSERT_BATCH_SIZE) {
				 bulkRequest.execute().actionGet();
			}
		}
    	if(position>0) {
    		 bulkRequest.execute().actionGet();
    	}
	}
	
}
