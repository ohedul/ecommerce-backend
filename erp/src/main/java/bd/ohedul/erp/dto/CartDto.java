package bd.ohedul.erp.dto;

public class CartDto {
	
	private Long itemId;
	private Integer quantity;
	
	public CartDto(Long itemId, Integer quantity) {
		super();
		this.itemId = itemId;
		this.quantity = quantity;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
