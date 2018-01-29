package com.hibernate.model;

// Generated Jun 15, 2015 8:17:19 PM by Hibernate Tools 3.4.0.CR1

/**
 * Customer generated by hbm2java
 */
public class Customer  {

	private int txnId;
	private Transaction transaction;
	private String custName;
	private String custEmail;
	private String custAddress;

	public Customer() {
	}

	public Customer(Transaction transaction, String custName, String custAddress) {
		this.transaction = transaction;
		this.custName = custName;
		this.custAddress = custAddress;
	}

	public Customer(Transaction transaction, String custName, String custEmail,
			String custAddress) {
		this.transaction = transaction;
		this.custName = custName;
		this.custEmail = custEmail;
		this.custAddress = custAddress;
	}

	public int getTxnId() {
		return this.txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	public Transaction getTransaction() {
		return this.transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public String getCustName() {
		return this.custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustEmail() {
		return this.custEmail;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public String getCustAddress() {
		return this.custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

}