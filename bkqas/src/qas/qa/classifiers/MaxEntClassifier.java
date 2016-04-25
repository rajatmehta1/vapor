package qas.qa.classifiers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerEvaluator;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSample;
import opennlp.tools.util.InvalidFormatException;

public class MaxEntClassifier {

	public void classify(String category,String text) throws InvalidFormatException, IOException{
		String classModelPath = "C:\\harpreet\\workspace\\bkqas\\src\\resources\\max-ent.bin";
		InputStream is = new FileInputStream(classModelPath);
		DoccatModel dModel = new DoccatModel(is);
		DocumentCategorizerME dme = new DocumentCategorizerME(dModel);
		DocumentCategorizerEvaluator dv = new DocumentCategorizerEvaluator(dme);
	
		String expDocCat = category;
		String docContent = text;
		DocumentSample dsample = new DocumentSample(expDocCat, docContent);
			double[] classDist = dme.categorize(docContent);
			String predictedCat = dme.getBestCategory(classDist);
			dv.evaluateSample(dsample);
			double result = dv.getAccuracy();
			
			System.out.println("Model Prediction : " + predictedCat);
			System.out.println("Accuracy : " + result);
	}
	
	
	public String classifyAndPredict(String category,String text) throws InvalidFormatException, IOException{
		String classModelPath = "C:\\harpreet\\workspace\\bkqas\\src\\resources\\max-ent.bin";
		InputStream is = new FileInputStream(classModelPath);
		DoccatModel dModel = new DoccatModel(is);
		DocumentCategorizerME dme = new DocumentCategorizerME(dModel);
		DocumentCategorizerEvaluator dv = new DocumentCategorizerEvaluator(dme);
	
		String expDocCat = category;
		String docContent = text;
		DocumentSample dsample = new DocumentSample(expDocCat, docContent);
			double[] classDist = dme.categorize(docContent);
			String predictedCat = dme.getBestCategory(classDist);
			dv.evaluateSample(dsample);
			double result = dv.getAccuracy();
			
			System.out.println("Model Prediction : " + predictedCat);
			System.out.println("Accuracy : " + result);
			
			return predictedCat;
	}
	
	public static void main(String[] args) throws InvalidFormatException, IOException {
		String cat = "MA";
		String content = "my last transactions";
		new MaxEntClassifier().classify(cat, content);
	}
}
