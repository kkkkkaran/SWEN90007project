package source.utils;
import java.util.HashSet;
import java.util.Set;



import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;

import source.domain.*;
import source.services.*;

public class AppRealm extends JdbcRealm{
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
		System.out.println("adminn");
		UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
		final String username = userPassToken.getUsername();
		StudentService ss = new StudentService();
		TutorService tt = new TutorService();
		
		if(username.equals("admin")) {
			System.out.println("adminn2");
			return new SimpleAuthenticationInfo(000,"admin","admin");
		}
		Member m = ss.getStudent(username);
		
		if (m.getUserName() == null) {
			System.out.println("Tutu2");
			m = tt.getTutor(username);
			
			if (m.getUserName() == null) {
				System.out.println("No account found for user with username"+ username);
				return null;
			}
			else {
				System.out.println("Tutu"+m.getFirstName());
			}
		}
		System.out.println("blaj");
		return new SimpleAuthenticationInfo(m.getId(),m.getPassWord(),getName());
		
	}
	@Override
	protected AuthorizationInfo getAuthorizationInfo(PrincipalCollection principals) {
		Set<String> roles = new HashSet<>();
		if(principals.isEmpty()) {
			System.out.println("Given principals to authorise are empty");
			return null;
		}
		int id = (Integer) principals.getPrimaryPrincipal();
		//if admin
		if(id == 000) {
			roles.add(AppSession.ADMIN_ROLE);
			
		}
		//if not admin
		else {
			StudentService ss = new StudentService();
			TutorService tt = new TutorService();
			Member m = ss.getStudentAtId(id);
			if (m.getUserName() == null) {
				m = tt.getTutorAtId(id);
				if (m.getUserName() == null) {
					System.out.println("No account found for user");
					return null;
				}
				else {
					roles.add(AppSession.TUTOR_ROLE);
				}
				
			}
			else {
				roles.add(AppSession.STUDENT_ROLE);
			}
			
		}
		
		return new SimpleAuthorizationInfo(roles);
	}

}
