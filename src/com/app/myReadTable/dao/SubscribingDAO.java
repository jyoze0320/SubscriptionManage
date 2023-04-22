package com.app.myReadTable.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;														//	저장되어있는 자료를 불러옴

import com.app.myReadTable.dto.SubscribingDTO;
import com.app.user.BaseDAO;

public class SubscribingDAO implements BaseDAO<SubscribingDTO, Integer> { 
	// DB 연결 정보
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String username = "project";
	private String password = "project";
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private Connection conn = null;											// Private: 외부에서 접근할 수 없는 변수	

	public SubscribingDAO() {
		try {																// try : 예외발생할 가능성이 있는 문장 
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {												// catch : Expection e 이 발생했을 경우,이를 처리하기 위한 문장 
			e.getStackTrace();
		}
	}

	@Override
	public SubscribingDTO findById(Integer id) {
		String query = "select * from Subscribing where ID = ?";
		SubscribingDTO dto = null;
		try {																// try : 예외발생할 가능성이 있는 문장
																			pstmt = conn.prepareStatement(query);							/*  Statement클래스:
																			SQL 구문을 실행하는 역할
																			스스로는 SQL 구문 이해 못함(구문해석X) -> 전달역할
																			SQL관리 O + 연결 정보 x
																			PreparedStatement: 
																			Statement 클래스의 기능향상
																			인자와 관련된 작업이 특화(매개변수)
																			코드 안정성 높음,가독성 높음
																			코드량이 증가->매개변수를set해줘야하기 때문에						*
																			텍스트 SQL호출	 */
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SubscribingDTO(rs.getInt("ID"), rs.getString("USER_ID"), rs.getInt("PAYSYS_ID"),
						rs.getInt("NUM_OF_SHARE"));
			}

		} catch (Exception e) {												// catch :Expection e 이 발생했을경우 리를 처리하기 위한 문장
			e.printStackTrace();
		}

		return dto;
	}

	public List<SubscribingDTO> findAll() {
		String query = "select * from subscribing";
		List<SubscribingDTO> dtos = new ArrayList<>();
		try {																// try: 예외발생한 가능성이 있는 문장
			stmt = conn.createStatement();
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				SubscribingDTO dto = new SubscribingDTO(rs.getInt("ID"), rs.getString("USER_ID"),
						rs.getInt("PAYSYS_ID"), rs.getInt("NUM_OF_SHARE"));
				dtos.add(dto);
			}

		} catch (Exception e) {												// catch :Expection e 이 발생했을경우 리를 처리하기 위한 문장
			e.getStackTrace();
		}
		return dtos;
	}

	public List<SubscribingDTO> findAllByUserId(String user_id) {
		String query = "select * from subscribing where user_id = ?";
		List<SubscribingDTO> dtos = new ArrayList<>();
		try {																// try: 예외발생한 가능성이 있는 문장
			stmt = conn.createStatement();
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user_id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				SubscribingDTO dto = new SubscribingDTO(rs.getInt("ID"), rs.getString("USER_ID"),
						rs.getInt("PAYSYS_ID"), rs.getInt("NUM_OF_SHARE"));
				dtos.add(dto);
			}
		
		} catch (Exception e) {												// catch :Expection e 이 발생했을경우 리를 처리하기 위한 문장
			e.getStackTrace();
		}
		return dtos;
	}

	@Override
	public void add(SubscribingDTO t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(SubscribingDTO t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		String query = "delete from subscribing where id = ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query); 					// id를 석택하면 삭제할 수 있는 쿼리문
			pstmt.setInt(1, id);
			int rn = pstmt.executeUpdate();
			System.out.println(rn);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public String toString() { 													// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "SubscribingDAO [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ ", stmt=" + stmt + ", pstmt=" + pstmt + ", cstmt=" + cstmt + ", conn=" + conn + "]";
	}

}

//코드:임다솜 주석:노설아