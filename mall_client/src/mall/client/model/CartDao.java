package mall.client.model;
import mall.client.commons.*;
import mall.client.vo.*;
import java.sql.*;
import java.util.*;

public class CartDao {
	private DBUtil dbUtil;
	//회원타뢰 전 모든 카트정보 삭제
	public void deleteCartByClient(String clientMail) {
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "DELETE from cart WHERE client_mail =?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			System.out.printf("stmt: %s<cartDao.deleteCart>\n", stmt);
			
			stmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
		
	}
	//카트 삭제
	public void deleteCart(Cart cart) {
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "DELETE from cart WHERE client_mail =? AND ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.printf("stmt: %s<cartDao.deleteCart>\n", stmt);
			
			stmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
		
	}
	
	//카트중복검사 - 장바구니에 있는지 확인하기
	public boolean selectClientMail(Cart cart) {
		boolean flag = true;//true가 있으면 사용가능 - 데이터가 없음
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql="SELECT * FROM cart WHERE client_mail=? AND ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());

			System.out.printf("stmt: %s<cartDao.checkCartList>\n", stmt);
			
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				flag = false; //중복있다.
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return flag;
	}
	
	//장바구니 넣기
	public int insertCart(Cart cart) {
		int rowCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO cart(client_mail, ebook_no, cart_date) VALUES(?,?,NOW())";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, cart.getClientMail());
			stmt.setInt(2, cart.getEbookNo());
			System.out.printf("stmt: %s<cartDao.insertCart>\n", stmt);
			
			stmt.executeUpdate();			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
		
		return rowCnt;
	}
	//장바구니 리스트
	public List<Map<String, Object>> selectCartList(Client client){
		List<Map<String, Object>> list = new ArrayList<>();
		
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT c.cart_no cartNo, e.ebook_no ebookNo, e.ebook_title ebookTitle, c.cart_date cartDate FROM cart c INNER JOIN ebook e ON c.ebook_no = e.ebook_no  WHERE client_mail=? ORDER BY cart_date DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, client.getClientMail());
			System.out.printf("stmt: %s<cartDao.selectCartList>\n", stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Map<String, Object> map = new HashMap<>();
				map.put("cartNo", rs.getInt("cartNo"));
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("cartDate", rs.getString("cartDate"));
				list.add(map);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		
		return list;
	}
}
