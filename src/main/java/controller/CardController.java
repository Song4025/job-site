package controller;

import java.io.File; 
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	public synchronized String reg(String title, String userName, int age,
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
        
        // 파일정보 담을 리스트 생성
        List<Files> insertFilesList = new ArrayList<>();
        
        if(files != null && files.length > 0) {
			for (MultipartFile file : files) {
				if(file.getSize() == 0L) {
					continue;
				}
				long size = file.getSize();
				String fileName = file.getOriginalFilename();
				Files insertFiles = new Files();
				insertFiles.setContent_type(fileName);
				insertFiles.setPath(filePath);
				insertFiles.setUpdate_date(new Date());
				insertFiles.setFile_size(size);
				insertFilesList.add(insertFiles);
			}
        }
        
		service.insert(card, insertFilesList);
		return "redirect:/index";
	}

	@RequestMapping("update")
	public synchronized String update(String upCardId, String upTitle, String upUserName, Integer upAge, String upPhone, String upPosition, String upUrl, boolean upPub, boolean upJobState, MultipartFile[] upFiles, String[] filesToDelete) throws ClassNotFoundException, SQLException, IllegalStateException, IOException {
		List<Files> updateFilesList = new ArrayList<>();
		if (upFiles != null) {
			for (MultipartFile file : upFiles) {
				if(file.getSize() == 0L) {
					continue;
				}
				//파일저장 로직
				String fileName = file.getOriginalFilename();
				long size = file.getSize();
				System.out.printf("fileName: %s, fileSize: %d\n", fileName, size);
				File savePath = new File(filePath);
				
				// 경로가 존재하지않으면 경로만들어주기
				if (!savePath.exists())
					savePath.mkdirs();
				File saveFile = new File(filePath + File.separator + fileName);
				file.transferTo(saveFile);
				
				Files fileObj = new Files();
				fileObj.setContent_type(file.getContentType());
	            fileObj.setUpdate_date(new Date());
	            fileObj.setPath(filePath);
	            fileObj.setFile_size(size);
	            updateFilesList.add(fileObj);
			}
		}
		
		// 파일 삭제 로직 추가
	    if (filesToDelete != null) {
	        for (String fileId : filesToDelete) {
	            // 파일 삭제
	            service.deleteFile(fileId); // 파일 삭제를 처리하는 메서드 호출
	        }
	    }
		
		// Card 객체 생성 및 값 설정
        Card card = new Card();
        card.setCard_id(upCardId);
        card.setTitle(upTitle);
        card.setUser_name(upUserName);
        card.setAge(upAge);
        card.setPhone(upPhone);
        card.setPosition(upPosition);
        card.setUrl(upUrl);
        card.setPub_yn(upPub);
        card.setJob_state(upJobState);

		service.update(card, updateFilesList);
		
		return "redirect:/index";
	}

	@RequestMapping("delete")
	public synchronized String delete(String upCardId) throws ClassNotFoundException, SQLException {
		Card card = new Card();
        card.setCard_id(upCardId);
        service.delete(card);

        return "redirect:/index";
	}

}
