package com.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.entity.Customer;
import com.entity.Purchase;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class PurchaseDAO {

	private Connection con;

	public PurchaseDAO(Connection con) {
		super();
		this.con = con;
	}

	public boolean addpurchase(Purchase purchase) {
		boolean f = false;
		try {
			String sql = "insert into purchase(customer_id,customer_name,product_name,price,quantity,unit,amount) values(?,?,?,?,?,?,?)";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setInt(1, purchase.getCustomer_id());
			ps.setString(2, purchase.getCustomer_name());
			ps.setString(3, purchase.getProduct_name());
			ps.setString(4, purchase.getPrice());
			ps.setString(5, purchase.getQuantity());
			ps.setString(6, purchase.getUnit());
			ps.setDouble(7, purchase.getAmount());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public List<Purchase> getAllPurchase() {
		List<Purchase> list = new ArrayList<Purchase>();
		Purchase p = null;
		try {
			String sql = "select * from purchase WHERE MONTH(purchase_date) = MONTH(CURRENT_DATE()) AND YEAR(purchase_date) = YEAR(CURRENT_DATE());";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				p = new Purchase();
				p.setPurchase_id(rs.getInt(1));
				p.setPurchase_date(rs.getDate(4));
				p.setCustomer_name(rs.getString(3));
				p.setProduct_name(rs.getString(5));
				p.setPrice(rs.getString(6));
				p.setQuantity(rs.getString(7));
				p.setUnit(rs.getString(8));
				list.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Purchase> getPurchaseDetail(String name, int month) {
		// Get the current month

		// System.out.print(name);
		// System.out.print(month);

		List<Purchase> list = new ArrayList<Purchase>();

		try {
			Statement stmt = (Statement) con.createStatement();

			String sql = "SELECT * FROM purchase WHERE customer_name ='" + name + "' AND MONTH(purchase_date) ='" + month + "' AND YEAR(purchase_date) = YEAR(CURRENT_DATE())";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String customer_name = rs.getString("customer_name");
				Date date = rs.getDate("purchase_date");
				String product_name = rs.getString("product_name");
				String price = rs.getString("price");
				String quantity = rs.getString("quantity");
				String unit = rs.getString("unit");
				Purchase p = new Purchase(customer_name, date, product_name, price, quantity, unit);
				list.add(p);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;

	}

	public boolean deletePurchase(String purchase_id) {

		boolean f = false;
		try {
			String sql = "delete from purchase where purchase_id=?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(sql);
			ps.setString(1, purchase_id);
			int i = ps.executeUpdate();
			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return f;
	}

	public double getTotalAmountByCustomer(String customerName) {
		double totalAmount = 0.0;
		try {
			String query = "SELECT SUM(amount) AS totalAmount FROM purchase WHERE customer_name = ?";
			PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
			statement.setString(1, customerName);

			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				totalAmount = resultSet.getDouble("totalAmount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalAmount;
	}

}
