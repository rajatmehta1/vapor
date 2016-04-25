package sb.helper;

import java.util.HashMap;
import java.util.Map;

//a quick temporary class to hold the faqs
public class FaqHelper {

	public static Map<String, String> faqMap = new HashMap<String, String>();
	
	static {
		faqMap.put("sentChqBk", "We have entered your request for a new Cheque book. \n Your cheque book would arrive in a week.");
		faqMap.put("homeLoanRate", "The current home loan rate is 4%. Would you like a bank representative to reach out to you for home loan queries.");
		faqMap.put("custServNum", "Our customer service number is 1-800-999-9999");
	}
}
