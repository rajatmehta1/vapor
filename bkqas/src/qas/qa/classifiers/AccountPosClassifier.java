package qas.qa.classifiers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class AccountPosClassifier {

	public POSModel fetchPosMd() {
		POSModel md = new POSModelLoader().load(new File("src/resources/en-pos-maxent.bin"));
		return md;
	}
	
	public POSTaggerME fetchPosMe(POSModel sm_) {
		return new POSTaggerME(sm_);
	}
	
	public String[] fetchPos(String[] senArr) {
		POSTaggerME sme = this.fetchPosMe(this.fetchPosMd());
		String tags[] = sme.tag(senArr);
		return tags;
	}
	
	public void fetchPos(String sen) throws IOException {
		ObjectStream<String> lineStream = new PlainTextByLineStream(new StringReader(sen));
		POSTaggerME tagger = new POSTaggerME(this.fetchPosMd());
		String line;
		while ((line = lineStream.read()) != null) {
	 
			String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
			String[] tags = tagger.tag(whitespaceTokenizerLine);
	 
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
	 
		}
	}
	
	public static void main(String[] args) {
		//String paragraph = "How much money do i have in my account.";
		String paragraph = "What is the current balance of my account.";
		AccountSenClassifier sc = new AccountSenClassifier();	
		AccountPosClassifier asc = new AccountPosClassifier();
			String[] sarr = sc.fetchSen(paragraph);
			for (String sr : sarr) {
				System.out.println(sr);
			}
		
		 try {
			asc.fetchPos(paragraph);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}

}
