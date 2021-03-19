package logic;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class User {
	@Size(min=3,max=10,message="���̵�� 3���̻� 10�� ���Ϸ� �Է��ϼ���")
	private String userid;
	@Size(min=3,max=10,message="��й�ȣ�� 3���̻� 10�� ���Ϸ� �Է��ϼ���")
	private String password;
	@NotEmpty(message="������̸��� �ʼ� �Դϴ�.")
	private String username;
	private String phoneno;
	private String postcode;
	private String address;
	@NotEmpty(message="email�� �Է��ϼ���")
	@Email(message="email �������� �Է��ϼ���")
	private String email;
	@NotNull(message="������ �Է��ϼ���")
	@Past(message="������ ���� ��¥�� �����մϴ�.")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthday;
	
	//getter,setter,toString
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", password=" + password + ", username=" + username + ", phoneno=" + phoneno
				+ ", postcode=" + postcode + ", address=" + address + ", email=" + email + ", birthday=" + birthday
				+ "]";
	}
	
}
