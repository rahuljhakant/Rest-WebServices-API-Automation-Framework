package com.qa.tests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Testbase;
import com.qa.client.RestClient;

public class getAPITest  extends Testbase{

	Testbase testbase;
	String serviceuRL;
	String apiURl;
	String url;
	RestClient restclient;
	
	@BeforeMethod
	public void Setup() throws ClientProtocolException, IOException {
		testbase =new Testbase();
		serviceuRL=prop.getProperty("URL");
		apiURl=prop.getProperty("serviceURl");
		url= serviceuRL + apiURl;
	}
	
	
	@Test
	public void getTest() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		restclient.get(url);
	}
	

}
