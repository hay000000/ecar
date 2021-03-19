package aes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CipherMain4 {

	public static void main(String[] args) throws Exception {
		String url = "jdbc:mariadb://localhost:3306/classdb";
		String sql = "SELECT email,userid FROM usersecurity";
		Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "scott", "1234");
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			String email = rs.getString(1);
			String userid = rs.getString(2);
			String key = CipherUtil.makehash(userid).substring(0,16);
			String hashemail =CipherUtil.decrypte(email,key);
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
