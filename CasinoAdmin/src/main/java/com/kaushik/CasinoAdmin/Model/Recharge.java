package com.kaushik.CasinoAdmin.Model;

public class Recharge {
	
//	private int id;
	private int balance;
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public Recharge(/*int id,*/ int balance) {
		super();
//		this.id = id;
		this.balance = balance;
	}
	public Recharge() {
		super();
		// TODO Auto-generated constructor stub
	}

}
