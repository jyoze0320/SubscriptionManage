package com.app.myReadTable.dto;

public class CategoryDTO {

	private String service_name;
	private String service_category;											// Private: 외부에서 접근할 수 없는 변수

	public String getService_name() {											/* get메소드는 인스턴스나 인스턴스나 클래스 변수의 값을 가져오는 메소드접근자이다. 
		 																		* 속성은 함수처럼 생겼지만 해당 객체의 값을 얻고(get),설정하는 역할(set)을 수행한다*/
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getService_category() {
		return service_category;
	}

	public void setService_category(String service_category) {
		this.service_category = service_category;
	}

	public CategoryDTO(String service_name, String service_category) {
		super();
		this.service_name = service_name;
		this.service_category = service_category;
	}

	@Override
	public String toString() { 													// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "CategoryDTO [service_name=" + service_name + ", service_category=" + service_category + "]";
	}

}

//코드:임다솜 주석:노설아
