package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mall.client.commons.DBUtil;
import mall.client.vo.Client;
import mall.client.vo.Orders;

public class OrdersDao {
	private DBUtil dbUtil;
	
	//베스트셀러
	public List<Map<String, Object>> selectBastOrdersList(){
		List<Map<String, Object>> list = new ArrayList<>();
		//전처리
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			//서브쿼리+그룹핑 사용
			String sql = "SELECT t.ebook_no ebookNo, t.cnt cnt, e.ebook_title ebookTitle, e.ebook_price ebookPrice FROM "
					+ "(SELECT ebook_no , COUNT(ebook_no) cnt FROM orders WHERE orders_state = '주문완료' GROUP BY ebook_no "
					+ "HAVING cnt > 1 LIMIT 5) t INNER JOIN ebook e "
					+ "ON t.ebook_no = e.ebook_no ORDER BY cnt DESC";
			stmt = conn.prepareStatement(sql);

			System.out.printf("stmt: %s<OrdersDao.selectBastOrdersList()>\n", stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Map<String, Object> map = new HashMap<>();
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("ebookPrice", rs.getInt("ebookPrice"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return list;
	}
	
	//전체 리스트 숫자 구하기
	public int totalCount(Client client) {
		int cnt = 0;
		//전처리
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//db연결
		try {
			conn = this.dbUtil.getConnection();
			String sql ="SELECT COUNT(*) cnt From orders WHERE client_no = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, client.getClientNo());
			System.out.printf("stmt: %s<OrdersDao.totalCount()>\n", stmt);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return cnt;
	}
	//주문하기
	public void insertOrders(Orders orders) {
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "INSERT INTO orders(ebook_no, client_no, orders_date, orders_state) VALUES(?, ?, NOW(), '주문완료')";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, orders.getEbookNo());
			stmt.setInt(2, orders.getClientNo());
			System.out.printf("stmt: %s<OrdersDao.insertOrders()>\n", stmt);
			
			stmt.executeUpdate();
			
		} catch(Exception e){
			//예외발생시 시스템을 멈추고 함수호출스택구조를 콘솔에 출력
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, stmt, conn);
		}
		
	}
	
	//주문 list가져오기
	public List<Map<String, Object>> selectOrdersListByClient(int beginRow, int rowPerPage,Client client){
		List<Map<String, Object>> list = new ArrayList<>();
		//전처리
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT o.orders_no ordersNo, o.ebook_no ebookNo, o.orders_state ordersState, e.ebook_title ebookTitle, e.ebook_price ebookPrice, o.orders_date ordersDate FROM orders o INNER JOIN ebook e ON o.ebook_no = e.ebook_no WHERE o.client_no=? ORDER BY o.orders_date DESC LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, client.getClientNo());
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);
			System.out.printf("stmt: %s<OrdersDao.selectOrdersList()>\n", stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Map<String, Object> map = new HashMap<>();
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ordersState", rs.getString("ordersState"));
				map.put("ordersNo", rs.getInt("ordersNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("ebookPrice", rs.getInt("ebookPrice"));
				map.put("ordersDate", rs.getString("ordersDate"));
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
