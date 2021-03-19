package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
 * 화면에서 아이디와 비밀번호를 입력받아서
 * 해당 아이디가 usersecurity 테이블에없으면 "아이디 확인" 출력
 * 해당 아이디의 비밀번호를 비교해서 맞으면 "반갑습니다. 홍길동님" 출력
 * 해당 아이디의 비밀번호를 비교해서 틀리면 "비밀번호 확인" 출력
 */
public class DigestMain3 {
	public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException {
		String url = "jdbc:mariadb://localhost:3306/classdb";
		Class.forName("org.mariadb.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, "scott", "1234");
		Scanner scan = new Scanner(System.in);
		while (true) {
		System.out.println("아이디를 입력해주세요");
		String idcheck = scan.next();
		System.out.println("비밀번호를 입력해주세요");
		String passcheck = scan.next();

		PreparedStatement pstmt = con
				.prepareStatement("select userid,password,username from usersecurity where userid = ?");
		pstmt.setString(1, idcheck);

		ResultSet rs = pstmt.executeQuery(); //db문장 실행
		String hashpass = "";
		MessageDigest md = MessageDigest.getInstance("SHA-256");
			if (rs.next()) {
				String userid = rs.getString(1);
				String pass = rs.getString(2);
				String username = rs.getString(3);
				
				byte[] hash = md.digest(passcheck.getBytes());
				for (byte b : hash) {
					hashpass += String.format("%02X", b);
				}
				if (!userid.equals(idcheck)) {
					System.out.println("아이디 확인");
				} else if (!hashpass.equals(pass)) {
					System.out.println("비밀번호 확인");
				}
				if (hashpass.equals(pass) && userid.equals(idcheck)) {
					System.out.println(username + "님 환영합니다.");
					break;
				}
			}
		}
	}

}
