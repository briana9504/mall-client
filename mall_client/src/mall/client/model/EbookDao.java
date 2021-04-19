package mall.client.model;

import mall.client.vo.Ebook;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

import mall.client.commons.DBUtil;

public class EbookDao {
	
	private DBUtil dbUtil;
	
	public List<Map<String, Object>> selectEbookListByMonth(int year, int month){
		List<Map<String, Object>> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			
			String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, DAY(ebook_date) d FROM ebook WHERE YEAR(ebook_date)=? AND MONTH(ebook_date)=? ORDER BY DAY(ebook_date)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, year);
			stmt.setInt(2, month);
			System.out.printf("stmt: %s<EbookDao.selectEbookListByDay()>\n", stmt);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("ebookNo", rs.getInt("ebookNo"));
				map.put("ebookTitle", rs.getString("ebookTitle"));
				map.put("d", rs.getInt("d"));
				list.add(map);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//try절에서 예외가 발생해서 catch절로 가든 가지 않던 finally는 실행된다.
			System.out.println(stmt);
			this.dbUtil.close(rs, stmt, conn);
		}
		return list;
		
	}
	
	//전체 페이지  - 검색어
	public int searchTotalCount(String searchWord){
		int totalCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();		
			String sql = "SELECT COUNT(*) cnt From ebook WHERE ebook_title LIKE ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+searchWord+"%");
			System.out.printf("stmt: %s<EbookDao.searchTotalCount>\n", stmt);
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
	
	//ebooktotalCount구하기
	public int totalCount(String categoryName){
		int totalCnt = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			if(categoryName == null) {
				String sql = "SELECT COUNT(*) cnt From ebook";
				stmt = conn.prepareStatement(sql);
				System.out.printf("stmt: %s<EbookDao.totalCount>\n", stmt);
			} else {
				String sql = "SELECT COUNT(*) cnt From ebook WHERE category_name=?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categoryName);
				System.out.printf("stmt: %s<EbookDao.totalCount>\n", stmt);
				
			}
			
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
	//책들 각자 페이지
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
	// 검색별 - ebook목록: index
	public List<Ebook> selectSearchEbookListByPage(int beginRow, int rowPerPage, String searchWord){
		List<Ebook> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			
			String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE ebook_title like ? ORDER BY ebook_date DESC LIMIT ?,?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "%"+searchWord+"%");
			stmt.setInt(2, beginRow);
			stmt.setInt(3, rowPerPage);	
			System.out.printf("stmt: %s<EbookDao.selectEbookListByPage()>\n", stmt);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebookNo"));
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				//ebook.setEbookImg(rs.getString("ebookImg"));
				list.add(ebook);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//try절에서 예외가 발생해서 catch절로 가든 가지 않던 finally는 실행된다.
			System.out.println(stmt);
			this.dbUtil.close(rs, stmt, conn);
		}
		return list;
	}

	public List<Ebook> selectEbookListByPage(int beginRow, int rowPerPage, String categoryName){// index의 페이지별 ebook목록
		
		List<Ebook> list = new ArrayList<>();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = this.dbUtil.getConnection();
			if(categoryName == null) { // 카테고리 없음
				String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook ORDER BY ebook_date DESC LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginRow);
				stmt.setInt(2, rowPerPage);
				System.out.printf("stmt: %s<EbookDao.selectEbookListByPage()>\n", stmt);
				
			} else {// 카테고리 있음
				String sql = "SELECT ebook_no ebookNo, ebook_title ebookTitle, ebook_price ebookPrice FROM ebook WHERE category_name=? ORDER BY ebook_date DESC LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, categoryName);
				stmt.setInt(2, beginRow);
				stmt.setInt(3, rowPerPage);	
				System.out.printf("stmt: %s<EbookDao.selectEbookListByPage()>\n", stmt);
			}
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Ebook ebook = new Ebook();
				ebook.setEbookNo(rs.getInt("ebookNo"));
				ebook.setEbookTitle(rs.getString("ebookTitle"));
				ebook.setEbookPrice(rs.getInt("ebookPrice"));
				//ebook.setEbookImg(rs.getString("ebookImg"));
				list.add(ebook);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {//try절에서 예외가 발생해서 catch절로 가든 가지 않던 finally는 실행된다.
			System.out.println(stmt);
			this.dbUtil.close(rs, stmt, conn);
		}
		return list;
	}

}
