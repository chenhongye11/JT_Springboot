package com.jt;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.util.HttpClientService;

public class TestHttpClient {
	@Autowired
	HttpClientService httpClientService;

	@Test
	public void testGet() throws ClientProtocolException, IOException {

		CloseableHttpClient client = HttpClients.createDefault();

		String url = "https://www.baidu.com";

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse response = client.execute(httpGet);


		if (response.getStatusLine().getStatusCode() == 200) {
			System.out.println("Congragulation, your request sucess");

			HttpEntity httpEntity = response.getEntity();

			String result = EntityUtils.toString((httpEntity));
			System.out.println(result);

		} else {
			throw new RuntimeException();

		}

	}
	
	
	@Test 
	public void testGet2() throws ClientProtocolException, IOException {
		String url = "https://www.baidu.com";

		String result =httpClientService.doGet(url);
		System.out.println(result);
		
	}
	
	
	
	
	

}
