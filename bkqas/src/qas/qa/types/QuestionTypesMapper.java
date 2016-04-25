package qas.qa.types;

import java.util.HashMap;
import java.util.Map;

public class QuestionTypesMapper {
   public static Map<String,String> questionTypesMap = new HashMap<String, String>();
   
   public QuestionTypesMapper() {
	   
   }
   
   static {
	   questionTypesMap.put("MA", "accountAmount");
	   questionTypesMap.put("MSG", "amtWithGas");
	   questionTypesMap.put("MSS", "amtWithStar");
	   questionTypesMap.put("MO", "overdraft");
	   questionTypesMap.put("FC", "sentChqBk");
	   questionTypesMap.put("FCSN", "custServNum");
	   questionTypesMap.put("CRC", "custRepChat");
	   questionTypesMap.put("CCBL", "creditCardBillAmt");
	   questionTypesMap.put("SMT", "showMyTransactions");
   }
   

}
