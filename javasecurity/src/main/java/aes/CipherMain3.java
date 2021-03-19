package aes;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CipherMain3 {

/*
 * useraccount 테이블의 email 값을 읽어서 usersecurity 테이블의 email을 암호화 하기.
 * 	1.email 컬럼의 크기 300으로 변경하기
 * 	2.key 는 userid의 (SHA-256)해쉬값의 문자열 앞 16자리로 정한다.
 */
	public static void main(String[] args) throws Exception {
		String url = "jdbc:mariadb://localhost:3306/classdb";
		String sql = "SELECT email,userid FROM useraccount";
		Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "scott", "1234");
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String email = rs.getString(1);
			String userid = rs.getString(2);
			String key = CipherUtil.makehash(userid).substring(0,16);
			String hashemail =CipherUtil.encrypte(email,key);
			System.out.println(email);
			System.out.println(hashemail);
			pstmt.close();
			pstmt = con.prepareStatement("update usersecurity set email = ? where userid = ?");
			pstmt.setString(1, hashemail);
			pstmt.setString(2, userid);
			pstmt.executeLargeUpdate();
		}

	}

}
