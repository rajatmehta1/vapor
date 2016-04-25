package qas.qa.vo;

public class ResultVO {
   private String answerType;
   private String error;
   private String answer;
   private boolean isAnswer;
   private String param;
   
   public ResultVO() {
	   
   }

   public ResultVO(String ansType, String answer, boolean isAns, String error) {
	   this.answerType = ansType;
	   this.answer = answer;
	   this.isAnswer = isAns;
	   this.error = error;
   }
   
	public String getAnswerType() {
		return answerType;
	}
	
	public void setAnswerType(String answerType) {
		this.answerType = answerType;
	}
	
	public String getError() {
		return error;
	}
	
	public void setError(String error) {
		this.error = error;
	}
	
	public String getAnswer() {
		return answer;
	}
	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public boolean getIsAnswer() {
		return isAnswer;
	}
	
	public void setIsAnswer(boolean isAnswer) {
		this.isAnswer = isAnswer;
	}
   
   
   
}
