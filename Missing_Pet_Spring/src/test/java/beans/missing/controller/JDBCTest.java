package beans.missing.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class JDBCTest {
	
	@Test
	public void testConnection() throws Exception{
		//1. 드라이버 로딩	2. 연결객체 생성
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
			System.out.println("DB연결 성공!!");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(conn != null) {
				conn.close();
			}
		}
	}//testConnection
	
	//@Test
	public void testConnection2() throws Exception{
		//1. 드라이버 로딩	2. 연결객체 생성
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		/*
		 	try-with구문: JDK7버전부터 지원
		 		형식) try(AutoCloseable객체){
		 					작업
		 			}catch(Exception e){
		 				예외처리
		 			}
		 			==> finally{conn.close();}문 생략하는 것이 가능
		*/
		
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger")){
			System.out.println("DB연결 성공!!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//testConnection2
}
