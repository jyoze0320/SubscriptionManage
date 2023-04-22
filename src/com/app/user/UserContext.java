package com.app.user;

/**
 * 유저 정보가 담길 클래스<br/>
 * 싱글톤으로 관리한다.
 * @author 임다솜
 *
 */
public class UserContext {
	private static UserContext context;
	
	private String userId;
	private String userRole;
	
	private UserContext() {}
	
	public static UserContext getInstance() {
		if(context == null) context = new UserContext();
		return context;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	
	
	
	
}
