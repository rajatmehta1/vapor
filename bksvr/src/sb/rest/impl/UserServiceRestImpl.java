package sb.rest.impl;



import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import qas.qa.AccountQaMain;
import qas.qa.vo.ResultVO;
import sb.helper.BkServiceHelper;
import sb.rest.UserServiceRest;
import sb.services.UserService;
import sb.util.exceptions.UserException;
import sb.vo.UserVO;



/*

* Restful implementation of the User Service

* Essential a Spring Controller

* GET , PUT , DELETE , POST

*/

@Controller

public class UserServiceRestImpl implements UserServiceRest {



                @Autowired
                private UserService userService;



                public UserService getUserService() {
                                return userService;
                }



                public void setUserService(UserService _userService) {
                                this.userService = userService;
                }


                @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET)
                @Override          
                public @ResponseBody UserVO getPerson(@PathVariable(value="userId") Integer userId) {
                                return userService.getPerson(userId);
                }

                @RequestMapping(value = "/bkservice",method = RequestMethod.GET)
                @Override          
                public @ResponseBody String getBkServiceResponse(@RequestParam(value="m")String mesg_) {
                        AccountQaMain qaMain = new AccountQaMain();
                        ResultVO rvo = qaMain.handleMesg(mesg_);
                        BkServiceHelper bkh = new BkServiceHelper();
                          return bkh.handleAnswer(rvo.getAnswerType());
                        //return rvo.getAnswerType();
                        // return mesg_;
                }
}

