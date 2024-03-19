package entity;

import java.util.Date;

public class Files {
	
	private String file_id;
	private String path;
	private String content_type;
	private Date update_date;
	private int card_id;

	public Files(String file_id, String path, String content_type, Date update_date, int card_id) {
		this.file_id = file_id;
		this.path = path;
		this.content_type = content_type;
		this.update_date = update_date;
		this.card_id = card_id;
	}


	public Files() {
	}


	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getContent_type() {
		return content_type;
	}

	public void setContent_type(String content_type) {
		this.content_type = content_type;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public int getCard_id() {
		return card_id;
	}

	public void setCard_id(int card_id) {
		this.card_id = card_id;
	}
	
}