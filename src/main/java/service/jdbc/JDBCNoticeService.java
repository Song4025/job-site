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

import entity.Card;
import entity.Files;
import service.CardService;

@Service
public class JDBCNoticeService implements CardService {

	@Autowired
	private DataSource dataSource;

	public List<Card> getList(int page, String field, String query) throws ClassNotFoundException, SQLException {

		int start = 1 + (page - 1) * 10; // 1, 11, 21, 31....
		int end = 10 * page; // 10, 20, 30, 40....

		String sql = "SELECT * FROM BUSINESS_CARD_VIEW WHERE " + field + " LIKE ? AND ROWNUM BETWEEN ? AND ?";
//				"SELECT * FROM ("
//				+ "SELECT ROWNUM NUM, N.* FROM ("
//				+ "SELECT * FROM NOTICE ORDER BY REGDATE DESC"
//				+ ") N"
//				+ ")"
//				+ "WHERE NUM BETWEEN ? AND ?";

		// JDBC 드라이버 로드
		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%" + query + "%");
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
	
	public Card getOneCard(Integer id) {
		String sql = "SELECT * FROM BUSINESS_CARD_VIEW WHERE CARD_ID = ?"  ;
		
		try ( Connection con = dataSource.getConnection(); PreparedStatement st = con.prepareStatement(sql);) {
			st.setInt(1, id);
			// JDBC 드라이버 로드
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
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
			
				return  new Card(cardId, title, userName, age, phone, position, url, regDate, hit, pub, jobState);
			}
		}catch (SQLException e) {
	        e.printStackTrace();
	    }finally {
	    	if (id == null) {
	            System.out.println("해당 ID에 대한 카드를 찾을 수 없습니다.");
	        }
		}
		return null;
	}

	public int insert(Card card, List<Files> filesList) throws ClassNotFoundException, SQLException {
		String userName = card.getUser_name();
		int age = card.getAge();
		String phone = card.getPhone();
		String position = card.getPosition();
		String url = card.getUrl();
		String title = card.getTitle();

		// 카드 정보 삽입
		String cardSql = "INSERT INTO BUSINESS_CARD (CARD_ID, USER_NAME, AGE, PHONE, POSITION, PUB_YN, JOB_STATE, URL, REG_DATE, HIT, TITLE)"
				+ " VALUES (card_seq.nextval, ?, ?, ?, ?, ?, ?, ?, SYSDATE, ?, ?)";

		int result = 0;

		try (Connection con = dataSource.getConnection();
				PreparedStatement cardSt = con.prepareStatement(cardSql, new String[] {"CARD_ID"})) {
			cardSt.setString(1, userName);
			cardSt.setInt(2, age);

			String phone_v = phone;

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

			if (cardAffectedRows == 0) {
				throw new SQLException("카드 정보 삽입에 실패했습니다.");
			} else {
				System.out.println("카드정보 삽입성공!");
			}

			result = cardAffectedRows;
			
			// 파일 정보 삽입
			String filesSql = "INSERT INTO FILES (FILE_ID, PATH, CONTENT_TYPE, UPDATE_DATE, CARD_ID)"
					+ " VALUES (file_seq.nextval, ?, ?, ?, ?)";
			
			for(Files files : filesList) {
				// 시퀀스로 생성된 값(카드 ID) 가져오기
				try (ResultSet generatedKeys = cardSt.getGeneratedKeys();
						PreparedStatement filesSt = con.prepareStatement(filesSql);) {
					if (generatedKeys.next()) {
						String cardId = generatedKeys.getString(1); // 시퀀스로 생성된 카드 ID 값
						if (files != null) {
							filesSt.setString(1, files.getPath());
			                filesSt.setString(2, files.getContent_type());
			                filesSt.setDate(3, new java.sql.Date(files.getUpdate_date().getTime()));
			                filesSt.setString(4, cardId); // 카드 ID를 가져와서 파일과 카드를 연결
							int filesAffectedRows = filesSt.executeUpdate();
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
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int getCount() throws ClassNotFoundException, SQLException {
		return 0;
	}

	@Override
	public int update(Card card, List<Files> filesList) throws ClassNotFoundException, SQLException {
		String userName = card.getUser_name();
		Integer age = card.getAge();
		String phone = card.getPhone();
		String position = card.getPosition();
		boolean pubYnValue = card.isPub_yn();
		boolean jobStateValue = card.isJob_state();
		String title = card.getTitle();
		String url = card.getUrl();

		// 카드 정보 삽입
		String cardSql = "UPDATE BUSINESS_CARD SET USER_NAME=?, AGE=?, PHONE=?, POSITION=?, PUB_YN=?, JOB_STATE=?, URL=?, TITLE=?, REG_DATE=SYSDATE WHERE CARD_ID=?";

		int result = 0;

		try (Connection con = dataSource.getConnection();
			PreparedStatement cardSt = con.prepareStatement(cardSql)) {
			System.out.println(cardSt);
			cardSt.setString(1, userName);
			cardSt.setInt(2, age);
			cardSt.setString(3, phone);
			cardSt.setString(4, position);
			cardSt.setBoolean(5, pubYnValue);
			cardSt.setBoolean(6, jobStateValue);
			cardSt.setString(7, title);
			cardSt.setString(8, url);
			cardSt.setString(9, card.getCard_id());

			int cardAffectedRows = cardSt.executeUpdate();

			if (cardAffectedRows == 0) {
				throw new SQLException("카드 정보 업데이트에 실패했습니다.");
			} else {
				System.out.println("카드 정보 업데이트 성공!");
			}

			result = cardAffectedRows;
			
			if (filesList != null) {
				
				// 파일 정보 삽입
				String filesSql = "UPDATE FILES SET PATH=?, CONTENT_TYPE=?, UPDATE_DATE=? WHERE FILE_ID=? ";
	
				try (PreparedStatement filesSt = con.prepareStatement(filesSql);) {
					for(Files file : filesList) {
						String path = file.getPath();
						String contentType = file.getContent_type();
						Date updateDate = file.getUpdate_date();
						filesSt.setString(1, path);
						filesSt.setString(2, contentType);
						filesSt.setDate(3, new java.sql.Date(updateDate.getTime()));
						filesSt.setString(4, file.getFile_id());
						
						int filesAffectedRows = filesSt.executeUpdate();
						
						if (filesAffectedRows == 0) {
							throw new SQLException("파일 정보 업데이트에 실패했습니다.");
						} else {
							System.out.println("파일 정보 업데이트 성공");
						}
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public int delete(Card id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}
	

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