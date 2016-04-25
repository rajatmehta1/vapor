package qas.qa.classifiers;

import java.io.FileInputStream;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class NamedEntityClassifier {
	//static String sentence = "How much money is there in my account.";
	//static String sentence = "How much amount is there in my account.";
	static String sentence = "How much did i spend on starbucks this summer";
	//static String sentence = "Mike can you transfer 300$ to my saving account";
	//static String sentence = "Was i charged any overdraft fee";
	//static String sentence = "can you send me a new cheque book";
	
	public void namedEntityClassify() {
		InputStream modelInToken = null;
		InputStream modelIn = null;
		
		try {
		
			//convert sentence into tokens
	    	modelInToken = new FileInputStream("src/resources/en-token.bin");
	    	TokenizerModel modelToken = new TokenizerModel(modelInToken); 
	    	Tokenizer tokenizer = new TokenizerME(modelToken);  
	    	String tokens[] = tokenizer.tokenize(sentence);

	    	//load custom titles model
	    	modelIn = new FileInputStream("src/resources/ner-acctdet.bin");
	    	
	    	//create NameFinder and call find method
	    	TokenNameFinderModel model = new TokenNameFinderModel(modelIn);
	    	NameFinderME nameFinder = new NameFinderME(model);
	    	Span nameSpans[] = nameFinder.find(tokens);
	    	
	    	//find probabilities for names
	    	double[] spanProbs = nameFinder.probs(nameSpans);
	    	
	    	//print titles with probabilities
	    	for( int i = 0; i<nameSpans.length; i++) {
	    		
	    		int tokensStart = nameSpans[i].getStart();
	    		int tokensEnd = nameSpans[i].getEnd();
	    		String title = "";	    		
	    			    		
	    		System.out.println(tokens[tokensStart]);
	    		
	    		System.out.println("Probability is: "+spanProbs[i]);
	    	}		    	
	    	
	    	//Results
	    	
	    	//1. Extra punctuation likely related to tokenization method.
	    	//The Call of the Wild ? 
	    	//Probability is: 0.9556878839087964
	    	
	    	//2. Lower probability. Maybe because of more complex sentence structure?
	    	//Crime and Punishment ? 
	    	//Probability is: 0.8622695215302271
	    	
	    	//3. Quotes not a problem.
	    	//Reading in the Brain ? 
	    	//Probability is: 0.95192707478283961
	    	
	    	//4. Lower probability. Maybe because title is at the beginning of sentence? More complex, like 2.
	    	//The Call of the Wild , 
	    	//Probability is: 0.8272024223804438
	    	
	    	//5. Lowercase "the" not included. Makes sense.
	    	//Call of the Wild ? 
	    	//Probability is: 0.8526001988043367
	    	
	    	//6. No title recognized when everything in lowercase. Clearly case plays a big role.
	    	
	    	//7. Odd. The lowercase "the" included with the title, unlike 5. Note lowest probability.
	    	//the Odyssey ? 
	    	//Probability is: 0.6439045773599029
		}
		catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static void main(String[] args) {
		NamedEntityClassifier ne = new NamedEntityClassifier();
				ne.namedEntityClassify();
			
	}
}
