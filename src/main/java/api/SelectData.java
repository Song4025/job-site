package api;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Card;
import service.jdbc.JDBCNoticeService;
@Controller
public class SelectData {
	@Autowired
	private JDBCNoticeService service;
	
	@RequestMapping("/select")
	@ResponseBody
	public Card getOneCard(@RequestParam("id") int id) throws ClassNotFoundException, SQLException {
        Card card = service.getOneCard(id);
        return card;
    }
}
