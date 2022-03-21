package bd.ohedul.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bd.ohedul.erp.dto.CartDto;
import bd.ohedul.erp.dto.OrderDto;
import bd.ohedul.erp.dto.OrderItemDto;
import bd.ohedul.erp.model.Buyer;
import bd.ohedul.erp.model.Cart;
import bd.ohedul.erp.model.CartItem;
import bd.ohedul.erp.model.Items;
import bd.ohedul.erp.repository.BuyerRepository;
import bd.ohedul.erp.repository.CartItemRepository;
import bd.ohedul.erp.repository.CartRepository;
import bd.ohedul.erp.repository.ItemRepository;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private BuyerRepository buyerRepository;
	@Autowired
	private ItemRepository itemRepository;
	
	public Long addCart(List<CartDto> cartDtos, Long userId) {
		try {
			Optional<Buyer> buyer = buyerRepository.findById(userId);
			Cart cart = new Cart();
			cart.setBuyer(buyer.get());
			Double totalAmount = 0.0;
			List<CartItem> cartItems = new ArrayList<>();
			
			for(CartDto cartDto: cartDtos) {
				Long itemId = cartDto.getItemId();
				Integer quantity = cartDto.getQuantity();
				
				Optional<Items> item = itemRepository.findById(itemId);
				Items items = item.get();
				Double price = items.getPrice();
				Double subTotal = price*quantity;
				totalAmount+= subTotal;
				CartItem cartItem = new CartItem();
				cartItem.setQuantity(quantity);
				cartItem.setSubTotal(subTotal);
				cartItem.setItem(items);
				cartItems.add(cartItem);
			}
			cart.setDelivered(false);
			cart.setTotalAmount(totalAmount);
			cartRepository.save(cart);
			for(CartItem item: cartItems) {
				item.setCart(cart);
				cartItemRepository.save(item);
			}
			return cart.getOrderId();
			
		}catch(NumberFormatException e){
			return 0L;
		}catch(Exception e) {
			return 0L;
		}
		
	}
	
	public List<OrderDto> getAllOrders(){
		List<OrderDto> orders = new ArrayList<>();
		for(Cart cart: cartRepository.findAll()) {
			OrderDto dto = new OrderDto();
			dto.setOrderId(cart.getOrderId());
			dto.setBuyerEmail(cart.getBuyer().getEmail());
			dto.setTotalamount(cart.getTotalAmount());
			List<OrderItemDto> orderItems = new ArrayList<>();
			cartItemRepository.findAllByCart(cart).forEach(cartItem ->{
				OrderItemDto itemDto = new OrderItemDto();
				itemDto.setOrderItemId(cartItem.getId());
				itemDto.setItemId(cartItem.getItem().getItemId());
				itemDto.setItemName(cartItem.getItem().getName());
				itemDto.setPrice(cartItem.getItem().getPrice());
				itemDto.setQuantity(cartItem.getQuantity());
				itemDto.setSubTotal(cartItem.getSubTotal());
				orderItems.add(itemDto);
			});
			dto.setOrderItems(orderItems);
			orders.add(dto);
		}
		return orders;
	}
	
	public void accept(Long id) {
		Cart cart = cartRepository.getById(id);
		cart.setDelivered(true);
		cartRepository.saveAndFlush(cart);
	}
	
	

}
