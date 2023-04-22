/**
 * 
 * @author 정재웅
 *
 */
package com.app.service_add;

public class project_DTO 
{
//=======================================================변수 생성==================================================================================//	
	private String pay_service_name;
	private String pay_tier_name;
	private String pay_info;
	private int pay_fee;
	private int pay_num_people;
	private String sv_service_name;
	private String sv_category;
	private String pay_URL;
	private String pay_icon_URL;

//========================================================SUBSCRIBE_SERVICE, PAYMENT_SYSTEM INSERT INSERT 초기화=================================================================================//		
	public project_DTO(String pay_service_name, String pay_tier_name, String pay_info, int pay_fee,
			int pay_num_people, String sv_service_name, String sv_category, String pay_URL, String pay_icon_URL) {
		super();
		this.pay_service_name = pay_service_name;
		this.pay_tier_name = pay_tier_name;
		this.pay_info = pay_info;
		this.pay_fee = pay_fee;
		this.pay_num_people = pay_num_people;
		this.sv_service_name = sv_service_name;
		this.sv_category = sv_category;
		this.pay_URL = pay_URL;
		this.pay_icon_URL = pay_icon_URL;
	}
//=========================================================PAYMENT_SYSTEM INSERT 초기화================================================================================//		
	public project_DTO(String pay_service_name, String pay_tier_name, String pay_info, int pay_fee, int pay_num_people) {
		super();
		this.pay_service_name = pay_service_name;
		this.pay_tier_name = pay_tier_name;
		this.pay_info = pay_info;
		this.pay_fee = pay_fee;
		this.pay_num_people = pay_num_people;
		}
//=======================================================sv_category==================================================================================//		
		public project_DTO(String sv_category) {
			super();
			this.sv_category = sv_category;
			}
//========================================================pay_service_name=================================================================================//	
	public String getPay_service_name() {
		return pay_service_name;
	}
	public void setPay_service_name(String pay_service_name) {
		this.pay_service_name = pay_service_name;
	}
//=======================================================pay_tier_name==================================================================================//	
	public String getPay_tier_name() {
		return pay_tier_name;
	}
	public void setPay_tier_name(String pay_tier_name) {
		this.pay_tier_name = pay_tier_name;
	}
//======================================================pay_info===================================================================================//	
	public String getPay_info() {
		return pay_info;
	}
	public void setPay_info(String pay_info) {
		this.pay_info = pay_info;
	}
//=======================================================pay_fee==================================================================================//	
	public int getPay_fee() {
		return pay_fee;
	}
	public void setPay_fee(int pay_fee) {
		this.pay_fee = pay_fee;
	}
//=======================================================pay_num_people==================================================================================//	
	public int getPay_num_people() {
		return pay_num_people;
	}
	public void setPay_num_people(int pay_num_people) {
		this.pay_num_people = pay_num_people;
	}
//========================================================sv_service_name=================================================================================//	
	public String getSv_service_name() {
		return sv_service_name;
	}
	public void setSv_service_name(String sv_service_name) {
		this.sv_service_name = sv_service_name;
	}
//=========================================================sv_category================================================================================//	
	public String getSv_category() {
		return sv_category;
	}
	public void setSv_category(String sv_category) {
		this.sv_category = sv_category;
	}
//==========================================================pay_URL===============================================================================//	
	public String getPay_URL() {
		return pay_URL;
	}
	public void setPay_URL(String pay_URL) {
		this.pay_URL = pay_URL;
	}
//=========================================================pay_icon_URL================================================================================//	
	public String getPay_icon_URL() {
		return pay_icon_URL;
	}
	public void setPay_icon_URL(String pay_icon_URL) {
		this.pay_icon_URL = pay_icon_URL;
	}
//=========================================================================================================================================//		

	

}
