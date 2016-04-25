package sb.vo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UserVO {

    private Integer userId;

    private String userName;

    private String pwd;

    private String email;

    private boolean isActive;
    
    private Date joinedOn;
    
    
    public UserVO() {

    }


	public Integer getUserId() {
		return userId;
	}


	public void setUserId(Integer userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isActive() {
		return isActive;
	}


	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	public Date getJoinedOn() {
		return joinedOn;
	}


	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}



}
