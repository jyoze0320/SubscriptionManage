package com.app.myReadTable.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List; 												//import: 외부데이터를 가져와서 사용하는 것

import com.app.myReadTable.dto.PaymentDTO;
import com.app.user.BaseDAO;

public class PaymentDAO implements BaseDAO<PaymentDTO, Integer> {

	// DB 연결 정보
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	private String username = "project";
	private String password = "project";
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private CallableStatement cstmt = null;
	private Connection conn = null; 								// Private: 외부에서 접근할 수 없는 변수

	public PaymentDAO() {
		try { 														// try: 예외발생한 가능성이 있는 문장
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		} catch (Exception e) { 									// catch: Exception e가 발생했을경우, 이를 처리하기 위한 문장
			e.getStackTrace();
		}
	};

	@Override
	public List<PaymentDTO> findAll() {

		String query = "select * from Payment_SYSTEM"; 				// SQL: Payment_SYSTEM에 있는 모든 정보를 불러옴
		List<PaymentDTO> dtos = new ArrayList<>();
		try { 														// try: 예외발생한 가능성이 있는 문장
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				PaymentDTO dto = new PaymentDTO(rs.getInt("ID"), rs.getString("SERVICE_NAME"),
						rs.getString("TIER_NAME"), rs.getInt("FEE"), rs.getInt("NUM_PEOPLE"),rs.getString("info"));
				dtos.add(dto);
			}

		} catch (Exception e) { 									// catch: Expection e 이 발생했을 경우,이를 처리하기 위한 문장
			e.getStackTrace();
		}
		return dtos;
	}

	@Override
	public PaymentDTO findById(Integer id) {
		String query = "select * from Payment_SYSTEM where ID = ?";
		/*
		 * Preaparedstatement: statement구문은 보안에 취약한 방법이다 SQL Injection이라는 취약점을 가지고 있어
		 * 사용하길 권장하지 않는다 따라서 이 대안으로 PreparedStatement를 사용하면 된다 따라서 이 대안으로 물음표를 해서 안에 값을
		 * 넣을 수 있다.
		 */
		PaymentDTO dto = null;
		try { 														// try: 예외발생한 가능성이 있는 문장
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new PaymentDTO(rs.getInt("ID"), rs.getString("SERVICE_NAME"), rs.getString("TIER_NAME"),
						rs.getInt("FEE"), rs.getInt("NUM_PEOPLE"),rs.getString("info"));
			}

		} catch (Exception e) { 									// catch: Expection e 이 발생했을 경우,이를 처리하기 위한 문장
			e.printStackTrace();
		}

		return dto;
	}

	@Override
	public void add(PaymentDTO t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PaymentDTO t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() { 									// App.java에서 결과값을 확인해보기 위해서 (우클릭->Soure->GenertrateToString) 넣는다
		return "PaymentDAO [driver=" + driver + ", url=" + url + ", username=" + username + ", password=" + password
				+ ", stmt=" + stmt + ", pstmt=" + pstmt + ", cstmt=" + cstmt + ", conn=" + conn + "]";
	}

}
//코드:임다솜 주석:노설아
