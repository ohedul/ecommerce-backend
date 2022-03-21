package bd.ohedul.erp.dto;

import java.util.List;

import bd.ohedul.erp.model.Buyer;

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
