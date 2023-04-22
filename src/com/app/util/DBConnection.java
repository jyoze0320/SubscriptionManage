package com.app.util;

import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 
 * DB 접속 유틸
 *
 * DataBase와 연결하는 Connection 객체를 하나만 생성하기위해
 * 싱글톤으로 만들어 관리한다.
 *
 */
public class DBConnection {
	private static Connection conn;


	//스태틱 박스를 이용하여 생성자를 이용하지않고 컴파일과 동시에 Connection 객체를 생성한다.
	static {
		
		//프로퍼티 파일에 DB접속 정보를 저장하여 관리의 용의성을 높인다.
		Properties properties = new Properties();
		try {
			Reader file = new FileReader("lib/oracle.properties");
			properties.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String driver = properties.getProperty("driver");
		String url = properties.getProperty("url");
		String pw = properties.getProperty("pw");
		String id = properties.getProperty("id");
		
		//connection을 만든다.
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, "project", "project");
		}catch (ClassNotFoundException e) {
			System.out.println("예외: 드라이버로드 실패 :" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * DBConnection()의 생성자를 private로 하여 외부에서 생성을 할 수 없도록 한다.
	 */
	private DBConnection() {
	}
	
	/**
	 * 
	 * @return oracle.properties에 저장된 값으로 접속된 {@link Connection}
	 */
	public static Connection getConnection() {
		return conn;
	}

}
