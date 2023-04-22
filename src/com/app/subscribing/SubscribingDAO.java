package com.app.subscribing;

import java.util.List;

import com.app.user.BaseDAO;

public interface SubscribingDAO extends BaseDAO<SubscribingDTO, Integer>{
	SubscribingDTO findByUserIdAndPaymentId(String userId,int paymentId);
	int getUserTotalPay(String userId);
}
