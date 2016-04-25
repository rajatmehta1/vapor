package qas.qa.classifiers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;

public class TimeClassifier {

	public TokenNameFinderModel fetchSenMd() {
		InputStream modelIn = null; 
		TokenNameFinderModel model = null;

		try {
			modelIn = new FileInputStream("src/resources/en-ner-time.bin");
			model = new TokenNameFinderModel(modelIn);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		return model;
	}
	
	public TokenizerME fetchSenMe(TokenizerModel sm_) {
		return new TokenizerME(sm_);
	}
	
	public void fetchSen(String[] tokens) {
		NameFinderME sme = new NameFinderME(this.fetchSenMd());
		Span span[] = sme.find(tokens);
		for (Span span2 : span) {
			System.out.println(tokens[span2.getStart()]);
		}
	}
	
	public static void main(String[] args) {
		String paragraph = "How much is there 12:30am.";
		String[] tkns = {"is", "it", "12:30a.m","now"
				
		};
		TimeClassifier asc = new TimeClassifier();
			asc.fetchSen(tkns);
	}
	
	
}
