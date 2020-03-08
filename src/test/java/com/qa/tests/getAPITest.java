package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;

import com.qa.util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.Testbase;
import com.qa.client.RestClient;

public class getAPITest extends Testbase {

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
    public void getTest() throws ClientProtocolException, IOException {
        restclient = new RestClient();
        closeableHttpResponse = restclient.get(url);

        // getting status code
        int status_code = closeableHttpResponse.getStatusLine().getStatusCode();

        Assert.assertEquals(status_code, RESPONSE_STATUS_CODE_200, "Status code is not 200 OK	");

        System.out.println("Status code : " + status_code);

        // getting JSON String
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject response_Json = new JSONObject(responseString);

        System.out.println("Response JSON from API------->" + response_Json);

        // Per page value from the JSON response
		String perPageValue=TestUtil.getValueByJPath(response_Json,"/per_page");
        int i_perPageValue = Integer.parseInt(perPageValue);
		System.out.println(i_perPageValue);

		Assert.assertEquals(i_perPageValue, 6);

		// total value from the JSON response
        String totalValue=TestUtil.getValueByJPath(response_Json,"/total");
        int i_totalValue=Integer.parseInt(totalValue);
        System.out.println(i_totalValue);

        Assert.assertEquals(i_totalValue,12);


        // Fetch the value from the JSON Array
        

        // All headers
        Header[] headers = closeableHttpResponse.getAllHeaders();

        HashMap<String, String> allHeadersHashMap = new HashMap<String, String>();

        for (Header header : headers) {
            allHeadersHashMap.put(header.getName(), header.getValue());
        }

        System.out.println("All headers info" + allHeadersHashMap);
    }

}
