package service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import entity.Card;
import entity.Files;
import service.CardService;

@Service
public class JDBCNoticeService implements CardService{

	@Autowired
	private DataSource dataSource;
	
	public List<Card> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {

		int start = 1 + (page-1)*10; // 1, 11, 21, 31....
		int end = 10*page; // 10, 20, 30, 40....
		
		String sql = "SELECT * FROM BUSINESS_CARD_VIEW WHERE "+field+" LIKE ? AND ROWNUM BETWEEN ? AND ?";
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
			String cardId = rs.getString("CARD_ID");
			String title = rs.getString("TITLE");
			String userName = rs.getString("USER_NAME");
			int age = rs.getInt("AGE");
			String phone = rs.getString("PHONE");
			String position = rs.getString("POSITION");
			String url = rs.getString("URL");
			Date regDate = rs.getDate("REG_DATE");
			int hit = rs.getInt("HIT"); 
			boolean pub = rs.getBoolean("PUB_YN");
			boolean jobState = rs.getBoolean("JOB_STATE");
			
			Card card = new Card(cardId, title, userName, age, phone, position, url, regDate, hit, pub, jobState);

			list.add(card);
		}

		rs.close();
		st.close();
		con.close();

		return list;
	}

	public int insert(Card card, Files files) throws ClassNotFoundException, SQLException {

		String userName = card.getTitle();
		int age = card.getAge();
		String phone = card.getPhone();
		String position = card.getPosition();
		String url = card.getUrl();
		String title = card.getTitle();
		String path = files.getPath();
		String contentType = files.getContent_type();
		Date updateDate = files.getUpdate_date();
		
		Connection con = null;
	    PreparedStatement cardSt = null;
	    PreparedStatement filesSt = null;
	    int result = 0;
		
		try {
	        con = dataSource.getConnection();
	        con.setAutoCommit(false); // 트랜잭션 시작

	        // 카드 정보 삽입
	        String cardSql = "INSERT INTO BUSINESS_CARD (CARD_ID, USER_NAME, AGE, PHONE, POSITION, PUB_YN, JOB_STATE, URL, REG_DATE, HIT, TITLE)"
	                + " VALUES (card_seq.nextval, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?)";
	        cardSt = con.prepareStatement(cardSql);
	        cardSt.setString(1, userName);
	        cardSt.setInt(2, age);
	        
	        String phone_v = "";
	        try {
	            phone_v = phone;
	            System.out.println(Integer.parseInt(phone_v));
	        } catch (NumberFormatException e) {
	            System.out.println("올바른 전화번호 형식이 아닙니다.");
	            e.printStackTrace(); 
	        }
	        
	        cardSt.setString(3, phone_v);
	        cardSt.setString(4, position);
	        String pubYnValue = card.isPub_yn() ? "1" : "0";
	        String jobStateValue = card.isJob_state() ? "1" : "0";
	        
	        cardSt.setString(5, pubYnValue);
	        cardSt.setString(6, jobStateValue);
	        cardSt.setString(7, url);
	        cardSt.setInt(8, 0);
	        cardSt.setString(9, title);

	        int cardAffectedRows = cardSt.executeUpdate();
	        int filesAffectedRows = 0;
	        
	        if (cardAffectedRows == 0) {
	            throw new SQLException("카드 정보 삽입에 실패했습니다.");
	        } else {
	        	System.out.println("카드정보 삽입성공!");
	        }
	        
	        // 시퀀스로 생성된 값(카드 ID) 가져오기
	        try (ResultSet generatedKeys = cardSt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int cardId = generatedKeys.getInt(1); // 시퀀스로 생성된 카드 ID 값
	                if(files != null) {
	                	// 파일 정보 삽입
	        	        String filesSql = "INSERT INTO FILES (FILE_ID, PATH, CONTENT_TYPE, UPDATE_DATE, CARD_ID)"
	        	                + " VALUES (file_seq.nextval, ?, ?, ?, ?)";
	        	        filesSt = con.prepareStatement(filesSql);
	        	        filesSt.setString(1, path);
	        	        filesSt.setString(2, contentType);
	        	        filesSt.setDate(3, new java.sql.Date(updateDate.getTime())); // java.util.Date를 java.sql.Date로 변환
	        	        filesSt.setInt(4, cardId);
	        	        filesAffectedRows = filesSt.executeUpdate();
	                    if (filesAffectedRows == 0) {
	                        throw new SQLException("파일 정보 삽입에 실패했습니다.");
	                    } else {
	                        System.out.println("파일 정보 삽입 성공");
	                    }
	                }
	            } else {
	                throw new SQLException("카드 정보 삽입에 실패했습니다.");
	            }
	        }
	        
	        con.commit(); // 트랜잭션 커밋
	        
        result = cardAffectedRows + filesAffectedRows;
        
		} catch (SQLException e) {
		    if (con != null) {
		        try {
		            con.rollback(); // 트랜잭션 롤백
		        } catch (SQLException rollbackEx) {
		            rollbackEx.printStackTrace();
		        }
		    }
		    e.printStackTrace();
		}
		
		//int result = cardSt.executeUpdate()+filesSt.executeUpdate();
		return result;
	}

	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Card card) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Card id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void insert(String title, String userName, int age, String phone, String url, boolean pub, boolean jobState,
			MultipartFile[] files) {
		// TODO Auto-generated method stub
		
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