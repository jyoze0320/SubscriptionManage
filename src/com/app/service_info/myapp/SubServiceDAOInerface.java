/* @author 손일형 
 * 
 * 인터페이스 SubServiceDAOInerface : SubServiceDAO 에서 제공되어야 할 메서드를 선언함
 */

package com.app.service_info.myapp;

import java.util.List;

public interface SubServiceDAOInerface<T> {
	List<T> dataList();
	List<T> selectCategory(String s);
}//end interface