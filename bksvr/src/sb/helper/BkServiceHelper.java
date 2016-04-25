package sb.helper;


public class BkServiceHelper {
   
	public BkServiceHelper() {
		
	}
	
	//simplicity rules, only handling strings initialy
	public String handleAnswer(String answerType) {
		if("accountAmount".equals(answerType)) {
			return 
					new StringBuilder().append("Your Accounts : <br/>")
					.append("Savings Account(********8432) : 200$ <br/>")
					.append("Checkin Account(********5555) : 300$ <br/>").toString();
		}
		else if("overdraft".equals(answerType)) {
			return "You were charged an overdraft fee of $25 this month.";
		}
		else if("sentChqBk".equals(answerType) || "homeLoanRate".equals(answerType) || "custServNum".equals(answerType)) {
			return FaqHelper.faqMap.get(answerType);
		}
		else if("custRepChat".equals(answerType)) {
			return FaqHelper.faqMap.get("Please wait while we connect you to a customer service representative.");
		}
		else if("amtWithStar".equals(answerType)) {
			return "You spent $45 on starbucks this month";
		}
		else if("creditCardBillAmt".equals(answerType)) {
			return "Your current credit card bill is : 200$";
		}
		else if("showMyTransactions".equals(answerType)) {
			return new StringBuilder().append("Your Last 10 transactions : /n")
			.append("04/04/2016 Starbucks 10$ <br/>")
			.append("04/04/2016 WholeFoods 20$ <br/>")
			.append("04/04/2016 NJTransit 10$ <br/>")
			.append("04/04/2016 NJTransit 40$ <br/>")
			.append("04/04/2016 NJTransit 10$ <br/>")
			.append("04/04/2016 WholeFoods 10$ <br/>")
			.append("04/04/2016 WholeFoods 10$ <br/>")
			.append("04/04/2016 WholeFoods 10$ <br/>")
			.append("04/04/2016 Starbucks 10$ <br/>")
			.append("04/04/2016 Starbucks 10$")
			.append("Checkin Account(3729823748923) : 300$").toString();
		}
		else return "Sorry we donot have a correct response at this time.<br/> Would you like to chat with a customer service representative";
		
	}
	
}
