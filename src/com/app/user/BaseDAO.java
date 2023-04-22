package com.app.user;

import java.util.List;

/**
 * 
 * @author 임다솜
 *
 * @param <T> DTO 타입
 * @param <V> ID 타입
 */
public interface BaseDAO <T,V> {

	/**
	 * select all<br/>
	 * @return	테이블의 모든 행
	 */
	List<T> findAll(); 	
	
	/**
	 * select<br/>
	 * @param id primary key 값
	 * @return	id에 해당하는 행 하나
	 */
	T findById(V id); 	
	
	/**
	 * 삽입<br/>
	 * @param t DTO
	 */
	void add(T t);
	
	/**
	 * 수정<br/>
	 * @param t DTO
	 */
	void update(T t);
	
	/**
	 * 삭제<br/>
	 * @param id
	 */
	void deleteById(V id);
}
