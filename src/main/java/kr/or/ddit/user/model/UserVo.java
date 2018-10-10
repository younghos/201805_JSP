package kr.or.ddit.user.model;

import java.util.Date;

public class UserVo {
	private String userId; // 회원아이디
	private String name; // 회원이름
	private String pass; // 회원비번
	private String alias; // 회원별명
	private Date birth; // 회원생일
	private String addr1; // 회원주소
	private String addr2; // 회원상세주소
	private String zip; // 회원우편번호
	private String email; // 회원이메일
	private String tel; // 회원전화번호
	private String profile; // 회원프로필경로
	
	public UserVo() {
		super();
	}
	
	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "UserVo [userId=" + userId + ", name=" + name + ", alias="
				+ alias + ", birth=" + birth + ", pass=" + pass + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", zip=" + zip + ", email="
				+ email + ", tel=" + tel + ", profile=" + profile + "]";
	}
	
}
