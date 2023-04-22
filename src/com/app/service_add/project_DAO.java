/**
 * 
 * @author 정재웅
 *
 */
package com.app.service_add;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.util.DBConnection;

public class project_DAO //project_DAO class 생성
{	
	private Connection conn = DBConnection.getConnection();//Connection 생성
//=======================================================SUBSCRIBE_SERVICE, PAYMENT_SYSTEM INSERT DAO PART==================================================================================//	
	public void Service_Insert(project_DTO newAccount) {
		String SERVICE_sql = "insert into SUBSCRIBE_SERVICE (service_name, service_category, service_URL, service_icon_URL) VALUES (?, ?, ?,?)";//DB SUBSCRIBE_SERVICE INSERT 부분
		String PAYMENT_sql = "insert into PAYMENT_SYSTEM (service_name, tier_name, info, fee, num_people) VALUES (?,?,?,?,?) ";//DB PAYMENT_SYSTEM INSERT 부분
//--------------------------------------------------------DTO 값을 DB에 실제로 저장-------------------------------------------------------------------------------------------------//	
		try {
			PreparedStatement SERVICE_preparedStatement = conn.prepareStatement(SERVICE_sql);//SUBSCRIBE_SERVICE INSERT 부분
			SERVICE_preparedStatement.setString(1, newAccount.getSv_service_name());//service_name에 들어갈 DTO getSv_service_name()
			SERVICE_preparedStatement.setString(2, newAccount.getSv_category());//service_category에 들어갈 DTO getSv_category()
			SERVICE_preparedStatement.setString(3, newAccount.getPay_URL());//service_URL에 들어갈 DTO getPay_URL()
			SERVICE_preparedStatement.setString(4, newAccount.getPay_icon_URL());//service_icon_URL에 들어갈 DTO getPay_icon_URL()
			SERVICE_preparedStatement.executeUpdate();//Primary Key SUBSCRIBE_SERVICE 업데이트
//---------------------------------------------------------------------------------------------------------------------------------------------------------//					
			PreparedStatement PAYMENT_preparedStatement = conn.prepareStatement(PAYMENT_sql);//PAYMENT_SYSTEM INSERT 부분
			PAYMENT_preparedStatement.setString(1, newAccount.getPay_service_name());//service_name에 들어갈 DTO getPay_service_name()
			PAYMENT_preparedStatement.setString(2, newAccount.getPay_tier_name());//tier_name에 들어갈 DTOgetPay_tier_name()
			PAYMENT_preparedStatement.setString(3, newAccount.getPay_info());//info에 들어갈 DTOgetPay_info()
			PAYMENT_preparedStatement.setInt(4, newAccount.getPay_fee());//fee에 들어갈 DTOgetPay_fee()
			PAYMENT_preparedStatement.setInt(5, newAccount.getPay_num_people());//num_people에 들어갈 DTOgetPay_num_people()
			PAYMENT_preparedStatement.executeUpdate();//Foreign Key PAYMENT_SYSTEM 업데이트
//---------------------------------------------------------------------------------------------------------------------------------------------------------//
			/*업데이트가 성공 했는지 프린트로 확인*/
			System.out.println("project service 입력 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}//end try
	}//end Service_Insert	
	
//=======================================================PAYMENT_SYSTEM INSERT DAO PART==================================================================================//	
	public void Payment_Insert(project_DTO newAccount) {
		String PAYMENT_sql = "insert into PAYMENT_SYSTEM (service_name, tier_name, info, fee, num_people) VALUES (?,?,?,?,?) ";//DB PAYMENT_SYSTEM INSERT 부분
//---------------------------------------------------------------------------------------------------------------------------------------------------------//		
		try {				
			PreparedStatement PAYMENT_preparedStatement = conn.prepareStatement(PAYMENT_sql);//PAYMENT_SYSTEM INSERT 부분
			PAYMENT_preparedStatement.setString(1, newAccount.getPay_service_name());//service_name에 들어갈 DTO getPay_service_name()
			PAYMENT_preparedStatement.setString(2, newAccount.getPay_tier_name());//tier_name에 들어갈 DTOgetPay_tier_name()
			PAYMENT_preparedStatement.setString(3, newAccount.getPay_info());//info에 들어갈 DTOgetPay_info()
			PAYMENT_preparedStatement.setInt(4, newAccount.getPay_fee());//fee에 들어갈 DTOgetPay_fee()
			PAYMENT_preparedStatement.setInt(5, newAccount.getPay_num_people());//num_people에 들어갈 DTOgetPay_num_people()
			PAYMENT_preparedStatement.executeUpdate();//Foreign Key PAYMENT_SYSTEM 업데이트
//---------------------------------------------------------------------------------------------------------------------------------------------------------//	
			/*업데이트가 성공 했는지 프린트로 확인*/
			System.out.println("project service 입력 성공");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}//end try
	}//end Service_Insert
//=====================================================SUBSCRIBE_SERVICE->service_name 중복 확인====================================================================================//	
	public boolean findByService (String serviceName)
	{
		String query = "select count(*) as count from SUBSCRIBE_SERVICE where service_name = ?";//select SQL문
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);//service_name
			pstmt.setString(1, serviceName);//service_name
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			if(rs.getInt("count") > 0)// 중복된 값이 있을 경우 true
			{
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;// 중복된 값이 없을 경우 false
	}
//======================================================PAYMENT_SYSTEM->tier_name 중복 확인===================================================================================//		
	public boolean findByTier (String serviceName, String tier)
	{
		String query = "select count(*) as count from PAYMENT_SYSTEM where service_name = ? and tier_name = ?";//select SQL문
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);//service_name, tier_name select 부분
			pstmt.setString(1, serviceName);//service_name
			pstmt.setString(2, tier);//tier_name
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if(rs.getInt("count") > 0)// 중복된 값이 있을 경우 true
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return false;// 중복된 값이 없을 경우 false
	}
//======================================================카타고리 리스트 배열===================================================================================//		
	public List<String> categoryStringList() //String 형 LIST 
	{
		String query = "select distinct service_category as category from subscribe_service";//select SQL문
		List<String> categoryList = new ArrayList<>();//ArrayList 생성
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())// 값이 없을때 까지 반복
				categoryList.add(rs.getString("CATEGORY"));//배열에 저장
		}
		catch (SQLException e) {
			System.out.println("에러 발생!");
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return categoryList;
	}
}
