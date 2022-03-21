package bd.ohedul.erp.dto;

import java.util.List;

public class OrderForm {
	
	private List<Object> cartDto;
	private Long id;
	public OrderForm(List<Object> cartItems, Long id) {
		super();
		this.cartDto = cartItems;
		this.id = id;
	}
	public List<Object> getCartItems() {
		return cartDto;
	}
	public void setCartItems(List<Object> cartItems) {
		this.cartDto = cartItems;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

}
