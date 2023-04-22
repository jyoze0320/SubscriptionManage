package com.app.myReadTable.dto;

public class ServiceDTO {

	private String user_id;
	private String user_pw;
	private String user_name;
	private String user_email;									// Private: 외부에서 접근할 수 없는 변수

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

	public ServiceDTO(String user_id, String user_pw, String user_name, String user_email) {
		super();
		this.user_id = user_id;
		this.user_pw = user_pw;
		this.user_name = user_name;
		this.user_email = user_email;							/*this: 자기자신을 의미하는 키워드
																	this.은 필드와 매개변수가 동일할때 주로 사용되며 인스턴스의 멤버임을 명확하게 해준다.
																	this()는 자기 자신의 생성자를 호출할때 사용하며 호출하는 곳의 첫번째 문장에 작성해야한다.*/

	}

	@Override
	public String toString() { 									// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "ServiceDTO [user_id=" + user_id + ", user_pw=" + user_pw + ", user_name=" + user_name + ", user_email="
				+ user_email + "]";								// ??
	}

}
//코드:임다솜 주석:노설아