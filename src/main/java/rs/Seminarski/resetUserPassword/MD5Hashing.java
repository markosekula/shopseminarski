package rs.Seminarski.resetUserPassword;

import java.security.MessageDigest;

public class  MD5Hashing {

	public static String getMD5String (String target) {
		
		MessageDigest md;
		
		try {
			
			md = MessageDigest.getInstance("MD5");
			md.update(target.getBytes());
			
			byte byteData[] = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for (int i=0;i<byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}
			
			System.out.println("Digest(in hex format): " + sb.toString());
			return sb.toString();
			
		} catch (Exception e) {
		
			e.printStackTrace();
		}
		return null; 
		
		
		
		
	}
		
	}


