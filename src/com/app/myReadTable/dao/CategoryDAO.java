package com.app.myReadTable.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.myReadTable.dto.CategoryDTO;
import com.app.user.BaseDAO;

public class CategoryDAO implements BaseDAO<CategoryDTO, String> {
	/*
	 * implements: 가장 큰 특징은 부모의 메소드를 반드시 오버라이딩(재정의)해야 하며 인터페이스 상속에 사용된다 
	 * extends는 다중삭속이 안되는 반면 implements는 다중상속이 가능하다
	 */

	// DB 연결 정보
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String username = "project";
	private String password = "project";
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private Connection conn = null; 									// Private: 외부에서 접근할 수 없는 변수

	public CategoryDAO() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.getStackTrace();
		}
	}; 																	// try~catch: 예외를 처리하기 위한 구문

	@Override
	public List<CategoryDTO> findAll() {

		String query = "select * from Category";
		List<CategoryDTO> dtos = new ArrayList<>();
		try { 															// 예외발생할 가능성이 있는 문장
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) { 										// (rs.next)가 참일때 포함되는 코드를 실행한다
				CategoryDTO dto = new CategoryDTO(rs.getString("Service_Name"), rs.getString("Service_Category"));
				dtos.add(dto);
			}

		} catch (Exception e) { 										// Expection e 이 발생했을 경우,이를 처리하기 위한 문장
			e.getStackTrace();
		}

		return dtos; 													// return: 값을 반환한다
	}

	@Override
	public CategoryDTO findById(String id) {
		String query = "select * from category where service_name = ?";
		CategoryDTO dto = null;
		try { 															// try: 예외발생한 가능성이 있는 문장
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new CategoryDTO(rs.getString("service_name"), rs.getString("SERVICE_CATEGORY"));
			}

		} catch (Exception e) { 										// catch: Expection e 이 발생했을 경우,이를 처리하기 위한 문장
			e.printStackTrace();
		}

		return dto; 													// return: 값을 반환한다
	}

	@Override
	public void add(CategoryDTO t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(CategoryDTO t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() { 											// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "CategoryDAO [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ ", stmt=" + stmt + ", pstmt=" + pstmt + ", cstmt=" + cstmt + ", conn=" + conn + "]";
	}

}

//코드:임다솜 주석:노설아