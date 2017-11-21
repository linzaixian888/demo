
package com.linzaixian.demo.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.search.TermQuery;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public class Main {

    public static void main(String[] args) throws Exception {

//
//        TransportClient client = getTransportClient();
//        XContentBuilder builder = XContentFactory.jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date())
//                .field("message", "trying out Elasticsearch").endObject();
//        IndexResponse response = client.prepareIndex("index", "type", "2").setSource(builder).get();
//        System.out.println(response.status());
    	Map map=new HashMap<>();
    	map.put("name", "abcab c中");
    	query("index","type",map);

    }
    private static void query() throws UnknownHostException {
    	
    	QueryBuilder qb=QueryBuilders.termsQuery("name.keyword", "abcab c中");
    	QueryBuilder qb2=QueryBuilders.boolQuery().must(qb);
    	TransportClient client=getTransportClient();;
    	SearchRequestBuilder builder=client.prepareSearch("index").setTypes("type");
    	SearchResponse response=builder.setQuery(qb2).execute().actionGet();
    	System.out.println(builder.toString());
    	System.out.println(response);
    	for(SearchHit hit:response.getHits()) {
    		System.out.println(hit.getSourceAsString());
    	}
    }
    
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
    private static void batchInsert() throws UnknownHostException {
    	Long count = 100000L;
    	String index = "bigdata";
    	String type = "student1";
    	TransportClient client=getTransportClient();
    	BulkRequestBuilder bulkRequest = client.prepareBulk();
    	for (int i = 0; i < count; i++) {
    	    Map<String, Object> ret = new HashMap<String, Object>();
    	    ret.put("recordtime", 11);
    	    ret.put("area", 22);
    	    ret.put("usertype", 33);
    	    ret.put("count", 44);
    	    bulkRequest.add(client.prepareIndex(index, type).setSource(ret));
    	    // 每10000条提交一次
    	    if (i % 10000 == 0) {
    	    	System.out.println("开始插入");
    	        bulkRequest.execute().actionGet();
    	    }
    	}
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
