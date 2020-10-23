package com.jt.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.CartMapper;
import com.jt.pojo.Cart;
import com.jt.pojo.vo.SysResult;

public class CartServiceImpl implements CartService {
	private CartMapper cartMapper;
	@Override
	public List<Cart> findCardListByUserId(Long userId) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>();
		queryWrapper.eq("user_id", userId);
		return cartMapper.selectList(queryWrapper);
		
	}
	@Override
	public void updateCartNum(Cart cart) {
		// TODO Auto-generated method stub
		Cart tempCart= new Cart();
		tempCart.setNum(cart.getNum()).setUpdated(new Date());
		
		UpdateWrapper<Cart> updateWrapper = new UpdateWrapper<Cart>();
		updateWrapper.eq("userId", cart.getUserId()).eq("item_id", cart.getItemId());
		cartMapper.update(tempCart, updateWrapper);
	}
	@Override
	public void deleteCart(Cart cart) {
		
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<Cart>(cart);
		cartMapper.delete(queryWrapper);
		
		
		
		
	}
	@Override
	@Transactional
	public void inserCart(Cart cart) {
		QueryWrapper<Cart> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("user_id",cart.getUserId()).eq("item_id",cart.getItemId());
		
		Cart cartDB = cartMapper.selectOne(queryWrapper);
		if(cartDB == null) {
			cart.setCreated(new Date()).setUpdated(cart.getCreated());
			cartMapper.insert(cart);
		}else {
			int num = cart.getNum() + cartDB.getNum();
			
			cartDB.setNum(num).setUpdated(new Date());
			cartMapper.updateById(cartDB);
		}
		
	}
	
	

}
