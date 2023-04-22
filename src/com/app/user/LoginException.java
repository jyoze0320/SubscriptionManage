package com.app.user;

/**
 * 로그인 실패 Exception
 * 
 * @author 임다솜
 *
 */
public class LoginException extends RuntimeException{

	public LoginException() {
		super();
	}

	public LoginException(String message) {
		super(message);
	}

}
