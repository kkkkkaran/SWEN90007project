package source.utils;
import source.domain.Member;
import org.apache.shiro.SecurityUtils;



public class AppSession {
	public static final String USER_ATTRIBUTE_NAME = "user";
	public static final String TUTOR_ROLE = "tutor";
	public static final String STUDENT_ROLE = "student";
	public static final String ADMIN_ROLE = "admin";
	
	
	
	
	
	
	public static boolean hasRole(String role) {
		return SecurityUtils.getSubject().hasRole(role);
	}
	public static boolean isAuthenticated() {
		return SecurityUtils.getSubject().isAuthenticated();
	}
	public static void init(Member m) {
		SecurityUtils.getSubject().getSession().setAttribute(USER_ATTRIBUTE_NAME,m);
	}
	public static Member getUser() {
		return (Member) SecurityUtils.getSubject().getSession().getAttribute(USER_ATTRIBUTE_NAME);
	}
	
	public static void logout() {
		SecurityUtils.getSubject().logout();
	}

}
