package entity;

import java.util.Date;

public class Card {
	
	private String card_id;
	private String user_name;
	private int age ;
	private int phone ;
	private String position;
	private char pub_yn ;
	private String job_state ;
	private String url;
	private Date reg_date ;
	private int hit ;
	private String title;
	
	public Card(String card_id, String user_name, int age, int phone, String position, char pub_yn, String job_state,
			String url, Date reg_date, int hit, String title) {
		this.card_id = card_id;
		this.user_name = user_name;
		this.age = age;
		this.phone = phone;
		this.position = position;
		this.pub_yn = pub_yn;
		this.job_state = job_state;
		this.url = url;
		this.reg_date = reg_date;
		this.hit = hit;
		this.title = title;
	}

	public Card(String id, String title2, String userName, int age2, int phone2, String position2, String url2,
			Date regDate, int hit2, char pub, String jobState) {
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public char getPub_yn() {
		return pub_yn;
	}

	public void setPub_yn(char pub_yn) {
		this.pub_yn = pub_yn;
	}

	public String getJob_state() {
		return job_state;
	}

	public void setJob_state(String job_state) {
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