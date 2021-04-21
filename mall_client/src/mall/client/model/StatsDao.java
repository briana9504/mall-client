package mall.client.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import mall.client.commons.DBUtil;
import mall.client.vo.Stats;

public class StatsDao {
	private DBUtil dbUtil;
	//오늘 날짜가 있는지 없는지? - 오늘날짜 카운트
	public Stats selectStatsByToday() {
		Stats stats = null;
		//SELECT stats_day statsDay, stats_count statsCount FROM stats WHERE stats_day=DATE(NOW())
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT stats_day statsDay, stats_count statsCount FROM stats WHERE stats_day=DATE(NOW())";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.printf("stmt: %s<StatsDao.selectStatsByToday()>\n", stmt);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				stats = new Stats();
				stats.setStatsDay(rs.getString("statsDay"));
				stats.setStatsCount(rs.getInt("statsCount"));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return stats;
	}
	//오늘 날짜가 없으면
	public void insertStats() {
		//INSERT INTO stats(stats_day, stats_count) VALUES(DATE(NOW()), 1)
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "INSERT INTO stats(stats_day, stats_count) VALUES(DATE(NOW()), 1)";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.printf("stmt: %s\n", stmt);
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbUtil.close(null, stmt, conn);
		}
		
	}
	//오늘 날짜가 있으면
	public void updateStats() {
		//UPDATE stats SET stats_count = stats_count+1 WHERE stats_day = DATE(NOW())
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			String sql = "UPDATE stats SET stats_count = stats_count+1 WHERE stats_day = DATE(NOW())";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.printf("stmt: %s\n", stmt);
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbUtil.close(null, stmt, conn);
		}
	}
	
	//전체 카운트
	public long selectStatsTotal() {
		//SELECT COUNT(stats_count) total FROM stats
		long total = 0;
		this.dbUtil = new DBUtil();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT SUM(stats_count) total FROM stats";
			conn = this.dbUtil.getConnection();
			stmt = conn.prepareStatement(sql);
			System.out.printf("stmt: %s\n", stmt);
			rs = stmt.executeQuery();
			if(rs.next()) {
				total = rs.getInt("total");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			this.dbUtil.close(rs, stmt, conn);
		}
		
		return total;
	}

}
