package com.entity;

import java.util.Date;

public class ClientPurchase {
	private int purchase_id;
	private int customer_id;
	private String customer_name;
	private Date purchase_date;
	private String product_name;
	private String price;
	private String quantity;
	private String unit;
	public ClientPurchase() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientPurchase(String product_name, String price, String quantity, String unit) {
		super();
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	public ClientPurchase(int purchase_id, int customer_id, String customer_name, Date purchase_date, String product_name,
			String price, String quantity, String unit) {
		super();
		this.purchase_id = purchase_id;
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.purchase_date = purchase_date;
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	public ClientPurchase(Date purchase_date, String product_name, String price, String quantity, String unit) {
		super();		
		this.purchase_date = purchase_date;
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
	}
	public ClientPurchase(int customer_id, String customer_name, String product_name, String price, String quantity,
			String unit) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
	}
	
	
	public ClientPurchase(String customer_name, String product_name, String price, String quantity, String unit) {
		super();
		this.customer_name = customer_name;
		this.product_name = product_name;
		this.price = price;
		this.quantity = quantity;
		this.unit = unit;
	}
	public int getPurchase_id() {
		return purchase_id;
	}
	public void setPurchase_id(int purchase_id) {
		this.purchase_id = purchase_id;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "Purchase [purchase_id=" + purchase_id + ", customer_id=" + customer_id + ", customer_name="
				+ customer_name + ", purchase_date=" + purchase_date + ", product_name=" + product_name + ", price="
				+ price + ", quantity=" + quantity + ", unit=" + unit + "]";
	}
	
}
