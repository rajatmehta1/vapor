package qas.qa.parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import opennlp.tools.chunker.ChunkerME;
import opennlp.tools.chunker.ChunkerModel;
import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.InvalidFormatException;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.Span;

public class TreeBankParser {
	public static void main(String[] args) throws Exception {
		//Parse();
		//printNounPhrase();
		chunk();
	}
	public static void Parse() throws InvalidFormatException, IOException {
		// http://sourceforge.net/apps/mediawiki/opennlp/index.php?title=Parser#Training_Tool
		InputStream is = new FileInputStream("src/resources/en-parser-chunking.bin");
	 
		ParserModel model = new ParserModel(is);
	 
		Parser parser = ParserFactory.create(model);
	 
		String sentence = "What is the amount in my savings account";
		Parse topParses[] = ParserTool.parseLine(sentence, parser, 1);
	 
		for (Parse p : topParses)
			p.show();
	 
		is.close();
	 
		/*
		 * (TOP (S (NP (NN Programcreek) ) (VP (VBZ is) (NP (DT a) (ADJP (RB
		 * very) (JJ huge) (CC and) (JJ useful) ) ) ) (. website.) ) )
		 */
	}
	
	public static void chunk() throws IOException {
		POSModel model = new POSModelLoader()
				.load(new File("src/resources/en-pos-maxent.bin"));
		PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
		POSTaggerME tagger = new POSTaggerME(model);
	 
		String input = "how much did i spend on starbucks this month.";
		ObjectStream<String> lineStream = new PlainTextByLineStream(
				new StringReader(input));
	 
		perfMon.start();
		String line;
		String whitespaceTokenizerLine[] = null;
	 
		String[] tags = null;
		while ((line = lineStream.read()) != null) {
			whitespaceTokenizerLine = WhitespaceTokenizer.INSTANCE
					.tokenize(line);
			tags = tagger.tag(whitespaceTokenizerLine);
	 
			POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
			System.out.println(sample.toString());
				perfMon.incrementCounter();
		}
		perfMon.stopAndPrintFinalResult();
	 
		// chunker
		InputStream is = new FileInputStream("src/resources/en-chunker.bin");
		ChunkerModel cModel = new ChunkerModel(is);
	 
		ChunkerME chunkerME = new ChunkerME(cModel);
		String result[] = chunkerME.chunk(whitespaceTokenizerLine, tags);
	 
		for (String s : result)
			System.out.println(s);
	 
		Span[] span = chunkerME.chunkAsSpans(whitespaceTokenizerLine, tags);
		for (Span s : span)
			System.out.println(s.toString());
	}
	
	public static void printNounPhrase() throws Exception {
	    SentenceDetectorME sentenceDetector;
	    TokenizerME tokenizer;
	    POSTaggerME posTagger;
	    ChunkerME chunker;
	    InputStream smis = null;
	    InputStream tmis = null;
	    InputStream pmis = null;
	    InputStream cmis = null;
	    try {
	      smis = new FileInputStream(new File("C:\\harpreet\\workspace\\bkqas\\src\\resources\\en-sent.bin"));
	      tmis = new FileInputStream(new File("C:\\harpreet\\workspace\\bkqas\\src\\resources\\en-token.bin"));
	      pmis = new FileInputStream(new File("C:\\harpreet\\workspace\\bkqas\\src\\resources\\en-pos-maxent.bin"));
	      cmis = new FileInputStream(new File("C:\\harpreet\\workspace\\bkqas\\src\\resources\\en-chunker.bin"));
	      SentenceModel smodel = new SentenceModel(smis);
	      sentenceDetector = new SentenceDetectorME(smodel);
	      TokenizerModel tmodel = new TokenizerModel(tmis);
	      tokenizer = new TokenizerME(tmodel);
	      POSModel pmodel = new POSModel(pmis);
	      posTagger = new POSTaggerME(pmodel);
	      ChunkerModel cmodel = new ChunkerModel(cmis);
	      chunker = new ChunkerME(cmodel);
	    } finally {
	    }
	    String text = "What is the amount in my saving account";
	    Span[] sentSpans = sentenceDetector.sentPosDetect(text);
	    for (Span sentSpan : sentSpans) {
	      String sentence = sentSpan.getCoveredText(text).toString();
	      int start = sentSpan.getStart();
	      Span[] tokSpans = tokenizer.tokenizePos(sentence);
	      String[] tokens = new String[tokSpans.length];
	      for (int i = 0; i < tokens.length; i++) {
	        tokens[i] = tokSpans[i].getCoveredText(sentence).toString();
	      }
	      String[] tags = posTagger.tag(tokens);
	      Span[] chunks = chunker.chunkAsSpans(tokens, tags);
	      for (Span chunk : chunks) {
	        if ("NP".equals(chunk.getType())) {
	          int npstart = start + tokSpans[chunk.getStart()].getStart();
	          int npend = start + tokSpans[chunk.getEnd() - 1].getEnd();
	          System.out.println(text.substring(npstart, npend));
	        }
	      }
	    }
	  }
}
