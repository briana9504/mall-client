package mall.client.model;

import mall.client.vo.Ebook;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

import mall.client.commons.DBUtil;

public class EbookDao {
	
	private DBUtil dbUtil;
	
	//ebooktotalCount구하기
		public int totalCount(){
			int totalCnt = 0;
			this.dbUtil = new DBUtil();
			Connection conn = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				conn = this.dbUtil.getConnection();
				String sql = "SELECT COUNT(*) cnt From ebook";
				stmt = conn.prepareStatement(sql);
				rs = stmt.executeQuery();
				
				if(rs.next()) {
					totalCnt = rs.getInt("cnt");
				}
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				this.dbUtil.close(rs, stmt, conn);
			}
			return totalCnt;
		}
	
	public Ebook selectEbookOne(int ebookNo) {
		Ebook ebook = null;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT ebook_title ebookTitle, ebook_state ebookState, ebook_no ebookNo, category_name categoryName, ebook_summary ebookSummary, ebook_author ebookAuthor, ebook_company ebookCompany, ebook_page_count ebookPageCount, ebook_date ebookDate, ebook_img ebookImg, ebook_price ebookPrice, ebook_isbn ebookISBN FROM ebook WHERE ebook_no=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ebookNo);
			rs = stmt.executeQuery();
			while(rs.next()) {
				ebook = new Ebook();
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				ebook.setEbookISBN(rs.getString("ebookISBN"));
				ebook.setEbookImg(rs.getString("ebookImg"));
				ebook.setEbookDate(rs.getString("ebookDate"));
				ebook.setEbookPageCount(rs.getInt("ebookPageCount"));
				ebook.setEbookCompany(rs.getString("ebookCompany"));
				ebook.setEbookAuthor(rs.getString("ebookAuthor"));
				ebook.setEbookSummary(rs.getString("ebookSummary"));
				ebook.setCategoryName(rs.getString("categoryName"));
				ebook.setEbookNo(rs.getInt("ebookNo"));
				ebook.setEbookState(rs.getString("ebookState"));
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//try절에서 예외가 발생해서 catch절로 가든 가지 않던 finally는 실행된다.
			System.out.println(stmt);
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return ebook;
	}
	//index 전체 페이지 수 구하기
	
	public List<Ebook> selectEbookListByPage(int beginRow, int rowPerPage){// index의 페이지별 ebook목록
		
		List<Ebook> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook ORDER BY ebook_date DESC LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebookNo"));
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				//ebook.setEbookImg(rs.getString("ebookImg"));
				list.add(ebook);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {//try절에서 예외가 발생해서 catch절로 가든 가지 않던 finally는 실행된다.
			System.out.println(stmt);
			this.dbUtil.close(rs, stmt, conn);
		}
		return list;
	}

}
