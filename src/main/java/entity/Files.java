package entity;

import java.util.Date;

public class Files {
	
	private String file_card_id;
	private String file_id;
	private String path;
	private String content_type;
	private Date update_date;
	private long file_size;

	public Files() {
	}

	public Files(String fileCardId, String fileId, String contentType, String path, Date updateDate, long fileSize) {
		this.file_card_id = fileCardId; 
		this.file_id = fileId;
		this.content_type = contentType; 
		this.path = path; 
		this.update_date = updateDate;
		this.file_size = fileSize;
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

	public String getFile_card_id() {
		return file_card_id;
	}

	public void setFile_card_id(String file_card_id) {
		this.file_card_id = file_card_id;
	}

	public long getFile_size() {
		return file_size;
	}

	public void setFile_size(long size) {
		this.file_size = size;
	}

	@Override
	public String toString() {
		return "Files [file_card_id=" + file_card_id + ", file_id=" + file_id + ", path=" + path + ", content_type="
				+ content_type + ", update_date=" + update_date + ", file_size=" + file_size + "]";
	}
	
}