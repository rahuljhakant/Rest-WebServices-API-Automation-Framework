package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class RestClient {

    // 1. GET Method without headers

    public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);  // HTTP Get request

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);   // hit the GET URl

        return closeableHttpResponse;
    }
    
    
    // 2. GET Method with Headers
    public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws ClientProtocolException, IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);  // HTTP Get request

        
        for(Map.Entry<String, String> entry: headerMap.entrySet()) {
        	httpGet.addHeader(entry.getKey(), entry.getValue());
        }
        
        
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet);   // hit the GET URl

        return closeableHttpResponse;
    }

}
