/*@author 손일형 
 * 
 * 클래스 SubServiceDTO : 출력할 데이터의 형식에 대한 정보를 가지고있는 클래스
 */

package com.app.service_info.myapp;

public class SubServiceDTO {

	private String service_name;
	private String tier_name;
	private String info;
	private int fee;
	private int num_people;

	public SubServiceDTO(String service_name, String tier_name, String info, int fee, int num_people) {
		super();
		this.service_name = service_name;
		this.tier_name = tier_name;
		this.info = info;
		this.fee = fee;
		this.num_people = num_people;
	}

	public SubServiceDTO() {
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getTier_name() {
		return tier_name;
	}

	public void setTier_name(String tier_name) {
		this.tier_name = tier_name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
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
	public String toString() {
		return "SubServiceVO [service_name=" + service_name + ", tier_name=" + tier_name + ", info=" + info + ", fee="
				+ fee + ", num_people=" + num_people + "]";
	}

}
