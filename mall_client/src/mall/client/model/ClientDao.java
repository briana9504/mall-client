package mall.client.model;


import mall.client.commons.*;

import mall.client.vo.*;
import java.sql.*;

public class ClientDao {
	private DBUtil dbUtil;
	
	//회원가입 - 이메일 중복검사 필요
	public int insertClient(Client client) {
		//전처리
		this.dbUtil = new DBUtil();
		int rowCnt = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		//db연결 insert
		try {
			conn = this.dbUtil.getConnection();
			String sql="INSERT INTO client(client_mail, client_pw, client_date) VALUES(?,PASSWORD(?),NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			//디버깅
			System.out.printf("stmt: %s<ClientDao.insertClient>\n", stmt);
			stmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
		//return 문
		return rowCnt;
	}
	//이메일 중복검사//null 사용가능한 메일, null아니면 사용불가능
	public String selectClientMail(String clientMail) {
		//전처리
		String returnClientMail = null;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		//DB연결, table 가져오기
		try {
			conn = dbUtil.getConnection();
			String sql = "SELECT client_mail clientMail FROM client WHERE client_mail = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			
			System.out.printf("stmt : %s<ClientDao.selectClientMail>\n", stmt);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClientMail = rs.getString("clientMail");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		//return문
		return returnClientMail;
	}
	
	//로그인
	public Client login(Client client) {
		this.dbUtil = new DBUtil();
		Client returnClient = null;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT client_mail clientMail FROM client WHERE client_mail = ? AND client_pw = PASSWORD(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			stmt.setString(2, client.getClientPw());
			rs = stmt.executeQuery();
			if(rs.next()) {
				returnClient = new Client();
				returnClient.setClientMail(rs.getString("clientMail"));
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		return returnClient;
	}

}
