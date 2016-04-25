package sb.dao.impl;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import sb.dao.HackathonDao;
import sb.dto.AccountVO;
import sb.dto.TransactionVO;
import sb.dto.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

public class HackathonDaoImpl implements HackathonDao {

	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;
		
	//type debit or credit
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#moneyDebitCreditinXdays(int, int, java.lang.String)
	 */
	@Override
	public double moneyDebitCreditinXdays(int accountNumber,int days,String type){
		String sql="select sum(t.Transaction_Amount)  from  hackathon.Transaction t"+
				" where t.Account_Number=:account_number"+
				" and t.Transaction_Type=:type"+
				" and t.updted_date>(CURRENT_TIMESTAMP-INTERVAL :days day)" +
				" group by t.Account_Number";
		Map<String,Object> params= new HashMap<String,Object>(3);
		params.put("account_number", accountNumber);
		params.put("days", days);
		params.put("type", type);			
		
		return namedJdbcTemplate.queryForObject(sql, params, Double.class);
		
		
	}	
	
	//Total net addition/deduction in last x days
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#netMoneyFlowinXdays(int, int)
	 */
	@Override
	public double netMoneyFlowinXdays (int accountNumber,int days){
		String sql="select sum(credit.Transaction_Amount-debit.Transaction_Amount) from "+
				" (select t.Account_Number,sum(t.Transaction_Amount)  Transaction_Amount from  hackathon.Transaction t "+
				" where t.Account_Number=:account_number"+
				" and t.Transaction_Type='Credit'"+
				" and t.updted_date>(CURRENT_TIMESTAMP-INTERVAL :days day)" +
				" group by t.Account_Number) credit,"+
				" (select t.Account_Number,sum(t.Transaction_Amount)  Transaction_Amount from  hackathon.Transaction t "+
				" where t.Account_Number=:account_number"+
				" and t.Transaction_Type='Debit'"+
				" and t.updted_date>(CURRENT_TIMESTAMP-INTERVAL :days day)" +
				" group by t.Account_Number) debit"+
				" where credit.Account_Number=Debit.Account_Number";		
		Map<String,Object> params= new HashMap<String,Object>(2);
		params.put("account_number", accountNumber);
		params.put("days", days);				
		return namedJdbcTemplate.queryForObject(sql, params, Double.class);
	}
	//last x transactions of account
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#getLastXTransaction(int, int)
	 */
	@Override
	public List<TransactionVO> getLastXTransaction(int accountNumber,int num){
		String sql="select t.Transaction_Id,t.Account_Number, t.Transaction_Detail, t.Transaction_Type, t.Transaction_Amount, t.Updted_Date from hackathon.Transaction t"+
				" where t.Account_Number=:account_number"+
				" order by t.Updted_Date DESC" +
				" limit :num";
		Map<String,Object> params= new HashMap<String,Object>(2);
		params.put("account_number", accountNumber);
		params.put("num", num);	
		return namedJdbcTemplate.query(sql,params, new ResultSetExtractor<List<TransactionVO>>() {
			public List<TransactionVO> extractData(ResultSet rs) throws SQLException,DataAccessException{						
				List<TransactionVO> transactions= new ArrayList<TransactionVO>();				
				while(rs.next()){	
					
					TransactionVO transaction= new TransactionVO();
					transaction.setAccountNumber(rs.getInt("Account_Number"));
					transaction.setTransactionId(rs.getInt("Transaction_Id"));
					transaction.setTransactionDetail(rs.getString("Transaction_Detail"));
					transaction.setTransactionType(rs.getString("Transaction_Type"));					
					transaction.setTransactionAmount(rs.getDouble("Transaction_Amount"));
					transaction.setTransactionDate(rs.getDate("Updted_Date"));
					transactions.add(transaction);
					
				}			
				return transactions;
				
			}
		});
		
	}
	
	//last debit/credit x transactions of account
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#getLastXDebitCreditTransaction(int, int, java.lang.String)
	 */
	@Override
	public List<TransactionVO> getLastXDebitCreditTransaction(int accountNumber,int num,String type){
		String sql="select t.Transaction_Id,t.Account_Number, t.Transaction_Detail, t.Transaction_Type, t.Transaction_Amount, t.Updted_Date from hackathon.Transaction t"+
				" where t.Account_Number=:account_number"+
				" and t.Transaction_Type=:type" +
				" order by t.Updted_Date DESC" +
				" limit :num";
		Map<String,Object> params= new HashMap<String,Object>(3);
		params.put("account_number", accountNumber);
		params.put("num", num);	
		params.put("type", type);
		return namedJdbcTemplate.query(sql,params, new ResultSetExtractor<List<TransactionVO>>() {
			public List<TransactionVO> extractData(ResultSet rs) throws SQLException,DataAccessException{						
				List<TransactionVO> transactions= new ArrayList<TransactionVO>();				
				while(rs.next()){	
					
					TransactionVO transaction= new TransactionVO();
					transaction.setAccountNumber(rs.getInt("Account_Number"));
					transaction.setTransactionId(rs.getInt("Transaction_Id"));
					transaction.setTransactionDetail(rs.getString("Transaction_Detail"));
					transaction.setTransactionType(rs.getString("Transaction_Type"));					
					transaction.setTransactionAmount(rs.getDouble("Transaction_Amount"));
					transaction.setTransactionDate(rs.getDate("Updted_Date"));
					transactions.add(transaction);
					
				}			
				return transactions;
				
			}
		});
		
	}
	//x transaction in n days	
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#getLastTransactioninXDays(int, int)
	 */
	@Override
	public List<TransactionVO> getLastTransactioninXDays(int accountNumber,int days){
		String sql="select t.Transaction_Id,t.Account_Number, t.Transaction_Detail, t.Transaction_Type, t.Transaction_Amount, t.Updted_Date from hackathon.Transaction t"+
				" where t.Account_Number=:account_number"+
				" and t.updted_date>(CURRENT_TIMESTAMP-INTERVAL :days day)";
		Map<String,Object> params= new HashMap<String,Object>(2);
		params.put("account_number", accountNumber);
		params.put("days", days);	
		return namedJdbcTemplate.query(sql,params, new ResultSetExtractor<List<TransactionVO>>() {
			public List<TransactionVO> extractData(ResultSet rs) throws SQLException,DataAccessException{						
				List<TransactionVO> transactions= new ArrayList<TransactionVO>();				
				while(rs.next()){	
					
					TransactionVO transaction= new TransactionVO();
					transaction.setAccountNumber(rs.getInt("Account_Number"));
					transaction.setTransactionId(rs.getInt("Transaction_Id"));
					transaction.setTransactionDetail(rs.getString("Transaction_Detail"));
					transaction.setTransactionType(rs.getString("Transaction_Type"));					
					transaction.setTransactionAmount(rs.getDouble("Transaction_Amount"));
					transaction.setTransactionDate(rs.getDate("Updted_Date"));
					transactions.add(transaction);
					
				}			
				return transactions;
				
			}
		});
		
	
		
	}
	
	//x debit/credittransaction in n days
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#getLastDebitCreditTransactioninXDays(int, int, java.lang.String)
	 */
	@Override
	public List<TransactionVO> getLastDebitCreditTransactioninXDays(int accountNumber,int days,String type){
		String sql="select t.Transaction_Id,t.Account_Number, t.Transaction_Detail, t.Transaction_Type, t.Transaction_Amount, t.Updted_Date from hackathon.Transaction t"+
				" where t.Account_Number=:account_number"+
				" and t.Transaction_Type=:type" +
				" and t.updted_date>(CURRENT_TIMESTAMP-INTERVAL :days day)";
		Map<String,Object> params= new HashMap<String,Object>(3);
		params.put("account_number", accountNumber);
		params.put("days", days);	
		params.put("type", type);
		return namedJdbcTemplate.query(sql,params, new ResultSetExtractor<List<TransactionVO>>() {
			public List<TransactionVO> extractData(ResultSet rs) throws SQLException,DataAccessException{						
				List<TransactionVO> transactions= new ArrayList<TransactionVO>();				
				while(rs.next()){	
					
					TransactionVO transaction= new TransactionVO();
					transaction.setAccountNumber(rs.getInt("Account_Number"));
					transaction.setTransactionId(rs.getInt("Transaction_Id"));
					transaction.setTransactionDetail(rs.getString("Transaction_Detail"));
					transaction.setTransactionType(rs.getString("Transaction_Type"));					
					transaction.setTransactionAmount(rs.getDouble("Transaction_Amount"));
					transaction.setTransactionDate(rs.getDate("Updted_Date"));
					transactions.add(transaction);
					
				}			
				return transactions;
				
			}
		});
		
	
		
	}
	
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#getUserDetails(java.lang.String)
	 */
	@Override
	public UserVO getUserDetails(final String userName){
		
		String sql="select u.User_Id,u.USER_NAME,u.Email,a.Account_Number,a.Account_Balance,a.Account_Type,a.card_limit,a.card_Bill "
				+ "from hackathon.user u, hackathon.Account a "
				+ "where u.User_Id=a.User_Id and u.user_name=:user_name";				
		Map<String,Object> params= new HashMap<String,Object>(1);
		params.put("user_name", userName);
		return namedJdbcTemplate.query(sql,params, new ResultSetExtractor<UserVO>() {
			public UserVO extractData(ResultSet rs) throws SQLException,DataAccessException{
				UserVO userVO=new UserVO();				
				List<AccountVO> accounts= new ArrayList<AccountVO>();
				userVO.setUserName(userName);
				while(rs.next()){	
					if(rs.getRow()==1){
						userVO.setEmail(rs.getString("Email"));
						userVO.setUserId(rs.getInt("User_Id"));
					}
					AccountVO account= new AccountVO();
					account.setAccountNumber(rs.getInt("Account_Number"));
					account.setAccountType(rs.getString("Account_Type"));
					account.setAccountBalance(rs.getDouble("Account_Balance"));
					account.setCardBill(rs.getDouble("card_Bill"));
					account.setCardLimit(rs.getDouble("card_limit"));
					accounts.add(account);
					
				}
				userVO.setAccounts(accounts);
				return userVO;
				
			}
		});
		
	}
	
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#amountSpentInMerchantLastXdays(java.lang.String, int, java.lang.String, int)
	 */
	@Override
	public double amountSpentInMerchantLastXdays(String merchant,int days,String type,int accountNumber){
		String sql="select sum(t.Transaction_Amount) from transaction t "+
				" where INSTR(t.Transaction_Detail,:merchant)>0"+
				" and t.Transaction_Type=:type"+
				" and t.updted_date>(CURRENT_TIMESTAMP-INTERVAL :days day)"+
				" and t.account_number=:accountNumber";
				
		Map<String,Object> params= new HashMap<String,Object>(4);
		params.put("merchant", merchant);
		params.put("days", days);
		params.put("type", type);			
		params.put("accountNumber", accountNumber);
		return namedJdbcTemplate.queryForObject(sql, params, Double.class);
		
		
	}	
	
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#updateCreditCardPaymnet(int, double)
	 */
	@Override
	@Transactional
	public void updateCreditCardPaymnet(int accountNumber,double amount){
		String insertTransaction="insert into hackathon.Transaction (Account_Number,Transaction_Type,Transaction_Amount,Transaction_Detail,Updated_By) values (?,?,?,?,?)";
		String updateAccount="update account set Account_Balance=Account_Balance+?,card_bill=card_bill-? where account_number=?";
		
		Object[] inparams = {accountNumber,"Credit",amount,"Credit Card Payment","test"};
		int[] intypes = {Types.INTEGER,Types.VARCHAR,Types.DOUBLE,Types.VARCHAR,Types.VARCHAR};
		jdbcTemplate.update(insertTransaction, new Object[] {accountNumber,"Credit",amount,"Credit Card Payment","test"});
		
		Object[] params = {amount,amount,accountNumber};
		int[] types = {Types.DOUBLE,Types.DOUBLE,Types.INTEGER};
		jdbcTemplate.update(updateAccount, params, types);
	}
	
	/* (non-Javadoc)
	 * @see sb.dao.impl.HackathonDao#getCreditCardPaymnetCount(java.lang.String, int)
	 */
	@Override
	public int getCreditCardPaymnetCount(String userName,int noOfMonths){
		String sql="select count(*) from hackathon.transaction t,hackathon.account a, hackathon.user u "+
				" where u.User_Id=a.user_id "+
				" and a.Account_Number=t.Account_Number "+
				" and a.Account_Type='CreditCard'"+
				" and t.updted_date>(CURRENT_TIMESTAMP-INTERVAL :noOfMonths Month) "+
				" and t.Transaction_Detail='Credit Card Payment' "+
				" and t.Transaction_Amount>a.card_min_payment"+
				" and u.user_name=:userName";
		Map<String,Object> params= new HashMap<String,Object>(2);
		params.put("userName", userName);
		params.put("noOfMonths", noOfMonths);
			
		return namedJdbcTemplate.queryForObject(sql, params, Integer.class);
		
	}
	
	

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
	
}

