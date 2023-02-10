package chatbot.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import chatbot.Reserve;


public class DataBaseServiceImpl implements DataBaseService{

	Connection con = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;
	
	
	public DataBaseServiceImpl() {
		
		//데이터 연결을 위한 객체 처리
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "system";
		String pass = "0000";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			System.out.println("\n------------------------------");
			System.out.println("오라클 연결 성공");
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("실패");
		}
		System.out.println("\n------------------------------\n");
		
	}


	@Override
	public boolean reserve(Reserve r) throws SQLException {
		
		try {
			String sql = "INSERT INTO reserve VALUES(?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, r.getName());
			pstmt.setString(2, r.getPhone());
			pstmt.setInt(3, r.getMem());
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println(r.getName()+"님, "+r.getMem()+"명 예약되셨습니다.");
				return true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("예약 실패");
		return false;
	}



	
	
}
