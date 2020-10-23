package com.jt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.RestartScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.pojo.vo.SysResult;
import com.jt.service.ItemService;
import com.jt.vo.EasyUIData;

@RestController
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	//http://localhost:8091/item/query?page=1&rows=20
	//查询商品列表信息 分页查询
	@RequestMapping("/query")
	public EasyUIData findItemByPage(Integer page,Integer rows) {
		
		return itemService.findItemByPage(page,rows);
	}
	
	
	
	
	@RequestMapping("/save")
	public SysResult saveItem(Item item,ItemDesc itemDesc) {
		try{
			itemService.save(item,itemDesc);
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	

	@RequestMapping("/update")
	public SysResult updateItem(Item item, ItemDesc itemDesc) {
		
		try {
			itemService.updateItem(item,itemDesc);
			
			return SysResult.ok();
		}catch(Exception e){
			e.printStackTrace();
			return SysResult.fail();
		}
		
	}
	
	
	@RequestMapping("/delete")
	public SysResult deleteItem(String ids) {
		try {
			itemService.deleteItem(ids);
			return SysResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.fail();
		}
	}
	
	
	@RequestMapping("/instock")
	public SysResult instock(Long[] ids) {
		try {
			Integer status = 2;
			itemService.updateStatus(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			
			
			e.printStackTrace();
			return SysResult.fail();
		}
		
	}
	
	
	
	@RequestMapping("/reshelf")
	public SysResult reshelf(Long[] ids) {
		try {
			Integer status = 1;
			itemService.updateStatus(ids,status);
			return SysResult.ok();
		} catch (Exception e) {
			
			
			e.printStackTrace();
			return SysResult.fail();
		}
		
	}
	
	///item/query/item/desc/
	@RequestMapping("/query/item/desc/{itemId}")
	public SysResult findItemDescById (@PathVariable Long itemId) {
		try {
			
			ItemDesc itemDesc =itemService.findItemDescById(itemId);
			SysResult sysResult=SysResult.ok();
			sysResult.setData(itemDesc);
			return sysResult;
		} catch (Exception e) {
			
			
			e.printStackTrace();
			return SysResult.ok();
		}
	}
	
	
	
}
