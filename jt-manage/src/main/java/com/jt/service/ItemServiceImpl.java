package com.jt.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUIData;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	
	
	@Autowired
	private ItemDescMapper itemDescMapper;

	@Override
	public EasyUIData findItemByPage(Integer page, Integer rows) {
		//1.获取商品记录总数
		int total = itemMapper.selectCount(null);
		
		/**
		 * 2.分页之后回传数据
		 * sql: select * from tb_item limit 起始位置,查询行数
		 * 第1页:  20
		 * 	select * from tb_item limit 0,20
		 * 第2页:  
		 * 	select * from tb_item limit 20,20
		 * 第3页:
		 *  select * from tb_item limit 40,20
		 * 第N页:
		 * 	 select * from tb_item 
		 * 			limit (page-1)rows,rows
		 */
		//计算起始位置
		int start = (page-1)*rows;
		List<Item> itemList = itemMapper.findItemByPage(start,rows);
		
		return new EasyUIData(total,itemList);
	}
	
	
	
	@Transactional
	public void save(Item item, ItemDesc itemDesc) {
		item.setStatus(1).setCreated(new Date()).setUpdated(item.getCreated());
		
		System.out.println(item.getId());
		itemMapper.insert(item);
		
		System.out.println(item.getId());
		itemDesc.setItemId(item.getId());
		itemDesc.setCreated(new Date()).setUpdated(item.getCreated());
		
		itemDescMapper.insert(itemDesc);
		
		
		
		
	}
	
	


	@Transactional(propagation = Propagation.REQUIRED)//事务的传播属性 default: propagation.required
	@Override
	public void updateItem(Item item, ItemDesc itemDesc) {
		item.setStatus(1).setUpdated(new Date());
		itemMapper.updateById(item);
		itemDesc.setItemId(item.getId()).setUpdated(item.getUpdated());
		itemDescMapper.updateById(itemDesc);
		
	}



	@Override
	@Transactional
	public void deleteItem(String ids) {
		List<String> StringList=Arrays.asList(ids.split("\"\\\\s*,\\\\s*\""));
		List<Integer> idList = StringList.stream().map(s->Integer.parseInt(s)).collect(Collectors.toList());
		itemMapper.deleteBatchIds(idList);
		
		
		
		itemDescMapper.deleteBatchIds(idList);
	}



	@Override
	public void updateStatus(Long[] ids, int status) {
		UpdateWrapper<Item> updateWrapper=new UpdateWrapper();
		updateWrapper.in("id", ids);
		Item item = new Item().setStatus(status);
		item.setUpdated(new Date());
		
		itemMapper.update(item, updateWrapper);
		
	}



	@Override
	public ItemDesc findItemDescById(Long itemId) {
		ItemDesc itemDesc=itemDescMapper.selectById(itemId);
		System.out.println(itemDesc.getItemDesc());
		return itemDesc;
		
	}



	@Override
	public Item findItemById(Long itemId) {
		Item item= itemMapper.selectById(itemId);
		return item;
	}
	
}
