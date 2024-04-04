package service;

import java.sql.SQLException;
import java.util.List;

import entity.Card;
import entity.Files;

public interface CardService {

	List<Card> getList(int page, String field, String query) throws ClassNotFoundException, SQLException;
	int getCount() throws ClassNotFoundException, SQLException;
	int insert(Card card, List<Files> filesList) throws ClassNotFoundException, SQLException;
	int delete(Card id) throws ClassNotFoundException, SQLException;
	int update(Card card, List<Files> filesList) throws ClassNotFoundException, SQLException;
	
}
