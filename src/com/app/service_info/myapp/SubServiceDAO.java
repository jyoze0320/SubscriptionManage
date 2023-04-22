/* @author 손일형 
 * 
 * 클래스 SubServiceDAO : SQL에서 데이터를 불러 tableview에 출력하기 위한 클래스, 인터페이스를 상속받아 구현.
 * 특이사항 : 카테고리별 데이터 출력시 ? 파라메터 사용하여 컨트롤러에서 입력된 카테고리를 출력되도록 코드 작성
 *		    기존에는 카테고리별 select문을 사용하여 중복된 내용의 코드가 많았으나 이를 refactoring함 (해당 코드를 refactoring하는데 조장 임다솜님의 제안이 있었음)
 */


package com.app.service_info.myapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.util.DBConnection;

public class SubServiceDAO implements SubServiceDAOInerface<TableViweModel> {
	
	// DB연결
	private Connection conn = DBConnection.getConnection();


	// 전체 카테고리 출력 메소드
	public List<TableViweModel> dataList() {
		// SQL문 선언
		String sql = "select * from v_catelist";
		// ArrayList 사용
		List<TableViweModel> serviceVOs = new ArrayList<>();
		try {
			// PreparedStatement 사용하여 SQL 문을 데이터베이스로 보내기 위한 개체 생성
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			// executeQuery를 사용하여 SQL 쿼리를 실행하고 쿼리에 의해 생성된 PreparedStatement개체를 반환
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				//ResultSet : View에서 원하는 COLUNM을 불러옴
				serviceVOs.add(new TableViweModel(resultSet.getString("SERVICE_NAME"), resultSet.getString("TIER_NAME"), resultSet.getString("INFO"),
						resultSet.getInt("FEE"), resultSet.getInt("NUM_PEOPLE"),resultSet.getInt("id")));
			} // end while
			System.out.println();
		} catch (SQLException e) { // 예외처리
			System.out.println("에러 발생!");
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {	// 예외처리
			e.printStackTrace();
		} finally {
		}
		return serviceVOs;
	} // end dataList

	
	/* 
	 * 카테고리별 출력 메소드 : SQL WHERE절에서 ? 사용해 Controller에서 직접 변수를 입력하도록 작성.
	 * Refactoring 제안 : 임다솜
	 * 코드작성 : 손일형 
	 */
	//selectCategory 메소드는 문자형을 반환받음
	public List<TableViweModel> selectCategory(String insertcategory) {
		//SQL문 선언 : ? 파라메터를 사용해서 컨트롤러를 통해 카테고리를 받아오도록 함
		String sql = "select * from v_catelist WHERE SERVICE_CATEGORY = ?";	
		// ArrayList 사용
		List<TableViweModel> serviceVOs = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			// ? 의 자리에 inerstcategory를 받아옴
			preparedStatement.setString(1, insertcategory);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				serviceVOs.add(new TableViweModel(resultSet.getString("SERVICE_NAME"), resultSet.getString("TIER_NAME"), resultSet.getString("INFO"),
						resultSet.getInt("FEE"), resultSet.getInt("NUM_PEOPLE"),resultSet.getInt("id")));
			} // end while
			System.out.println();
		} catch (SQLException e) {
			System.out.println("에러 발생!");
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return serviceVOs;
	} // end selectCategory
} // end class



//  Refactoring 되기 전 코드	

// movie category
//public List<ReadTableDTO> movieList2() {
//	String sql = "select * from PAYMENT_SYSTEM a1 inner join SUBSCRIBE_SERVICE a2 on a1.service_name = a2.service_name where SERVICE_CATEGORY = '영상'";
//	List<ReadTableDTO> serviceVOs = new ArrayList<>();
//	try {
//		PreparedStatement preparedStatement = conn.prepareStatement(sql);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			serviceVOs.add(new ReadTableDTO(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
//					resultSet.getInt(6)));
//		} // end while
//		System.out.println();
//	} catch (SQLException e) {
//		System.out.println("에러 발생!");
//		System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//	} catch (Exception e) {
//		e.printStackTrace();
//	} finally {
//	}
//	return serviceVOs;
//}
//
//// music category
//public List<ReadTableDTO> musicList2() {
//	String sql = "select * from PAYMENT_SYSTEM a1 inner join SUBSCRIBE_SERVICE a2 on a1.service_name = a2.service_name where SERVICE_CATEGORY = '음악'";
//	List<ReadTableDTO> serviceVOs = new ArrayList<>();
//	try {
//		PreparedStatement preparedStatement = conn.prepareStatement(sql);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			serviceVOs.add(new ReadTableDTO(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
//					resultSet.getInt(6)));
//		} // end while
//		System.out.println();
//	} catch (SQLException e) {
//		System.out.println("에러 발생!");
//		System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//	} catch (Exception e) {
//		e.printStackTrace();
//	} finally {
//	}
//	return serviceVOs;
//}
//
//// game category
//public List<ReadTableDTO> gameList2() {
//	String sql = "select * from PAYMENT_SYSTEM a1 inner join SUBSCRIBE_SERVICE a2 on a1.service_name = a2.service_name where SERVICE_CATEGORY = '게임'";
//	List<ReadTableDTO> serviceVOs = new ArrayList<>();
//	try {
//		PreparedStatement preparedStatement = conn.prepareStatement(sql);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			serviceVOs.add(new ReadTableDTO(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
//					resultSet.getInt(6)));
//		} // end while
//		System.out.println();
//	} catch (SQLException e) {
//		System.out.println("에러 발생!");
//		System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//	} catch (Exception e) {
//		e.printStackTrace();
//	} finally {
//	}
//	return serviceVOs;
//}
//
//// cult category
//public List<ReadTableDTO> cultList2() {
//	String sql = "select * from PAYMENT_SYSTEM a1 inner join SUBSCRIBE_SERVICE a2 on a1.service_name = a2.service_name where SERVICE_CATEGORY = '서적'";
//	List<ReadTableDTO> serviceVOs = new ArrayList<>();
//	try {
//		PreparedStatement preparedStatement = conn.prepareStatement(sql);
//		ResultSet resultSet = preparedStatement.executeQuery();
//		while (resultSet.next()) {
//			serviceVOs.add(new ReadTableDTO(resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getInt(5),
//					resultSet.getInt(6)));
//		} // end while
//		System.out.println();
//	} catch (SQLException e) {
//		System.out.println("에러 발생!");
//		System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
//	} catch (Exception e) {
//		e.printStackTrace();
//	} finally {
//	}
//	return serviceVOs;
//}
