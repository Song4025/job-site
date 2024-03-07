package controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.Card;
import service.jdbc.JDBCNoticeService;

@Controller
@RequestMapping("/index")
public class IndexController {
	
//	@GetMapping
//	public String index() {
//		return "index";
//	}
	@Autowired
	private JDBCNoticeService service;
	
	
	@GetMapping
	public String index(Model model) throws ClassNotFoundException, SQLException {
        List<Card> list = service.getList(1, "TITLE", "");
        model.addAttribute("card", list.get(0));
        return "index";
    }

}
