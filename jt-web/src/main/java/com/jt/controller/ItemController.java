package com.jt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.service.ItemService;
import com.jt.util.HttpClientService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	/**
	 * 	根据商品id查询后台服务器数据
	 * 	业务步骤:
	 * 	1.在前台service中实现httpClient调用
	 * 	2.后台根据itemId查询数据库返回对象的json串
	 *  3.将json转化为item对象
	 *  4.将item对象保存到request域中
	 *  5.返回页面逻辑名称 item
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@RequestMapping("/items/{itemId}")
	public String findItemById(@PathVariable Long itemId,
			Model model) throws JsonParseException, JsonMappingException, IOException {
		Item item = itemService.findItemById(itemId);
		ItemDesc itemDesc = itemService.findItemDescById(itemId);
		model.addAttribute("item", item);
		model.addAttribute("itemDesc", itemDesc);
		return "item";
	}
	
}
