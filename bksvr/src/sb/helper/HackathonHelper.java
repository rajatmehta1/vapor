package sb.helper;

import java.util.List;

import sb.dao.HackathonDao;
import sb.dto.TransactionVO;
import sb.dto.UserVO;
import sb.rules.RuleVO;
import sb.rules.Rules;
import org.springframework.beans.factory.annotation.Autowired;


public class HackathonHelper {
	private HackathonDao hackathonDao;
	
	public String TransactionToString(List<TransactionVO> transactions){
		StringBuilder builder= new StringBuilder();
		if(transactions!=null && transactions.size()>0){
			for(TransactionVO transaction:transactions){
				builder.append(transaction.getTransactionDate()+","+transaction.getTransactionDetail()+","+transaction.getTransactionAmount()+"$\n");				
			}
		}
		return builder.toString();
	}
	
	public String getLastXTransaction(int accountNumber,int num){
		return TransactionToString(hackathonDao.getLastXTransaction(accountNumber,num));
		
	}
	
	public String getLastXDebitCreditTransaction(int accountNumber,int num,String type){
		return TransactionToString(hackathonDao.getLastXDebitCreditTransaction(accountNumber,num,type));
		
	}
	
	public String getLastTransactioninXDays(int accountNumber,int days){
		return TransactionToString(hackathonDao.getLastTransactioninXDays(accountNumber,days));
		
	}
	
	public String getLastDebitCreditTransactioninXDays(int accountNumber,int days,String type){
		return TransactionToString(hackathonDao.getLastDebitCreditTransactioninXDays(accountNumber,days,type));
		
	}
	
	public UserVO getUserDetails (String userName){
		return hackathonDao.getUserDetails(userName);
	}
	
	public String amountSpentInMerchantLastXdays(String merchant, int days,String type,int accountNumber){
		return new String("Amount spent on "+merchant+":"+hackathonDao.amountSpentInMerchantLastXdays(merchant, days,type,accountNumber)+"$");
	}
	
	public String overDraftFeeLastXdays(String merchant, int days,String type,int accountNumber){
		return new String("Overdraft fee chanrged:" +hackathonDao.amountSpentInMerchantLastXdays(merchant, days,type,accountNumber)+"$");
	}
	
	public String netIncome(int accountNumber,int days,String type){
		return new String("Your Net Income in last "+days+" days:" +hackathonDao.moneyDebitCreditinXdays(accountNumber,days,type)+"$");
	}
	
	public String netOverflow(int accountNumber,int days,String type){
		return new String("Your Net outflow in last "+days+":" +hackathonDao.moneyDebitCreditinXdays(accountNumber,days,type)+"$");
	}
	
	public void updateCreditCardPaymnet(int accountNumber,double amount){
		hackathonDao.updateCreditCardPaymnet(accountNumber, amount);
	}
	
	public String spendingHabit(String userName){
		int noofMonth=1;
		int result=hackathonDao.getCreditCardPaymnetCount(userName,noofMonth);
		if(result>=noofMonth){
			return Rules.getRuleMap().get(new RuleVO("Good Credit History"));
		}
		return "Bad Credit History";
	}

	public HackathonDao getHackathonDao() {
		return hackathonDao;
	}

	public void setHackathonDao(HackathonDao hackathonDao) {
		this.hackathonDao = hackathonDao;
	}

	
}
