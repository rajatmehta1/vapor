package qas.qa.model;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

public class MaxEntTrainer {
   
	
	
	public void train() {
		String modelPath = "C:\\harpreet\\workspace\\bkqas\\src\\resources\\max-ent.bin";
		String trainDataFilePath = "C:\\harpreet\\workspace\\bkqas\\src\\resources\\fdata.txt";
		DoccatModel model = null;
		InputStream dataInputStream = null;
		OutputStream dataOutputStream = null;
		
			try {
				dataInputStream = new FileInputStream(trainDataFilePath);
				ObjectStream<String> lineStream = new PlainTextByLineStream(dataInputStream, "UTF-8");
				ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);
				model = DocumentCategorizerME.train("en", sampleStream);
			} catch(IOException e) {
				e.printStackTrace();
			} finally {
				if(dataInputStream != null) {
					try {
						dataInputStream.close();
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		
			try {
				dataOutputStream = new BufferedOutputStream(new FileOutputStream(modelPath));
				model.serialize(dataOutputStream);
			} catch(IOException e) {
			   e.printStackTrace();	
			} finally {
				try {
					dataOutputStream.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
	}
	
	
	public static void main(String[] args) {
		MaxEntTrainer met = new MaxEntTrainer();
			met.train();
	}
	
	
}
