package qas.qa.model;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;

import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.NameSample;
import opennlp.tools.namefind.NameSampleDataStream;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import qas.qa.utils.BkUtil;

public class GenericModelTrainer {

                public String train(String lang, String entity,
                		InputStream taggedCorpusStream, OutputStream modelStream) {
                      Charset charset = Charset.forName("UTF-8");
            	try {
            		ObjectStream<String> lineStream = new PlainTextByLineStream(
            				taggedCorpusStream, charset);
            		
            		ObjectStream<NameSample> sampleStream = new NameSampleDataStream(
            				lineStream);
            		TokenNameFinderModel model;
            		OutputStream modelOut = null;
            		try {
            			model = NameFinderME.train(lang, entity, sampleStream, null);
            			modelOut = new BufferedOutputStream(modelStream);
            			if (model != null) {
            				model.serialize(modelOut);
            			}
            			return entity + " model trained successfully";
            		} catch (Exception ex) {
            			ex.printStackTrace();
            		} finally {
            			sampleStream.close();
            			if (modelOut != null) {
            				modelOut.close();
            			}
            		}
            	} catch (Exception e) {
            		e.printStackTrace();
            	}
            	return "Something goes wrong with training module.";
            }
                
                public String train(String lang, String entity, String taggedCoprusFile,
                		String modelFile) {
                	try {
                		return train(lang, entity, new FileInputStream(taggedCoprusFile),
                			new FileOutputStream(modelFile));
                	} catch (Exception e) {
                		e.printStackTrace();
                	}
                	return "Something goes wrong with training module.";
                }                
                public static void main(String[] args) throws IOException {
                	GenericModelTrainer amt = new GenericModelTrainer();
                	  amt.train("en", "acctdet", "src/resources/data/dataset.txt", "src/resources/en-ner-acctdet.bin");
                }
}
