package qas.qa.classifiers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class AccountSenClassifier {

	public SentenceModel fetchSenMd() {
		InputStream fin = null;
		SentenceModel mdl = null;

		try {
		   fin = new FileInputStream("src/resources/en-sent.bin");	
		   mdl = new SentenceModel(fin);
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
	
	public SentenceDetectorME fetchSenMe(SentenceModel sm_) {
		return new SentenceDetectorME(sm_);
	}
	
	public String[] fetchSen(String txt_) {
		SentenceDetectorME sme = this.fetchSenMe(this.fetchSenMd());
		String sen[] = sme.sentDetect(txt_);
		return sen;
	}
	
	public static void main(String[] args) {
		String paragraph = "How much is there in my account. Did i get any overdraft fee.";
		AccountSenClassifier asc = new AccountSenClassifier();
			String[] ss = asc.fetchSen(paragraph);
			for (String s : ss) {
				System.out.println(s);
			}
	}
	
}
