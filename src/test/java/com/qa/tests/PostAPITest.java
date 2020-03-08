package com.qa.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.Testbase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostAPITest extends Testbase {
	
	 Testbase testbase;
	    String serviceuRL;
	    String apiURl;
	    String url;
	    RestClient restclient;
	    CloseableHttpResponse closeableHttpResponse;
	    
	    
	@BeforeMethod(description = "TEST")
    public void Setup() throws ClientProtocolException, IOException {
        testbase = new Testbase();
        serviceuRL = prop.getProperty("URL");
        apiURl = prop.getProperty("serviceURl");
        url = serviceuRL + apiURl;
    }
	
	
	@Test(priority = 1)
	public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException{
		restclient = new RestClient();
		
		
		// Set Header
		HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");
        
        
        // Jackson Java API to do marshelling to convert POJO to Json object
        ObjectMapper mapper = new ObjectMapper();
        
//      mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        
        Users users = new Users("morpheus","leader");   // Expected user object
        
        
        // Convert 	Java object to Json Object
        String path = System.getProperty("user.dir");
        
        mapper.writeValue(new File(path+"/src/main/java/com/qa/data/user.json"), users);
        
        
        // Object to JSON String
        String usersJsonString=  mapper.writeValueAsString(users);
        System.out.println(usersJsonString);
      
        closeableHttpResponse=restclient.post(url, usersJsonString, headerMap);
        
        
        // Check status code
        int status=  closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code of POST Request"+ status);
        
        
        
        assertEquals(status, RESPONSE_STATUS_CODE_201,"STATUS CODE IS NOT 201 OK");
        
        
        // JSON String
        String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
        
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println(responseJson);
        
        
        // Json to Java Object
        Users objUsers=  mapper.readValue(responseString, Users.class);  // Actual user Object
        System.out.println(objUsers);
        
        
        // Compare Expected and Actual user object
        
        Assert.assertTrue(users.getName().equals(objUsers.getName()));
        Assert.assertTrue(users.getJob().equals(objUsers.getJob())); 
        
        System.out.println(objUsers.getCreatedAt());
        System.out.println(objUsers.getId());
        
	}
	
	
	
	

}
