package logic;

import java.util.Date;

public class Comment {
private int comnum;
private String content;
private Date up;
private String pw;
private int cnt;
private String id;//닉네임
private int btype;
private String man;
public int getComnum() {
	return comnum;
}
public void setComnum(int comnum) {
	this.comnum = comnum;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public Date getUp() {
	return up;
}
public void setUp(Date up) {
	this.up = up;
}
public String getPw() {
	return pw;
}
public void setPw(String pw) {
	this.pw = pw;
}
public int getCnt() {
	return cnt;
}
public void setCnt(int cnt) {
	this.cnt = cnt;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public int getBtype() {
	return btype;
}
public void setBtype(int btype) {
	this.btype = btype;
}
public String getMan() {
	return man;
}
public void setMan(String man) {
	this.man = man;
}
@Override
public String toString() {
	return "Comment [comnum=" + comnum + ", content=" + content + ", up=" + up + ", pw=" + pw + ", cnt=" + cnt + ", id="
			+ id + ", btype=" + btype + ", man=" + man + "]";
}

}
