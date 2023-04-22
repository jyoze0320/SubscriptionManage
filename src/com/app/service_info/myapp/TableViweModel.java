/* @author 손일형 
 * 
 * 클래스 TableViweModel :테이블뷰에 출력하고싶은 필드를 보관하는 클래스. 모든 필드에 대한 get/set메소드가 포함됨.
 * 
 */

package com.app.service_info.myapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableViweModel {
	private SimpleStringProperty serviceName;
	private SimpleStringProperty fee_name;
	private SimpleStringProperty info;
	private SimpleIntegerProperty fee;
	private SimpleIntegerProperty share;

	//데이터를 초기화하는 생성자
	public TableViweModel(String serviceName, String fee_name, String info, int fee, int share) {
		super();
		this.serviceName = new SimpleStringProperty(serviceName);
		this.fee_name = new SimpleStringProperty(fee_name);
		this.info = new SimpleStringProperty(info);
		this.fee = new SimpleIntegerProperty(fee);
		this.share = new SimpleIntegerProperty(share);
	}

	public String getServiceName() { return serviceName.get(); }
	public void setServiceName(String serviceName) { this.serviceName = new SimpleStringProperty(serviceName); }

	public String getFee_name() { return fee_name.get(); }
	public void setFee_name(String fee_name) { this.fee_name = new SimpleStringProperty(fee_name); }

	public String getInfo() { return info.get(); }
	public void setInfo(String info) { this.info = new SimpleStringProperty(info); }

	public int getFee() { return fee.get(); }
	public void setFee(int fee) { this.fee = new SimpleIntegerProperty(fee); }

	public int getShare() { return share.get(); }
	public void setShare(int share) { this.share = new SimpleIntegerProperty(share);}

	
	//======================================================================================
	
	private int id;
	
	/**
	 * @param serviceName 서비스명
	 * @param fee_name	요금제명
	 * @param info	세부정보
	 * @param fee	요금
	 * @param share	최대 공유 인원
	 * @param id	요금제 id
	 * 
	 * @author 임다솜
	 */
	public TableViweModel(String serviceName, String fee_name, String info, int fee, int share,int id) {
		this(serviceName, fee_name, info, fee, share);
		this.id =id;
	}
	public int getId() {
		return this.id;
	}
}
