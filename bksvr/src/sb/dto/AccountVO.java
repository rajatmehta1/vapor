package sb.dto;

import java.util.ArrayList;
import java.util.List;

public class AccountVO {
	private int accountNumber;
	private String accountType;
	private double accountBalance;
	private double cardBill;
	private double cardLimit;
	private List<TransactionVO> transactions;
	
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public List<TransactionVO> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<TransactionVO> transactions) {
		this.transactions = transactions;
	}
	public double getCardBill() {
		return cardBill;
	}
	public void setCardBill(double cardBill) {
		this.cardBill = cardBill;
	}
	public double getCardLimit() {
		return cardLimit;
	}
	public void setCardLimit(double cardLimit) {
		this.cardLimit = cardLimit;
	} 
	
}

