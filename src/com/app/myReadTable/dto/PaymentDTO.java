package com.app.myReadTable.dto;

public class PaymentDTO {

	private int number;
	private String service_name;
	private String tire_name;
	private int fee;
	private int num_people;							// Private: 외부에서 접근할 수 없는 변수
	private String info;
	public PaymentDTO(int number, String service_name, String tire_name, int fee, int num_people) {
		super();
		this.number = number;
		this.service_name = service_name;
		this.tire_name = tire_name;
		this.fee = fee;
		this.num_people = num_people;				/*this: 자기자신을 의미하는 키워드
														this.은 필드와 매개변수가 동일할때 주로 사용되며 인스턴스의 멤버임을 명확하게 해준다.
														this()는 자기 자신의 생성자를 호출할때 사용하며 호출하는 곳의 첫번째 문장에 작성해야한다.*/
	}

	
	public PaymentDTO(int number, String service_name, String tire_name, int fee, int num_people, String info) {
		super();
		this.number = number;
		this.service_name = service_name;
		this.tire_name = tire_name;
		this.fee = fee;
		this.num_people = num_people;
		this.info = info;
	}


	public int getNumber() {
		return number;								
													/* get메소드는 인스턴스나 인스턴스나 클래스 변수의 값을 가져오는 메소드접근자이다. 
													 * 속성은 함수처럼 생겼지만 해당 객체의 값을 얻고(get),설정하는 역할(set)을 수행한다*/
	}												

	public void setNumber(int number) {				
		this.number = number;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getTire_name() {
		return tire_name;
	}

	public void setTire_name(String tire_name) {
		this.tire_name = tire_name;
	}

	public int getFee() {
		return fee;
	}

	public void setFee(int fee) {
		this.fee = fee;
	}

	public int getNum_people() {
		return num_people;
	}

	public void setNum_people(int num_people) {
		this.num_people = num_people;
	}

	@Override
	public String toString() { 						// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "PaymentDTO [number=" + number + ", service_name=" + service_name + ", tire_name=" + tire_name + ", fee="
				+ fee + ", num_people=" + num_people + "]";
	}


	public String getInfo() {
		return info;
	}


	public void setInfo(String info) {
		this.info = info;
	}

}
//코드:임다솜 주석:노설아