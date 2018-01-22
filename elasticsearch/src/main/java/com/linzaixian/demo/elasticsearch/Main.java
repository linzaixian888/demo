/**
 * Copyright(c) Foresee Science & Technology Ltd. 
 */
package com.linzaixian.demo.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.elasticsearch.action.admin.indices.refresh.RefreshRequest;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.DeleteByQueryAction;
import org.elasticsearch.index.reindex.DeleteByQueryRequestBuilder;
import org.elasticsearch.index.reindex.UpdateByQueryAction;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * <pre>
 * TODO。
 * </pre>
 *
 * @author linzaixian@foresee.com.cn
 * @date 2018年1月15日
 * @version 1.00.00
 * 
 *          <pre>
 * 修改记录 
 *    修改后版本:     修改人：  修改日期:     修改内容:
 *          </pre>
 */

public class Main {
	private static final String INDEX = "notice";
	private static final String TYPE = "detail";
	private static final String HOST = "10.10.0.204";
	private static final int PORT = 9300;
	private  static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {
		insert();
		insertMap();
		insertBean();
		insertBean2();
		insertBatch();
		deleteByCondition();
		queryById();
		queryQuery();
		
	}
	
	/**
	 * 刷新，使插入的文档可快速被检索到
	 * @throws  Exception 
	 */
	public void refresh() throws  Exception {
		TransportClient client=getTransportClient();
		client.admin().indices().refresh(new RefreshRequest(INDEX)).actionGet();
	}

	/**
	 * 原本es的操入
	 * @throws Exception void
	 */
	private static void insert() throws Exception {
		TransportClient client=getTransportClient();
    	XContentBuilder builder=XContentFactory.jsonBuilder().startObject()  
        .field("name","名字1")
        .endObject();
    	IndexResponse  response=client.prepareIndex(INDEX, TYPE,"1")
								      .setSource( builder)  
								      .execute()  
								      .actionGet();  
    	System.out.println(response);
	}
	private static void insertMap() throws Exception {
		Map map=new HashMap<>();
		map.put("name", "名字2");
		TransportClient client=getTransportClient();
    	IndexResponse  response=client.prepareIndex(INDEX, TYPE,"2")
								      .setSource( map)  
								      .execute()  
								      .actionGet();  
    	System.out.println(response);
	}
	private static void insertBean()throws Exception{
		EsDto dto=new EsDto();
		dto.setName("名字3");
		Map map = PropertyUtils.describe(dto);
		map.remove("class");
		TransportClient client=getTransportClient();
    	IndexResponse  response=client.prepareIndex(INDEX, TYPE,"3")
								      .setSource( map)  
								      .execute()  
								      .actionGet();  
    	System.out.println(response);
	}
	
	private static void insertBean2()throws Exception{
		EsDto dto=new EsDto();
		dto.setName("名字4");
		ObjectMapper objectMapper=new ObjectMapper();
		Map map=objectMapper.convertValue(dto, Map.class);
		TransportClient client=getTransportClient();
    	IndexResponse  response=client.prepareIndex(INDEX, TYPE,"4")
								      .setSource( map)  
								      .execute()  
								      .actionGet();  
    	System.out.println(response);
		
	}
	private static void insertBatch()throws Exception{
		TransportClient client=getTransportClient();
		BulkRequestBuilder bulkRequest = client.prepareBulk();
		for(int i=5;i<=6;i++) {
			Map map=new HashMap<>();
			map.put("name", "名字"+i);
			bulkRequest.add(client.prepareIndex(INDEX, TYPE,i+"").setSource(map));
		}
		BulkResponse bulkItemResponses = bulkRequest.execute().actionGet();
		if (bulkItemResponses.hasFailures()) {
			throw new RuntimeException("" + bulkItemResponses.buildFailureMessage());
		}
	}
	public static void deleteByCondition()throws Exception{
		TransportClient client=getTransportClient();
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		queryBuilder.must(QueryBuilders.typeQuery(TYPE));
		queryBuilder.must(QueryBuilders.termQuery("id", "1"));
		DeleteByQueryAction.INSTANCE.newRequestBuilder(client).source(INDEX).filter(queryBuilder).execute().actionGet();
	}
	
	
	
	public static void queryById()throws Exception{
		TransportClient client=getTransportClient();
		GetResponse response=client.prepareGet(INDEX, TYPE, "1").execute().actionGet();
		System.out.println(response.getSource());
	}
	public static void queryQuery()throws Exception{
		TransportClient client=getTransportClient();
		//多条件
		BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
		//与
		queryBuilder.must(QueryBuilders.wildcardQuery("name", "名字*"));//模糊查询
		queryBuilder.must(QueryBuilders.termQuery("id", 3));//精确查询
		Date begin=sdf.parse("2017-11-21 11:00:00");
    	Date end=sdf.parse("2017-11-21 13:00:00");
    	//时间范围查询
    	queryBuilder.must(QueryBuilders.rangeQuery("time").gt(begin.getTime()).lt(end.getTime()));
		//--------------------------------------------------
		//或
		queryBuilder.must(QueryBuilders.wildcardQuery("name", "名字*"));
		queryBuilder.should(QueryBuilders.termQuery("id", 3));
		queryBuilder.minimumShouldMatch(1);//should的条件至少有一个满足;
		handleQueryResult(client, queryBuilder);
	}
	
	private static void handleQueryResult(TransportClient client,QueryBuilder queryBuilder) {
		SearchRequestBuilder builder = client.prepareSearch(INDEX).setTypes(TYPE).setQuery(queryBuilder);
		System.out.println(builder.toString());
		handleQueryResult(builder);
	}
	private static void handleQueryResult(SearchRequestBuilder searchRequestBuilder) {
		SearchResponse response = searchRequestBuilder.execute().actionGet();
		System.out.println(response.toString());
	}

	private static TransportClient getTransportClient() throws UnknownHostException {
		Settings setting = Settings.builder().put("cluster.name", "elk")// 指定集群名称
				.put("client.transport.sniff", true)// 启动嗅探功能
				.build();
		TransportClient client = new PreBuiltTransportClient(setting);// 创建client
		client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(HOST), PORT));// 增加地址和端口
		return client;
	}
}
