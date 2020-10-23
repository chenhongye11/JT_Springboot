package com.jt.service;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIData;

public interface ItemService {

	EasyUIData findItemByPage(Integer page, Integer rows);

	void save(Item item, ItemDesc itemDesc);

	void updateItem(Item item, ItemDesc itemDesc);

	void deleteItem(String ids);

	void updateStatus(Long[] ids, int status);

	ItemDesc findItemDescById(Long itemId);
	
	Item findItemById(Long itemId);
}
