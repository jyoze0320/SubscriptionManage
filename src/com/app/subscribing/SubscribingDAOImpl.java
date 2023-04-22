package com.app.subscribing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.util.DBConnection;

public class SubscribingDAOImpl implements SubscribingDAO {

	private static SubscribingDAOImpl instance;
	private Connection conn;

	private SubscribingDAOImpl() {
		try {
			conn = DBConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SubscribingDAOImpl getInstance() {
		if (instance == null)
			instance = new SubscribingDAOImpl();
		return instance;
	}

	@Override
	public List<SubscribingDTO> findAll() {
		String query = "select * from subscribing";

		List<SubscribingDTO> dtos = new ArrayList<>();

		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				dtos.add(new SubscribingDTO(rs.getInt("id"), rs.getString("user_id"), rs.getInt("paysys_id"),
						rs.getInt("num_of_share")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtos;
	}

	@Override
	public SubscribingDTO findById(Integer id) {
		String query = "select * from subscribing where id = ?";
		SubscribingDTO dto = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				dto = new SubscribingDTO(rs.getInt("id"), rs.getString("user_id"), rs.getInt("paysys_id"),
						rs.getInt("num_of_share"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public void add(SubscribingDTO dto) {
		String query = "insert into subscribing(user_id,paysys_id,num_of_share) values(?,?,?)";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2, dto.getPaySysId());
			pstmt.setInt(3, dto.getNumOfShare());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(SubscribingDTO dto) {
		String query = "update subscribing set user_id=?,paysys_id=?,num_of_share=? where user_id=?";

		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, dto.getUserId());
			pstmt.setInt(2, dto.getPaySysId());
			pstmt.setInt(3, dto.getNumOfShare());

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void deleteById(Integer id) {
		String query = "delete from subscribing where id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public SubscribingDTO findByUserIdAndPaymentId(String userId, int paymentId) {
		String query = "select * from subscribing where user_id = ? and paysys_id = ?";
		SubscribingDTO subscribingDTO = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setInt(2, paymentId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				subscribingDTO = new SubscribingDTO(rs.getInt("id"), rs.getString("user_id"), rs.getInt("paysys_id"),
						rs.getInt("num_of_share"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return subscribingDTO;
	}
	
	@Override
	public int getUserTotalPay(String userId) {
		String query = "select sum(ps.fee/s.num_of_share) as total_pay\r\n"
				+ "from subscribing s join service_user su on s.user_id = su.user_id  join payment_system ps on s.paysys_id = ps.id\r\n"
				+ "where s.user_id = ?";
		int totalPay = 0;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				totalPay = rs.getInt("total_pay");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return totalPay;
	}
}
