package com.jt.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.util.HttpClientService;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	HttpClientService httpClientService;

	@Override
	public Item findItemById(Long userId) throws JsonParseException, JsonMappingException, IOException {
		String url = "http://localhost:8091/web/item/findItemById";
		Map<String,String> params = new HashMap<>();
		String itemId=String.valueOf(userId);
		params.put("id", itemId);
		
		String result = httpClientService.doGet(url,params);
		
		ObjectMapper objectMapper= new ObjectMapper();
		
		
		Item item=objectMapper.readValue(result, Item.class);
		
		return item;
		
		
	}

	@Override
	public ItemDesc findItemDescById(Long userId) throws ClientProtocolException, IOException {
		
		String url = "http://localhost:8091/web/item/findItemDescById";
		Map<String,String> params = new HashMap<>();
		String itemId=String.valueOf(userId);
		params.put("id", itemId);
		
		String result = httpClientService.doGet(url,params);
		
		ObjectMapper objectMapper= new ObjectMapper();
		
		
		ItemDesc itemDesc=objectMapper.readValue(result, ItemDesc.class);
		
		return itemDesc;
	}
	
}
