package api;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.Card;
import service.jdbc.JDBCNoticeService;
@Controller
public class SelectData {
	@Autowired
	private JDBCNoticeService service;
	
	@RequestMapping("/select")
	@ResponseBody
	public List<Card> list() throws ClassNotFoundException, SQLException {
        List<Card> list = service.getList(1, "TITLE", "");
        return list;
    }
}
