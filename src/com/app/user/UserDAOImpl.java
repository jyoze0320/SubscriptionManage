package com.app.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.app.util.DBConnection;
/**
 * 
 * UserDAO 구현부<br/>
 * 싱글톤으로 관리
 * @author 임다솜
 *
 */
public class UserDAOImpl implements UserDAO{
	
	private static UserDAOImpl instance;
	private Connection conn;
	
	private UserDAOImpl() {
		try {
			conn = DBConnection.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static UserDAOImpl getInstance() {
		if(instance == null) instance = new UserDAOImpl();
		return instance;
	}
	
	@Override
	public List<UserDTO> findAll() {
		String query = "select * from service_user";
		List<UserDTO> users = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				users.add(new UserDTO(rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_name"),
						rs.getString("user_email"),
						rs.getString("user_role")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public UserDTO findById(String userId) {
		String query = "select * from service_user where user_id = ?";
		UserDTO user = null;
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new UserDTO(rs.getString("user_id"),
						rs.getString("user_pw"),
						rs.getString("user_name"),
						rs.getString("user_email"),
						rs.getString("user_role"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public void add(UserDTO userDTO) {
		String query = "insert into service_user(user_id,user_pw,user_name,user_email) values(?,?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1,userDTO.getUser_id());
			pstmt.setString(2, userDTO.getUser_pw());
			pstmt.setString(3,userDTO.getUser_name());
			pstmt.setString(4,userDTO.getUser_email());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(UserDTO userDTO) {
		String query = "update service_user set user_pw=?,user_name=?,user_email=?,user_role=? where user_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userDTO.getUser_pw());
			pstmt.setString(2, userDTO.getUser_name());
			pstmt.setString(3, userDTO.getUser_email());
			pstmt.setString(4, userDTO.getUser_role());
			pstmt.setString(5, userDTO.getUser_id());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	@Override
	public void deleteById(String userId) {
		String query = "delete from service_uer where user_id=?";
		try {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			int rn = pstmt.executeUpdate();
			System.out.println(rn);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
