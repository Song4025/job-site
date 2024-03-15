package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller("cardController")
public class CardController {

	@Autowired
	private ServletContext ctx;
	/*
	 * @RequestMapping("/") public String list() { return "/"; }
	 */
	
	@GetMapping("reg")
	public String reg() {
		return "reg";
	}
	
	@PostMapping("reg")
	public String reg(HttpServletRequest request, String title, String user_name, int age, String phone, String position, Character pub_yn, String job_state,  MultipartFile[] files ) throws IllegalStateException, IOException {
		
		if (files != null) {
			for(MultipartFile file : files) {
				
				String fileName = file.getOriginalFilename();
				long size = file.getSize();
				System.out.printf("fileName: %s, fileSize: %d\n", fileName, size);
				
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

//			return "reg";
			
			return String.format("title: %s<br> age: %s<br>", title, age);
		} else {
			return "index";
		}
	}
	
	@RequestMapping("edit")
	public String edit() {
		return "edit";
	}
	
	@RequestMapping("del")
	public String del() {
		return "/";
	}
	
}
