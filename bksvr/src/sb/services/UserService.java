package sb.services;



import java.util.List;
import sb.util.exceptions.UserException;
import sb.vo.UserVO;

public interface UserService {

				public UserVO authenticate(String userId,String pwd) throws UserException;
                public UserVO getPerson(Integer userId);
				
}

