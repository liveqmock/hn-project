package hn.travel.persist.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




/**
 * 
 * @author luzq	
 * 新浪微博读写cookie
 *
 */
public class SinaUtils {

	public static void writeSinaToken(HttpServletRequest request,
			HttpServletResponse response, String key, String value) {
		Cookie u = new Cookie(key, value);
		u.setPath("/");
		u.setMaxAge(-1);
		u.setDomain("wofs.com.cn");
		response.addCookie(u);

	}
	
	
	public static void clearSinaToken(HttpServletRequest request,
			HttpServletResponse response, String key) {
		Cookie u = new Cookie(key, "");
		u.setPath("/");
		u.setMaxAge(0);
		u.setDomain("wofs.com.cn");
		response.addCookie(u);
		
		Cookie n = new Cookie("adShareId", "");
		n.setPath("/");
		n.setMaxAge(0);
		n.setDomain("ta.sass.sina.com.cn");
		response.addCookie(n);

	}
	
	public static String getSinaToken(HttpServletRequest request,
			HttpServletResponse response, String key){
		Cookie[] cookies=request.getCookies();
		String userToken = "";
		if (cookies != null && cookies.length >= 1) {	
			for (Cookie c : cookies) {
				if (c.getName().equals(key)) {
					userToken = c.getValue();
					continue;
				}
				
			}
		}
		return userToken;
	}
	
	
	
	
	

}
