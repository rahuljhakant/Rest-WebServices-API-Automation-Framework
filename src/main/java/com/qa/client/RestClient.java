package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method
	
	public void get(String url) throws ClientProtocolException, IOException {
		
	CloseableHttpClient	httpClient=	HttpClients.createDefault();
	HttpGet httpGet = new HttpGet(url);  // HTTP Get request
	
	CloseableHttpResponse closeableHttpResponse=	httpClient.execute(httpGet);   // hit the GET URl
	
	// getting status code
	int status_code=closeableHttpResponse.getStatusLine().getStatusCode();
	
	System.out.println("Status code : "+status_code);
	
	
	// getting JSON String
	String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
	
	JSONObject response_Json = new JSONObject(responseString);
	
	System.out.println("Response JSON from API------->"+ response_Json);
	
	// All headers
	Header[] headers = closeableHttpResponse.getAllHeaders();
	
	
	HashMap<String, String> allHeadersHashMap = new HashMap<String, String>();
	
	
	for(Header header: headers) {
	
		allHeadersHashMap.put(header.getName(), header.getValue());
	}
	
	System.out.println("All headers info"+allHeadersHashMap);
		
	}
	
}
