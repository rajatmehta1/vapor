package sb.dao;

import java.util.List;
import sb.vo.UserVO;

public interface UserDao {
                public UserVO authenticate(String userIdOrEmail,String pwd);
                public UserVO getPerson(Integer userId);                           
}
