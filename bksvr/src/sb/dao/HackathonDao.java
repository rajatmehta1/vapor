package sb.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import sb.dto.TransactionVO;
import sb.dto.UserVO;

public interface HackathonDao {

	//type debit or credit
	public abstract double moneyDebitCreditinXdays(int accountNumber, int days,
			String type);

	//Total net addition/deduction in last x days
	public abstract double netMoneyFlowinXdays(int accountNumber, int days);

	//last x transactions of account
	public abstract List<TransactionVO> getLastXTransaction(int accountNumber,
			int num);

	//last debit/credit x transactions of account
	public abstract List<TransactionVO> getLastXDebitCreditTransaction(
			int accountNumber, int num, String type);

	//x transaction in n days	
	public abstract List<TransactionVO> getLastTransactioninXDays(
			int accountNumber, int days);

	//x debit/credittransaction in n days
	public abstract List<TransactionVO> getLastDebitCreditTransactioninXDays(
			int accountNumber, int days, String type);

	public abstract UserVO getUserDetails(String userName);

	public abstract double amountSpentInMerchantLastXdays(String merchant,
			int days, String type, int accountNumber);

	public abstract void updateCreditCardPaymnet(int accountNumber,
			double amount);

	public abstract int getCreditCardPaymnetCount(String userName,
			int noOfMonths);

}