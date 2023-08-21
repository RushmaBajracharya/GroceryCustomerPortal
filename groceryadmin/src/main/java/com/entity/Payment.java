package com.entity;

public class Payment {
	private int payment_id;
	private String customer_name;
	private double total;
	private double paid_amount;
	private String status;
	private int customer_id;
	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Payment(String customer_name, double total, String status, int customer_id) {
		super();
		this.customer_name = customer_name;
		this.total = total;
		this.status = status;
		this.customer_id = customer_id;
	}

	public Payment(int payment_id, double paid_amount) {
		super();
		this.payment_id = payment_id;
		this.paid_amount = paid_amount;
	}

	public Payment(int payment_id, String customer_name, double total, double paid_amount, String status) {
		super();
		this.payment_id = payment_id;
		this.customer_name = customer_name;
		this.total = total;
		this.paid_amount = paid_amount;
		this.status = status;
	}

	public Payment(String customer_name, double total,double paid_amount, String status) {
		super();
		this.customer_name = customer_name;
		this.total = total;
		this.paid_amount=paid_amount;
		this.status = status;
	}
	
	public Payment(String customer_name, double total, String status) {
		super();
		this.customer_name = customer_name;
		this.total = total;
		this.status = status;
	}
	
	public Payment(int payment_id, String customer_name, double total, String status) {
		super();
		this.payment_id = payment_id;
		this.customer_name = customer_name;
		this.total = total;
		this.status = status;
	}
	public int getPayment_id() {
		return payment_id;
	}
	public void setPayment_id(int payment_id) {
		this.payment_id = payment_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getPaid_amount() {
		return paid_amount;
	}
	public void setPaid_amount(double paid_amount) {
		this.paid_amount = paid_amount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	
}
