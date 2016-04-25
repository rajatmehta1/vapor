package sb.rules;


import java.util.HashMap;
import java.util.Map;

public class Rules {
	
	private static Map<RuleVO,String> ruleMap;
	
	static {
		initRules();
	}

	public static void initRules() {
		if(ruleMap==null ||ruleMap.size()==0){
			ruleMap=new HashMap<RuleVO,String>();
			//TODO Get rules from DB 
			ruleMap.put(new RuleVO("Good Credit History"), "Increase Card Limit\nOffer New Credit Card");
			ruleMap.put(new RuleVO("Home Loan"), "For Best Offer call customer care number: 888-888-8888");
		}
		
	}

	public static Map<RuleVO, String> getRuleMap() {
		return ruleMap;
	}

}

