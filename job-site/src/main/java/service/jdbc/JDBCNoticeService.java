package service.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import entity.Card;

@Service
public class JDBCNoticeService {

	@Autowired
	private DataSource dataSource;
	
	public List<Card> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {

		int start = 1 + (page-1)*10; // 1, 11, 21, 31....
		int end = 10*page; // 10, 20, 30, 40....
		
		String sql = "SELECT * FROM BUSINESS_CARD_VIEW WHERE "+field+" LIKE ? AND NUM BETWEEN ? AND ?";
//				"SELECT * FROM ("
//				+ "SELECT ROWNUM NUM, N.* FROM ("
//				+ "SELECT * FROM NOTICE ORDER BY REGDATE DESC"
//				+ ") N"
//				+ ")"
//				+ "WHERE NUM BETWEEN ? AND ?";
				
				
		// JDBC 드라이버 로드
		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+query+"%");
		st.setInt(2, start);
		st.setInt(3, end);
		ResultSet rs = st.executeQuery();

		List<Card> list = new ArrayList<Card>();

		while (rs.next()) {
			String id = rs.getString("CARD_ID");
			String title = rs.getString("TITLE");
			String userName = rs.getString("USER_NAME");
			int age = rs.getInt("AGE");
			int phone = rs.getInt("PHONE");
			String position = rs.getString("POSITION");
			String url = rs.getString("URL");
			Date regDate = rs.getDate("REG_DATE");
			int hit = rs.getInt("HIT");
			char pub = ' '; 
			String pubStr = rs.getString("PUB_YN");
			if (pubStr != null && !pubStr.isEmpty()) {
				pub = pubStr.charAt(0);
		    }
			String jobState = rs.getString("JOB_STATE");
			
			Card card = new Card(id, title, userName, age, phone, position, url, regDate, hit, pub, jobState);

			list.add(card);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	
	
//	public int getCount() throws ClassNotFoundException, SQLException {
//		int count = 0;
//
//		String sql = "SELECT COUNT(ID) COUNT FROM NOTICE";
//		
//		Connection con = dataSource.getConnection();
//		Statement st = con.createStatement();
//		
//		ResultSet rs = st.executeQuery(sql);
//
//		if(rs.next()) 
//			count = rs.getInt("COUNT");
//
//		rs.close();
//		st.close();
//		con.close();
//
//		return count;
//	}
//
	public int insert(Card card) throws ClassNotFoundException, SQLException {
		String title = card.getTitle();
		String writerId = card.getWriter_id();
		String content = card.getContent();
		//String files = notice.getFiles();

		String sql = "INSERT INTO notice (" + "    title," + "    writer_id," + "    content," + "    files"
				+ ") VALUES (?,?,?,?)";

		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, title);
		st.setString(2, writerId);
		st.setString(3, content);
		//st.setString(4, files);

		int result = st.executeUpdate();
		st.close();
		con.close();

		return result;
	}
//
//	public int update(Notice notice) throws ClassNotFoundException, SQLException {
//		String title = notice.getTitle();
//		String content = notice.getContent();
//		//String files = notice.getFiles();
//		String id = notice.getId();
//
//		String sql = "UPDATE NOTICE " + "SET " + "    TITLE = ?," + "    CONTENT = ?," + "    FILES = ?"
//				+ "WHERE ID = ?";
//
//		Connection con = dataSource.getConnection();
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, title);
//		st.setString(2, content);
//		//st.setString(3, files);
//		st.setString(3, id);
//
//		int result = st.executeUpdate();
//		st.close();
//		con.close();
//
//		return result;
//	}
//
//	public int delete(Notice notice) throws ClassNotFoundException, SQLException {
//
//		String id = notice.getId();
//		String sql = "DELETE NOTICE WHERE ID=?";
//
//		Connection con = dataSource.getConnection();
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, id);
//
//		int result = st.executeUpdate();
//
//		st.close();
//		con.close();
//
//		return result;
//	}

}