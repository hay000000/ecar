package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class CipherUtil {
	public String makehash(String plain) throws NoSuchAlgorithmException{
		//plain : 입력된 비밀번호
		 MessageDigest md = MessageDigest.getInstance("SHA-256");
		 byte[] pbyte = plain.getBytes();
		 byte[] hash = md.digest(pbyte); //해쉬암호화 실행
		 return byteToHex(hash);
	}

	private String byteToHex(byte[] hash) {
		if(hash == null) return null;
		String str = "";
		for(byte b : hash) str+= String.format("%02X", b);
		return str;
	}
	

}
