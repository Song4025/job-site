package entity;

import java.util.Date;

public class Card {
	
	private String card_id;
	private String user_name;
	private int age ;
	private String phone ;
	private String position;
	private char pub_yn ;
	private String job_state ;
	private String url;
	private Date reg_date ;
	private int hit ;
	private String title;
	
	public Card(String cardId, String userName, int age, String phone, String position, char pub_yn, String jobState,
			String url, Date reg_date, int hit, String title) {
		this.card_id = cardId;
		this.user_name = userName;
		this.age = age;
		this.phone = phone;
		this.position = position;
		this.pub_yn = pub_yn;
		this.job_state = jobState;
		this.url = url;
		this.reg_date = reg_date;
		this.hit = hit;
		this.title = title;
	}

	public Card(String id, String title2, String userName, int age2, String phone2, String position2, String url2,
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

	public void setCard_id(String cardId) {
		this.card_id = cardId;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String userName) {
		this.user_name = userName;
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