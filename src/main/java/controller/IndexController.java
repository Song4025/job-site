package controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import entity.Card;
import service.jdbc.JDBCNoticeService;

@Controller
public class IndexController {
	
//	@GetMapping
//	public String index() {
//		return "index";
//	}
	@Autowired
	private JDBCNoticeService service;
	
	@RequestMapping("/index")
	@GetMapping
	public ModelAndView list() throws ClassNotFoundException, SQLException {
        List<Card> list = service.getList(1, "TITLE", "");
        
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("list", list);
        
        return modelAndView;
    }

}
