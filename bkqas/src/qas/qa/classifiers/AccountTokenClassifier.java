package qas.qa.classifiers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

public class AccountTokenClassifier {
	
	public TokenizerModel fetchTokenMd() {
		InputStream fin = null;
		TokenizerModel mdl = null;

		try {
		   fin = new FileInputStream("src/resources/en-token.bin");	
		   mdl = new TokenizerModel(fin);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (mdl != null) {
		    try {
		      fin.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
		return mdl;
	}
	
	public TokenizerME fetchTokenMe(TokenizerModel sm_) {
		return new TokenizerME(sm_);
	}
	
	public String[] fetchTokens(String s_) {
		TokenizerME sme = this.fetchTokenMe(this.fetchTokenMd());
		String _tkArr[] = sme.tokenize(s_);
		return _tkArr;
	}
	
	public static void main(String[] args) {
		String paragraph = "How much is there in my account? Did i get any overdraft fee.";
		AccountSenClassifier sc = new AccountSenClassifier();
		AccountTokenClassifier asc = new AccountTokenClassifier();
			String[] ss = sc.fetchSen(paragraph);
			for (String s : ss) {
				System.out.println(s);
				Tokenizer tm = WhitespaceTokenizer.INSTANCE;
				String[] tkArr = tm.tokenize(s);
				for (String tkn : tkArr) {
					System.out.println(" tkn --> " + tkn);
				}
			}
	}
	
}
