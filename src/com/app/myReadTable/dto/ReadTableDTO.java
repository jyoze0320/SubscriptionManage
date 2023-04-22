package com.app.myReadTable.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class ReadTableDTO {
	private SimpleStringProperty service;
	private SimpleStringProperty serviceName;
	private SimpleIntegerProperty cash;
	private SimpleStringProperty information;
	private SimpleIntegerProperty share;// Private: 외부에서 접근할 수 없는 변수
	private int subscringId;

	public ReadTableDTO(String service, String serviceName, int cash, String information, int share) {
		super();
		this.service = new SimpleStringProperty(service);
		this.serviceName = new SimpleStringProperty(serviceName);
		this.cash = new SimpleIntegerProperty(cash/share);
		this.information = new SimpleStringProperty(information);
		this.share = new SimpleIntegerProperty(share);					/*this: 자기자신을 의미하는 키워드
																		this.은 필드와 매개변수가 동일할때 주로 사용되며 인스턴스의 멤버임을 명확하게 해준다.
																		this()는 자기 자신의 생성자를 호출할때 사용하며 호출하는 곳의 첫번째 문장에 작성해야한다.*/
	}
	public ReadTableDTO(String service, String serviceName, int cash, String information, int share,int subscringId) {
		this(service, serviceName, cash, information, share);
		this.subscringId = subscringId;
	}

	public String getService() {
		return service.get();
	}

	public void setService(String service) {
		this.service.set(service);
		;
	}

	public String getServiceName() {
		return serviceName.get();
	}

	public void setServiceName(String serviceName) {
		this.serviceName.set(serviceName);
		;
	}

	public int getCash() {
		return cash.get();
	}

	public void setCash(int cash) {
		this.cash.set(cash);
	}

	public String getInformation() {
		return information.get();
	}

	public void setInformation(String information) {
		this.information.set(information);
	}

	public int getShare() {
		return share.get();
	}

	public void setShare(int share) {
		this.share.set(share);
	}

	public int getSubscringId() {
		return this.subscringId;
	}
	
}

//코드:임다솜 주석:노설아
