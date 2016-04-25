package qas.qa.helpers;

import java.io.IOException;

import qas.qa.classifiers.MaxEntClassifier;
import qas.qa.types.QuestionTypesMapper;
import qas.qa.utils.BkUtil;
import qas.qa.vo.ResultVO;

public class AccountQaHelper {

	public AccountQaHelper() {
		
	}
	
	public ResultVO handleMessage(String mesg_) {
		MaxEntClassifier me = null;
		String predictedCat = null;
		ResultVO resultVO = null;
		try {
				me = new MaxEntClassifier();
				predictedCat = me.classifyAndPredict(BkUtil.DEF_CAT, mesg_);
				resultVO = classifyAnswerTypeAndGetResult(predictedCat);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		return resultVO;
	}
	
	public ResultVO classifyAnswerTypeAndGetResult(String predictedCat) {		
		return buildResultVO(predictedCat);
	}
	
	public ResultVO buildResultVO(String predictedCat) {
		String answerType = QuestionTypesMapper.questionTypesMap.get(predictedCat);
		ResultVO rvo = new ResultVO();
		if("MA".equals(predictedCat) || "MO".equals(predictedCat)) {
			rvo.setIsAnswer(false);
		}
		else if("MSG".equals(predictedCat) || "MSS".equals(predictedCat)) {
			rvo.setIsAnswer(false);			
		}
		rvo.setAnswerType(answerType);
		return rvo;
	}
}
