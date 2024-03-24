package controller;

import java.io.File; 
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import entity.Card;
import entity.Files;
import service.jdbc.JDBCNoticeService;

@Controller("cardController")
public class CardController {

	@Autowired
	private JDBCNoticeService service;
	private final String filePath = "C:\\fileUpload";

	@GetMapping("/reg")
	public String reg() {
		return "reg";
	}

	@PostMapping("/reg")
	public String reg(String title, String userName, int age,
		 String phone, String position, String url, boolean pub, boolean jobState, MultipartFile[] files) throws IllegalStateException, IOException, ClassNotFoundException, SQLException {

		if (files != null) {
			for (MultipartFile file : files) {
				if(file.getSize() == 0L) {
					continue;
				}
				String fileName = file.getOriginalFilename();
				long size = file.getSize();
				System.out.printf("fileName: %s, fileSize: %d\n", fileName, size);
				File savePath = new File(filePath);
				// 경로가 존재하지않으면 경로만들어주기
				if (!savePath.exists())
					savePath.mkdirs();

				File saveFile = new File(filePath + File.separator + fileName);

				file.transferTo(saveFile);
			}
		}
		
		// Card 객체 생성 및 값 설정
        Card card = new Card();
        card.setTitle(title);
        card.setUser_name(userName);
        card.setAge(age);
        card.setPhone(phone);
        card.setPosition(position);
        card.setUrl(url);
        card.setPub_yn(pub);
        card.setJob_state(jobState);
        Files insertFiles = new Files();
        if (files != null) {
			for (MultipartFile file : files) {
				if(file.getSize() == 0L) {
					continue;
				}
				String fileName = file.getOriginalFilename();
				insertFiles.setContent_type(fileName);
				insertFiles.setUpdate_date(new Date());
			}
		}
        
        insertFiles.setPath(filePath);
		service.insert(card, insertFiles);
		
		return "index";
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
