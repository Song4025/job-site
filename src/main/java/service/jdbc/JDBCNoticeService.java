package service.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
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
	
	public List<Files> getFilesList() throws ClassNotFoundException, SQLException {

		String sql = "SELECT FILE_CARD_ID, FILE_ID, CONTENT_TYPE, PATH, UPDATE_DATE, FILE_SIZE  FROM BUSINESS_CARD_VIEW";

		// JDBC 드라이버 로드
		Connection con = dataSource.getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		List<Files> getFilesList = new ArrayList<Files>();

		while (rs.next()) {
			String fileCardId = rs.getString("FILE_CARD_ID");
			String fileId = rs.getString("FILE_ID");
			String contentType = rs.getString("CONTENT_TYPE");
			String path = rs.getString("PATH");
			Date updateDate = rs.getDate("UPDATE_DATE");
			long size = rs.getLong("FILE_SIZE");

			Files files = new Files(fileCardId, fileId, contentType, path, updateDate, size);

			getFilesList.add(files);
		}

		rs.close();
		st.close();
		con.close();

		return getFilesList;
	}

	public int insert(Card card, List<Files> filesList) throws ClassNotFoundException, SQLException {
		String userName = card.getUser_name();
		int age = card.getAge();
		String phone = card.getPhone();
		String position = card.getPosition();
		String url = card.getUrl();
		String title = card.getTitle();
		String cardId = ""; // 새로 생성된 카드의 ID를 저장할 변수

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
			
			// 삽입된 카드의 ID 가져오기
	        try (ResultSet generatedKeys = cardSt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                cardId = generatedKeys.getString(1);
	                System.out.println("생성된카드아이디:" +cardId);
	            } else {
	                throw new SQLException("카드 정보 삽입에 실패했습니다.");
	            }
	        }

			result = cardAffectedRows;
			
			// 파일 정보 삽입
			String filesSql = "INSERT INTO FILES (FILE_ID, PATH, CONTENT_TYPE, UPDATE_DATE, CARD_ID, FILE_SIZE)"
					+ " VALUES (file_seq.nextval, ?, ?, ?, ?)";
			
			for(Files files : filesList) {
				// 시퀀스로 생성된 값(카드 ID) 가져오기
				try (PreparedStatement filesSt = con.prepareStatement(filesSql);) {
					if (files != null) {
						filesSt.setString(1, files.getPath());
		                filesSt.setString(2, files.getContent_type());
		                filesSt.setDate(3, new java.sql.Date(files.getUpdate_date().getTime()));
		                filesSt.setString(4, cardId); // 카드 ID를 가져와서 파일과 카드를 연결
		                filesSt.setLong(5, files.getFile_size());
						int filesAffectedRows = filesSt.executeUpdate();
						if (filesAffectedRows == 0) {
							throw new SQLException("파일 정보 삽입에 실패했습니다.");
						} else {
							System.out.println("파일 정보 삽입 성공");
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
	public int getCount() throws ClassNotFoundException, SQLException {
		return 0;
	}

	@Override
	public int update(Card card, List<Files> filesList) throws ClassNotFoundException, SQLException {
		String userName = card.getUser_name();
		Integer age = card.getAge();
		String phone = card.getPhone();
 		String position = card.getPosition();
		String title = card.getTitle();
		String url = card.getUrl();
		String cardId = card.getCard_id();
		
		int result = 0;
		
		// 카드 정보 삽입
		String cardSql = "UPDATE BUSINESS_CARD SET USER_NAME=?, AGE=?, PHONE=?, POSITION=?, PUB_YN=?, JOB_STATE=?, URL=?, TITLE=?, REG_DATE=SYSDATE WHERE CARD_ID=?";
		
		try (Connection con = dataSource.getConnection();
			PreparedStatement cardSt = con.prepareStatement(cardSql)) {
			
			cardSt.setString(1, userName);
			if (age != null) {
			    int ageValue = age.intValue();
			    cardSt.setInt(2, ageValue);
			} else {
			    cardSt.setNull(2, Types.INTEGER); 
			}
			cardSt.setString(3, phone);
			cardSt.setString(4, position);
			String upPubYn = card.isPub_yn() ? "1" : "0";
			String upJobState = card.isJob_state() ? "1" : "0";
			cardSt.setString(5, upPubYn);
			cardSt.setString(6, upJobState);
			cardSt.setString(7, url);
			cardSt.setString(8, title);
			cardSt.setString(9, cardId);
			int cardAffectedRows = cardSt.executeUpdate();

			if (cardAffectedRows == 0) {
				throw new SQLException("카드 정보 업데이트에 실패했습니다.");
			} else {
				System.out.println("카드 정보 업데이트 성공!");
			}

			result = cardAffectedRows;
			
			// 기존파일정보
			String beforeFilesSql = "SELECT * FROM FILES WHERE CARD_ID =?";
			
			for(Files files : filesList) {
			    try (PreparedStatement beforeFilesSt = con.prepareStatement(beforeFilesSql);) {
			        beforeFilesSt.setString(1, cardId);
			        try (ResultSet rs = beforeFilesSt.executeQuery()) {
			            while (rs.next()) {
			                Files existingFile = new Files();
			                existingFile.setFile_id(rs.getString("FILE_ID"));
			                existingFile.setPath(rs.getString("PATH"));
			                existingFile.setContent_type(rs.getString("CONTENT_TYPE"));
			                existingFile.setUpdate_date(rs.getDate("UPDATE_DATE"));
			                existingFile.setFile_card_id(rs.getString("CARD_ID"));
			                existingFile.setFile_size(rs.getLong("FILE_SIZE"));
			                
			                // 파일 크기가 다르면 삭제 후 새 파일 추가
			                if (existingFile.getFile_size() != files.getFile_size()) {
			                    // 파일 삭제
			                    deleteFile(con, existingFile.getFile_id());
			                    // 새 파일 추가
			                    insertFile(con, files);
			                }
			            }
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			        throw new SQLException("파일정보 비교 중 오류가 발생했습니다.", e);
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("카드 정보 업데이트 중 오류가 발생했습니다: " + e.getMessage());
		}
		return result;
	}

	private void deleteFile(Connection con, String fileId) throws SQLException {
	    String deleteFileSql = "DELETE FROM FILES WHERE FILE_ID = ?";
	    try (PreparedStatement deleteFileSt = con.prepareStatement(deleteFileSql);) {
	        deleteFileSt.setString(1, fileId);
	        int deletedRows = deleteFileSt.executeUpdate();
	        if (deletedRows == 0) {
	            throw new SQLException("파일 삭제에 실패했습니다.");
	        } else {
	            System.out.println("파일이 성공적으로 삭제되었습니다.");
	        }
	    }
	}
	
	private void insertFile(Connection con, Files files) throws SQLException {
	    String insertFileSql = "INSERT INTO FILES (FILE_ID, PATH, CONTENT_TYPE, UPDATE_DATE, CARD_ID, FILE_SIZE)  VALUES (file_seq.nextval, ?, ?, ?, ?, ?)";
	    try (PreparedStatement insertFileSt = con.prepareStatement(insertFileSql);) {
	        insertFileSt.setString(1, files.getPath());
	        insertFileSt.setString(2, files.getContent_type());
	        insertFileSt.setDate(3, new java.sql.Date(files.getUpdate_date().getTime()));
	        insertFileSt.setString(4, files.getFile_card_id());
	        insertFileSt.setLong(5, files.getFile_size());
	        int insertedRows = insertFileSt.executeUpdate();
	        if (insertedRows == 0) {
	            throw new SQLException("파일 추가에 실패했습니다.");
	        } else {
	            System.out.println("파일이 성공적으로 추가되었습니다.");
	        }
	    }
	}

	@Override
	public int delete(Card card) throws ClassNotFoundException, SQLException {
		int result = 0;
		String fSql = "DELETE FROM FILES WHERE CARD_ID=?";
		String bcSql = "DELETE FROM BUSINESS_CARD WHERE CARD_ID=?";
		try (	Connection con = dataSource.getConnection();
				PreparedStatement fileDel = con.prepareStatement(fSql);
				PreparedStatement cardDel = con.prepareStatement(bcSql);) {
			fileDel.setString(1, card.getCard_id());			
			int filesAffectedRows = fileDel.executeUpdate();
			if (filesAffectedRows == 0) {
				throw new SQLException("파일정보 삭제 실패");
			} else {
				System.out.println("파일정보 삭제 성공");
			}
			cardDel.setString(1, card.getCard_id());
			int cardAffectedRows = cardDel.executeUpdate();
			if (cardAffectedRows == 0) {
				throw new SQLException("카드정보 삭제 실패");
			} else {
				System.out.println("카드정보 삭제 성공");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("카드 삭제 중 오류가 발생했습니다: " + e.getMessage());
		}
		return result;
	}

}