package bd.ohedul.erp.model;

import java.util.List;

public class OrderModel {
	
	private Buyer buyer;
	private OrderDetailsModel[] orderDetails;
	private Double totalAmount;
	public OrderModel(Buyer buyer, OrderDetailsModel[] orderDetails, Double totalAmount) {
		super();
		this.buyer = buyer;
		this.orderDetails = orderDetails;
		this.totalAmount = totalAmount;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public OrderDetailsModel[] getOrderDetails() {
		return orderDetails;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public void setOrderDetails(OrderDetailsModel[] orderDetails) {
		this.orderDetails = orderDetails;
	}
	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
	
	

}
