package com.jt.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;

public interface ItemService {

	Item findItemById(Long userId) throws JsonParseException, JsonMappingException, IOException;

	ItemDesc findItemDescById(Long itemId) throws ClientProtocolException, IOException;
	
}
