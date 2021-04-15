package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mall.client.commons.DBUtil;

public class OrdersDao {
	private DBUtil dbUtil;
	
	//주문 list가져오기
	public List<Map<String, Object>> selectOrdersList(String clientMail){
		List<Map<String, Object>> list = new ArrayList<>();
		//전처리
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT e.ebook_title ebookTitle, o.orders_date ordersDate, e.ebook_price ebookPrice FROM orders o INNER JOIN  ebook e INNER JOIN client c ON o.eook_no = e.ebook_no AND o.client_no = c.client_no where c.client_mail = ? ORDER BY o.orders_date DESC";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, clientMail);
			System.out.printf("stmt: %s<OrdersDao.selectOrdersList>\n", stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()){
				Map<String, Object> map = new HashMap<>();
				map.put("ebookTitle", rs.getInt("ebookTitle"));
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
