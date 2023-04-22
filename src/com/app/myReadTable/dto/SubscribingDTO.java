package com.app.myReadTable.dto;

public class SubscribingDTO {

	private int number;
	private String user_id;
	private int paysys_id;
	private int num_of_share;														// Private: 외부에서 접근할 수 없는 변수

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getPaysys_id() {
		return paysys_id;
	}

	public void setPaysys_id(int paysys_id) {
		this.paysys_id = paysys_id;
	}

	public int getNum_of_share() {
		return num_of_share;
	}

	public void setNum_of_share(int num_of_share) {
		this.num_of_share = num_of_share;
	}

	public SubscribingDTO(int number, String user_id, int paysys_id, int num_of_share) {
		super();
		this.number = number;
		this.user_id = user_id;
		this.paysys_id = paysys_id;
		this.num_of_share = num_of_share;											/*this: 자기자신을 의미하는 키워드
																						this.은 필드와 매개변수가 동일할때 주로 사용되며 인스턴스의 멤버임을 명확하게 해준다.
																						this()는 자기 자신의 생성자를 호출할때 사용하며 호출하는 곳의 첫번째 문장에 작성해야한다.*/
	}

	@Override
	public String toString() { 														// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "SubscribingDTO [number=" + number + ", user_id=" + user_id + ", paysys_id=" + paysys_id
				+ ", num_of_share=" + num_of_share + "]";
	}

}

//코드:임다솜 주석:노설아