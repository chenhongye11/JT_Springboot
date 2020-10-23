package com.jt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jt.pojo.Cart;
import com.jt.pojo.vo.SysResult;
import com.jt.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	
	@Autowired
	CartService cartService;
	
	@RequestMapping("/show")
	public String findCardList(Model model) {
		Long userId = 7L;
		List<Cart> cartList = cartService.findCardListByUserId(userId);
		model.addAttribute("cartList",cartList);
		
		return "cart";
	}
	
	@RequestMapping("/update/num/{itemId}/{num}")
	public SysResult UpdateCartNum(Cart cart) {
		try {
			Long userId=7L;
			cart.setUserId(userId);
			cartService.updateCartNum(cart);
			return SysResult.ok();
		} catch (Exception e) {
			return SysResult.fail();
		}
		
		
		
	}
	
	
	
	@RequestMapping("/delete/{itemId}")
	public String deleteCart(Cart cart) {
		
		
		Long userId = 7L;
		cart.setUserId(userId);
		cartService.deleteCart(cart);
		
		return "redirect:/cart.show.html";
	}
	
	@RequestMapping("/add/{itemId}")
	public String insertCart(Cart cart) {
		Long userId=7L;
		cart.setUserId(userId);
		cartService.inserCart(cart);
		
		return "redirect:/cart/show.html";
	}

}
