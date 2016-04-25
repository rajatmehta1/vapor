package sb.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import sb.dao.UserDao;
import sb.vo.UserVO;



public class UserDaoImpl implements UserDao {

			private JdbcTemplate jdbcTemplate;
			//private NamedParameterJdbcTemplate namedJdbcTemplate;

		    public void setDataSource(DataSource dataSource) {
		        this.jdbcTemplate = new JdbcTemplate(dataSource);		        
		    }

                

                @Override
                public UserVO authenticate(String userName, String pwd) {
                    Object[] args = new Object[2];

                                                     args[0] = userName;

                                                     args[1] = pwd;

                                List<UserVO> users = this.jdbcTemplate.query(

                                                                                        "select * from kk_users where user_name = ? and pwd = ?",args,new UserMapper());

                    if(null == users || users.size() == 0) return null;

                    else {
                    	UserVO userVO = users.get(0);
                    		return userVO;
                    }

                }

                private static final class UserMapper implements RowMapper<UserVO> {



                    public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {

                                UserVO user = new UserVO();

                                       user.setUserId(rs.getInt("user_id"));

                                       user.setEmail(rs.getString("email"));

                                       if(null != rs.getString("user_name")) {
                                    	   user.setUserName(rs.getString("user_name"));
                                       } else {
                                    	   user.setUserName(rs.getString("email"));
                                       }

                                       user.setJoinedOn(rs.getDate("update_time"));
                                       user.setActive(true);
                                    return user;

                    }        

                }


                
                public UserVO getPerson(Integer userId) {

                    Object[] args = new Object[1];

                                                 args[0] = userId;



                    List<UserVO> users = this.jdbcTemplate.query("select * from users where user_id = ?",args,new UserMapper());

                    if(null == users || users.size() == 0) return null;
                    else {
                                   return users.get(0);
                    }

                }

                				
}
