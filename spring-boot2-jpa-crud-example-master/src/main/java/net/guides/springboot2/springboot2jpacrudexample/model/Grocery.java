package net.guides.springboot2.springboot2jpacrudexample.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "grocery")
public class Grocery {

	private long itemCode;
	private String itemName;
	private double amount;
	
	public Grocery() {
		
	}
	
	public Grocery(String itemName, double amount) {
		this.itemName = itemName;
		this.amount = amount;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getItemCode() {
		return itemCode;
	}
	public void setItemCode(long itemCode) {
		this.itemCode = itemCode;
	}
	
	@Column(name = "item_name", nullable = false)
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	@Column(name = "amount", nullable = false)
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Grocery [itemcode=" + itemCode + ", itemName=" + itemName + ", Amount=" + amount + "]";
	}
	
}
