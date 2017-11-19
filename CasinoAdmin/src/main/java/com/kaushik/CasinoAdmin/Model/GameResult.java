package com.kaushik.CasinoAdmin.Model;

public class GameResult {

	private int randomNumber;
	private int updateBalance;
	
	public GameResult() {
		super();
	}
	
	public GameResult(int randomNumber, int updateBalance) {
		super();
		this.randomNumber = randomNumber;
		this.updateBalance = updateBalance;
	}
	
	public int getRandomNumber() {
		return randomNumber;
	}
	
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	
	public int getUpdateBalance() {
		return updateBalance;
	}
	
	public void setUpdateBalance(int updateBalance) {
		this.updateBalance = updateBalance;
	}
	
}
