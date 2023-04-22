package com.app.subscribing;

public class SubscribingService {
	private SubscribingDAO subscribingDAO = SubscribingDAOImpl.getInstance();

	/**
	 * 구독 추가
	 * @param user_id 유저 아이디
	 * @param payment_id 요금제 아이디
	 * @param numOfShare 공유 인원
	 */
	public void addSubscribing(String user_id, int payment_id, int numOfShare) {
		SubscribingDTO subscribingDTO = new SubscribingDTO(user_id, payment_id, numOfShare);
		subscribingDAO.add(subscribingDTO);
	}
	
	/**
	 * 중복 확인<br/>
	 * 한 회원은 같은 요금제를 또 구독할 수 없기때문에 중복체크 필요
	 * @param userId 유저 아이디
	 * @param paySysId 요금제 아이디
	 * @return 중복이라면 true, 아니라면 false
	 */
	public boolean isDuplicated(String userId,int paySysId) {
		if(subscribingDAO.findByUserIdAndPaymentId(userId, paySysId) == null) return false;
		
		return true;
	}
}
