package sb.services.impl;

import java.util.List;
import org.w3c.dom.UserDataHandler;
import sb.dao.UserDao;
import sb.dao.impl.UserDaoImpl;
import sb.services.UserService;
import sb.util.exceptions.UserException;
import sb.vo.UserVO;


public class UserServiceImpl implements UserService {

                private UserDao userDao;



                @Override

                public UserVO authenticate(String userId, String pwd) throws UserException {

                                return userDao.authenticate(userId, pwd);

                }

                @Override
                public UserVO getPerson(Integer userId) {
                                return userDao.getPerson(userId);
                }

				public UserDao getUserDao() {
					return userDao;
				}

				public void setUserDao(UserDao userDao) {
					this.userDao = userDao;
				}

                
}

