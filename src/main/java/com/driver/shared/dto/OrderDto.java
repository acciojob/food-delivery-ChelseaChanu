package com.driver.shared.dto;

public class OrderDto{

	private long id;
	private String orderId;
	private float cost;
	private String[] items;
	private String userId;
	private boolean status;

	public OrderDto() {
	}

	public OrderDto(long id, String orderId, float cost, String[] items, String userId, boolean status) {
		this.id = id;
		this.orderId = orderId;
		this.cost = cost;
		this.items = items;
		this.userId = userId;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	public String [] getItems() {
		return items;
	}

	public void setItems(String [] items) {
		this.items = items;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
