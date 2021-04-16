package mall.client.model;

import java.util.*;

import mall.client.commons.DBUtil;
import mall.client.vo.*;
import java.sql.*;

public class CategoryDao {
	private DBUtil dbUtil;
	
	//카테고리 이름 리스트
	public List<String> CategoryList(){
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//초기화
		List<String> list = new ArrayList<>();

		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT category_name categoryName FROM category ORDER BY category_weight DESC";
			stmt = conn.prepareStatement(sql);
			System.out.printf("stmt: %s<CategoryDao.CategoryList()>\n",stmt);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String categoryName = rs.getString("CategoryName");
				list.add(categoryName);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return list;
	}

}
