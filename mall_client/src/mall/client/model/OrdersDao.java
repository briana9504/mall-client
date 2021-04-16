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
	public List<Map<String, Object>> selectOrdersListByClient(Client client){
		List<Map<String, Object>> list = new ArrayList<>();
		//전처리
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT o.orders_no ordersNo, o.ebook_no ebookNo, o.orders_state ordersState, e.ebook_title ebookTitle, e.ebook_price ebookPrice, o.orders_date ordersDate FROM orders o INNER JOIN ebook e ON o.ebook_no = e.ebook_no WHERE o.client_no=? ORDER BY o.orders_date DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, client.getClientNo());
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
