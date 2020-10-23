package com.jt.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	/**
	 * 1.用户发起post请求携带了itemCatId=560
	 * 2.servlet request  response
	 * 
	 * @return
	 */
	//实现根据id查询商品分类信息
	@RequestMapping("/queryItemName")
	public String findItemCatNameById(Long itemCatId) {
		
		return itemCatService.findItemCatNameById(itemCatId);
	}
	
	//查询全部数据的商品分类信息
	@RequestMapping("/list")
	public List<EasyUITree> findItemCatByParentId(){
		
		Long parentId = 0L;	//查询一级商品分类信息
		return itemCatService.findItemCatByParentId(parentId);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
