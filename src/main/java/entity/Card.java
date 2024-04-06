package entity;

import java.util.Date;

public class Card {

	private String card_id;
	private String user_name;
	private Integer age ;
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

	public Card(String id, String title, String userName, Integer age, String phone, String position, String url,
			Date regDate, int hit2, boolean pub, boolean jobState) {
		this.card_id = id;
		this.title = title;
		this.user_name = userName;
		this.age = age;
		this.phone = phone;
		this.position = position;
		this.url = url;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
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

	@Override
	public String toString() {
		return "Card [card_id=" + card_id + ", user_name=" + user_name + ", age=" + age + ", phone=" + phone
				+ ", position=" + position + ", pub_yn=" + pub_yn + ", job_state=" + job_state + ", url=" + url
				+ ", reg_date=" + reg_date + ", hit=" + hit + ", title=" + title + "]";
	}

}