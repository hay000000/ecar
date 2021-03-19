package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * db useraccount => usersecurity 테이블로 모든 내용 저장.
 * create table usersecurity as select * from useraccount
 *
 * 프로그램 작성
 * useraccount 테이블을 읽어서 usersecurity테이블의 
 * password컬럼을 SHA256 알고리즘을 이용하여 해쉬값으로 수정
 */
public class DigestMain2 {
	public static void main(String[] args) throws NoSuchAlgorithmException, ClassNotFoundException, SQLException {
		String url = "jdbc:mariadb://localhost:3306/classdb";
		String sql = "SELECT userid,password FROM useraccount";
		Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "scott", "1234");
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		try {
			while (rs.next()) {
				String hashpass = "";
				String pass = rs.getString("password");
				String userid = rs.getString("userid");
				String sql1 = " update usersecurity set password = ? where userid = ?";
				MessageDigest md = MessageDigest.getInstance("SHA-256");
				byte[] hash = md.digest(pass.getBytes());
				for (byte b : hash) {
					hashpass += String.format("%02X", b);
				}
				pstmt.close();
				pstmt = con.prepareStatement(sql1);
				pstmt.setString(1, hashpass);
				pstmt.setString(2, userid);
				pstmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
