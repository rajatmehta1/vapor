package sb.rest;

import sb.util.exceptions.UserException;
import sb.vo.UserVO;


public interface UserServiceRest {

                public UserVO getPerson(Integer userId);

                public String getBkServiceResponse(String m);
}

