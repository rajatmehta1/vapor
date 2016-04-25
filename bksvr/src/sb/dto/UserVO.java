package sb.dto;

import java.util.ArrayList;
import java.util.List;

public class UserVO {
	private int userId;
	private String userName;
	private String email;
	private List<AccountVO> accounts;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<AccountVO> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<AccountVO> accounts) {
		this.accounts = accounts;
	}
	
	/*public List<String> getAccountBalance(){
		List<String> accountbalances=null;
		if(null!=accounts && accounts.size()>0){
			accountbalances= new ArrayList<String>();
			for(AccountVO account:accounts){
				accountbalances.add("Account Number: "+account.getAccountNumber()+" Account Balance: "+account.getAccountBalance());
			}
		}
		return accountbalances;		
	}*/
	
	/*public List<String> getAccountBalance(String type){
		List<String> accountbalances=null;
		if(null!=accounts && accounts.size()>0){
			accountbalances= new ArrayList<String>();
			for(AccountVO account:accounts){
				if(type.equalsIgnoreCase(account.getAccountType())){
					accountbalances.add("Account Number: "+account.getAccountNumber()+" Account Balance: "+account.getAccountBalance());
				}				
			}
		}
		return accountbalances;		
	}*/
	
	public String getAccountBalance(){
		StringBuilder accountbalance=null;
		if(null!=accounts && accounts.size()>0){
			accountbalance= new StringBuilder();
			for(AccountVO account:accounts){				
				accountbalance.append(account.getAccountType()+" ("+account.getAccountNumber()+"):"+account.getAccountBalance()+"$\n" );							
			}
		}
		if(null!=accountbalance)	{
			return accountbalance.toString();
		}
		
		return null;
	}
	
	public String getCreditCardBalance(){
		StringBuilder accountbalance=null;
		if(null!=accounts && accounts.size()>0){
			accountbalance= new StringBuilder();
			for(AccountVO account:accounts){	
				if("CreditCard".equals(account.getAccountType())){
					accountbalance.append("Credit Card Bill: "+ account.getCardBill()+"$\n"+"Credit Card Limit: "+account.getCardLimit()+"$\n");					
				}											
			}
		}
		if(null!=accountbalance)	{
			return accountbalance.toString();
		}
		
		return null;
		
	}
		
}

