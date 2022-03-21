package bd.ohedul.erp.model;

public class OrderDetailsModel {
	
	private Items item;
	
	private Integer quantity;
	
	private double subTotal;
	
	

	public OrderDetailsModel(Items item, Integer quantity, double subTotal) {
		super();
		this.item = item;
		this.quantity = quantity;
		this.subTotal = subTotal;
	}

	public void setItem(Items item) {
		this.item = item;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public Items getItem() {
		return item;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public double getSubTotal() {
		return subTotal;
	}
	
	

}
