package mall.client.model;

import mall.client.vo.Ebook;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import mall.client.commons.DBUtil;

public class EbookDao {
	
	private DBUtil dbutil;
	
	public List<Ebook> selectEbookListByPage(int beginRow, int rowPerPage){//페이지별 ebook목록
		
		List<Ebook> list = new ArrayList<>();
		this.dbutil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbutil.getConnection();
			String sql = "SELECT ebook_title ebookTitle, ebook_price ebookPrice FROM ebook ORDER BY ebook_date DESC LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				//ebook.setEbookImg(rs.getString("ebookImg"));
				list.add(ebook);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//try절에서 예외가 발생해서 catch절로 가든 가지 않던 finally는 실행된다.
			System.out.println(stmt);
			this.dbutil.close(rs, stmt, conn);
		}
		return list;
	}

}
