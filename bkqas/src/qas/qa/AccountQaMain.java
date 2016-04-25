package qas.qa;

import qas.qa.helpers.AccountQaHelper;
import qas.qa.vo.ResultVO;

public class AccountQaMain {

	public AccountQaMain() {
		
	}
	
	public ResultVO handleMesg(String qtxt_) {
		AccountQaHelper qaHelper = new AccountQaHelper();
		return qaHelper.handleMessage(qtxt_);
	}
}
