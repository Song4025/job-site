package entity;

import java.util.Date;

public class Card {
	
	private String card_id;
	private String user_name;
	private int age ;
	private String phone ;
	private String position;
	private boolean pub_yn ;
	private boolean job_state ;
	private String url;
	private Date reg_date ;
	private int hit ;
	private String title;
	
	public Card() {
	}

	public Card(String id, String title2, String userName, int age2, String phone2, String position2, String url2,
			Date regDate, int hit2, boolean pub, boolean jobState) {
		this.card_id = id;
		this.title = title2;
		this.user_name = userName;
		this.age = age2;
		this.phone = phone2;
		this.position = position2;
		this.url = url2;
		this.reg_date = regDate;
		this.hit = hit2;
		this.pub_yn = pub;
		this.job_state = jobState;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public boolean isPub_yn() {
		return pub_yn;
	}

	public void setPub_yn(boolean pub_yn) {
		this.pub_yn = pub_yn;
	}

	public boolean isJob_state() {
		return job_state;
	}

	public void setJob_state(boolean job_state) {
		this.job_state = job_state;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	
}