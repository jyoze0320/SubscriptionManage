package com.app.user;

import org.mindrot.jbcrypt.BCrypt;
/**
 * 
 * UserDTO
 * DB에 저장된 유저 데이터를 전달하기 위한 DTO 클래스
 * 
 * @author 임다솜
 *
 */
public class UserDTO {
	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_email;
	private String user_role;
	
	public UserDTO() {}

	public UserDTO(String user_id, String user_pw, String user_name, String user_email,String user_role) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_email = user_email;
		this.user_role = user_role;
	}
	
	public UserDTO(String user_id, String user_pw, String user_name, String user_email) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_email = user_email;
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_role() {
		return user_role;
	}

	public void setUser_role(String user_role) {
		this.user_role = user_role;
	}

	@Override
	public String toString() {
		return "UserDTO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_email="
				+ user_email + "]";
	}
	
	/**
	 * 패스워드를 해싱하여 저장
	 */
	public void passwordHashing() {
		this.user_pw = BCrypt.hashpw(this.user_pw, BCrypt.gensalt());
	}

	/**
	 * 패스워드 체크
	 * @param inputPw 평문
	 * @return 저장된 해시값이 입력된 평문으로 만들어진 해시값이라면 true 아니면 false
	 */
	public boolean matchPassword(String inputPw) {
		return BCrypt.checkpw(inputPw, this.user_pw);
	}
	
}
