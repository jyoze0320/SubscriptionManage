package test;

import com.app.subscribing.SubscribingDAO;
import com.app.subscribing.SubscribingDAOImpl;

public class Test {
	public static void main(String[] args) {
		SubscribingDAO dao = SubscribingDAOImpl.getInstance();
		
		System.out.println(dao.getUserTotalPay("a"));
		
	}
}
