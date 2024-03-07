package service.jdbc;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import entity.Card;

@RestController("cardJDBCController")
@RequestMapping("/card")
public class CardController {

	@Autowired
	private CardController service;
	
	@RequestMapping("list")
	public Card list() throws ClassNotFoundException, SQLException {
		
		List<Card> list = service.getList(1, "USER_NAME", 20, 01044442222, "POSITION", "Y", "BACKEND", "HTTPS://WWW.NAVER.COM", "", 0);
		
		return list.get(0);
	}

	private List<Card> getList(int i, String string, int j, int k, String string2, String string3, String string4,
			String string5, String string6, int l) {
		return null;
	}


}