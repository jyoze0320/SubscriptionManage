package com.app.user;

import java.util.List;
/**
 * 
 * @author 임다솜
 *
 */
public class UserService {
	private UserDAO userDAO = UserDAOImpl.getInstance();
	
	/**
	 * 중복 아이디 검사<br/>
	 * DB에 userId 값에 해당하는 행이 있는지 검사한다.
	 * @param userId
	 * @return 중복이라면 true 아니라면 false
	 */
	public boolean isDupplicatedId(String userId) {
		if(userDAO.findById(userId)!=null) return true;
		
		return false;
	}
	
	/**
	 * 로그인 검증 <br/>
	 * 로그인에 실패하면 LoginException throw
	 * @param userId 아이디
	 * @param userPassword 패스워드
	 * @return
	 * @throws LoginException
	 */
	public UserDTO login(String userId,String userPassword) throws LoginException{
		UserDTO userDTO = userDAO.findById(userId);
		if(userDTO == null) throw new LoginException("존재하지 않는 아이디 입니다.");
		if(!userDTO.matchPassword(userPassword)) throw new LoginException("비밀번호가 일치하지 않습니다.");
		return userDTO;
	}
	
	public UserDTO getUserById(String userId) {
		return userDAO.findById(userId);
	}
	
	public void updateUser(UserDTO dto) {
		userDAO.update(dto);
	}
	
	public void addOneUser(UserDTO dto) {
		userDAO.add(dto);
	}
	
	/**
	 * 
	 * 두 입력값 비교
	 * 
	 * @param pw1
	 * @param pw2
	 * @return 두 입력이 같다면 true, 아니면 false
	 */
	public boolean compareInputPassword(String pw1,String pw2) {
		if (pw1.equals(pw2)) {
			return true;
		} else {
			return false;
		}
	}
}
