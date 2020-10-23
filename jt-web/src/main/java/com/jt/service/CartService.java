package com.jt.service;

import java.util.List;

import com.jt.pojo.Cart;

public interface CartService {

	List<Cart> findCardListByUserId(Long userId);

	void updateCartNum(Cart cart);

	void deleteCart(Cart cart);

	void inserCart(Cart cart);

	

	

}
