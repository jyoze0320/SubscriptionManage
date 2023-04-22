package com.app.myReadTable.dto;

public class SubscribeDTO {

	private String service_name;
	private String service_info;
	private String service_url;
	private String service_icon_url;											// Private: 외부에서 접근할 수 없는 변수

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_info() {
		return service_info;
	}

	public void setService_info(String service_info) {
		this.service_info = service_info;
	}

	public String getService_url() {
		return service_url;
	}

	public void setService_url(String service_url) {
		this.service_url = service_url;
	}

	public String getService_icon_url() {
		return service_icon_url;
	}

	public void setService_icon_url(String service_icon_url) {
		this.service_icon_url = service_icon_url;
	}

	public SubscribeDTO(String service_name, String service_info, String service_url, String service_icon_url) {	
		super();
		this.service_name = service_name;
		this.service_info = service_info;
		this.service_url = service_url;
		this.service_icon_url = service_icon_url;										/*this: 자기자신을 의미하는 키워드
																							this.은 필드와 매개변수가 동일할때 주로 사용되며 인스턴스의 멤버임을 명확하게 해준다.
																							this()는 자기 자신의 생성자를 호출할때 사용하며 호출하는 곳의 첫번째 문장에 작성해야한다.*/
	}

	@Override
	public String toString() { 															// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "SubscribeDTO [service_name=" + service_name + ", service_info=" + service_info + ", service_url="
				+ service_url + ", service_icon_url=" + service_icon_url + "]";
	}

}

//코드:임다솜 주석:노설아