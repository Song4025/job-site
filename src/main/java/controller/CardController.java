package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller("cardController")
public class CardController {

	private final String filePath = "C:\\fileUpload";
	
	@Autowired
	private ServletContext ctx;
	/*
	 * @RequestMapping("/") public String list() { return "/"; }
	 */

	@GetMapping("/reg")
	public String reg() {
		return "reg";
	}

	@PostMapping("/reg")
	public String reg(String title, String userName, int age,
		 String phone, String url, boolean pub, boolean jobState, MultipartFile[] files) throws IllegalStateException, IOException {

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
