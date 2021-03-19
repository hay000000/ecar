package logic;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	@NotEmpty(message="제목을 입력하세요")
	private String title;
	@NotEmpty(message="내용을 입력하세요")
	private String con;
	@NotEmpty(message="비밀번호를 입력하세요")
	private String pw;
	private int btype;
	private MultipartFile file1;
	private String fileurl;
	private Date date;
	@NotEmpty(message="차종을 선택해주세요")
	private String man;
	@NotEmpty(message="글쓴이를 입력하세요")
	private String id;
	private int cnt;
	private int readcnt;
	

	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getCon() {
		return con;
	}


	public void setCon(String con) {
		this.con = con;
	}


	public String getPw() {
		return pw;
	}


	public void setPw(String pw) {
		this.pw = pw;
	}


	public int getBtype() {
		return btype;
	}


	public void setBtype(int btype) {
		this.btype = btype;
	}


	public MultipartFile getFile1() {
		return file1;
	}


	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}


	public String getFileurl() {
		return fileurl;
	}


	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getMan() {
		return man;
	}


	public void setMan(String man) {
		this.man = man;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public int getCnt() {
		return cnt;
	}


	public void setCnt(int cnt) {
		this.cnt = cnt;
	}


	public int getReadcnt() {
		return readcnt;
	}


	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}


	@Override
	public String toString() {
		return "Board [title=" + title + ", con=" + con + ", pw=" + pw + ", btype=" + btype + ", file1=" + file1
				+ ", fileurl=" + fileurl + ", date=" + date + ", man=" + man + ", id=" + id + ", cnt=" + cnt
				+ ", readcnt=" + readcnt + "]";
	}

}