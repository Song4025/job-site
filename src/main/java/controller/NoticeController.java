package com.newlecture.web.controller.admin.board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller("adminNoticeController")
@RequestMapping("/admin/board/notice/")
public class NoticeController { // <bean name="noticeController" class="com.newlecture.web.controller.admin.board" />

	@Autowired
	private ServletContext ctx;
	
	@RequestMapping("list")
	public String list() {
		return "admin.board.notice.list";
	}
	
	@GetMapping("reg")
	public String reg() {
		return "admin.board.notice.reg";
	}
	
	@PostMapping("reg")
	public String reg(HttpServletRequest request, String title, String content, MultipartFile[] files, String category, String[] foods, String food) throws IllegalStateException, IOException {
		
		for(MultipartFile file : files) {
		
			String fileName = file.getOriginalFilename();
			long size = file.getSize();
			System.out.printf("fileName: %s, fileSize: %d\n", fileName, size);
			
			// ServletContext ctx = request.getServletContext();
			
			String webPath = "/static/upload";
			String realPath = ctx.getRealPath(webPath);
			System.out.printf("realPath: %s\n", realPath);
			File savePath = new File(realPath);
			// 경로가 존재하지않으면 경로만들어주기
			if(!savePath.exists())
				savePath.mkdirs();
			
			realPath += File.separator + fileName; // 
			File saveFile = new File(realPath);
			
			file.transferTo(saveFile);
		}
		
		for(String food1 : foods)
			System.out.println(food1);
		return "admin.board.notice.reg";
		
		//return String.format("title: %s<br> content: %s<br> category: %s<br> favorite food: %s", title, content, category, food);
	}
	
	@RequestMapping("edit")
	public String edit() {
		return "admin.board.notice.edit";
	}
	
	@RequestMapping("del")
	public String del() {
		return "admin.board.notice.list";
	}
	
}
