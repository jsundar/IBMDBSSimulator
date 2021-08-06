package com.ibm.dbs.atm;

import java.sql.Timestamp;

public class Transactions {

	private Timestamp createdDate;
	private String transactionType;
	private double amount;
	private double closingBalance;
	
	
	
	public Transactions(Timestamp createdDate, String transactionType, double amount, double closingBalance) {
		super();
		this.createdDate = createdDate;
		this.transactionType = transactionType;
		this.amount = amount;
		this.closingBalance = closingBalance;
	}
	
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
	
	
	
}
