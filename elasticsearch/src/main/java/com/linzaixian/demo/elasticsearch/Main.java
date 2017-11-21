
package com.linzaixian.demo.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class Main {
	private  static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws Exception {
    	insert();
    	insert2();
    	queryEqual();
    	queryLike();
    	queryDate();

    }
    
    
    private static void queryEqual() throws UnknownHostException{
    	QueryBuilder qb=QueryBuilders.termQuery("name.keyword", "吕慧城");
    	TransportClient client=getTransportClient();
    	SearchRequestBuilder builder=client.prepareSearch("index").setTypes("type");
    	SearchResponse response=builder.setQuery(qb).execute().actionGet();
    	System.out.println(response);
    	for(SearchHit hit:response.getHits()) {
    		System.out.println(hit.getSourceAsString());
    	}
    }
    private static void queryLike()  throws UnknownHostException{
    	QueryBuilder qb=QueryBuilders.wildcardQuery("name", "*林*");
    	TransportClient client=getTransportClient();
    	SearchRequestBuilder builder=client.prepareSearch("index").setTypes("type");
    	SearchResponse response=builder.setQuery(qb).execute().actionGet();
    	System.out.println(response);
    	for(SearchHit hit:response.getHits()) {
    		System.out.println(hit.getSourceAsString());
    	}
    }
    private static void queryDate() throws UnknownHostException, ParseException{
    	Date begin=sdf.parse("2017-11-21 11:00:00");
    	Date end=sdf.parse("2017-11-21 13:00:00");
    	QueryBuilder qb=QueryBuilders.rangeQuery("time").gt(begin.getTime()).lt(end.getTime());
    	System.out.println();
    	TransportClient client=getTransportClient();
    	SearchRequestBuilder builder=client.prepareSearch("index").setTypes("type");
    	SearchResponse response=builder.setQuery(qb).execute().actionGet();
    	System.out.println(response);
    	for(SearchHit hit:response.getHits()) {
    		System.out.println(hit.getSourceAsString());
    	}
    }
    private static void insert () throws Exception {
    	TransportClient client=getTransportClient();
    	XContentBuilder builder=XContentFactory.jsonBuilder().startObject()  
        .field("name" , "有时间的").field("time", sdf.parse("2017-11-21 12:00:00"))
        .endObject();
    	
    	
    	IndexResponse  response=client.prepareIndex("index", "type","5")
								      .setSource( builder)  
								      .execute()  
								      .actionGet();  
    	System.out.println(response);
    }
    
    
    private static void insert2 () throws Exception {
    	TransportClient client=getTransportClient();
    	XContentBuilder builder=XContentFactory.jsonBuilder().startObject()  
        .field("name" , "有时间的").field("time", sdf.parse("2017-11-21 14:00:00"))
        .endObject();
    	
    	
    	IndexResponse  response=client.prepareIndex("index", "type","6")
								      .setSource( builder)  
								      .execute()  
								      .actionGet();  
    	System.out.println(response);
    }
    /**
     * 多条件精确查询
     * @param index
     * @param type
     * @param param
     * @throws UnknownHostException
     */
    private static void query(String index,String type,Map<String,Object> param) throws UnknownHostException {
    	BoolQueryBuilder queryBuilder=QueryBuilders.boolQuery();
		if(param!=null) {
			Iterator<Entry<String, Object>> it=param.entrySet().iterator();
			while(it.hasNext()) {
				Entry<String, Object> entry=it.next();
				queryBuilder.must(QueryBuilders.termQuery(entry.getKey()+".keyword", entry.getValue()));
			}
		}
    	TransportClient client=getTransportClient();
		SearchRequestBuilder searchRequestBuilder=client.prepareSearch(index).setTypes(type);
		System.out.println(queryBuilder);
		SearchResponse response=searchRequestBuilder.setQuery(queryBuilder).execute().actionGet();
		System.out.println(searchRequestBuilder);
		System.out.println(response);
    }
    private static TransportClient getTransportClient() throws UnknownHostException {
    	 Settings setting = Settings.builder()
                 .put("cluster.name", "elasticsearch")//指定集群名称  
				 .put("client.transport.sniff", true)//启动嗅探功能  
				 .build();
    	 TransportClient client = new PreBuiltTransportClient(setting);// 创建client
         client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));// 增加地址和端口
         return client;
    }
}
