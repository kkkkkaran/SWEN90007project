
package source.script;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import source.domain.Member;
import source.services.StudentInterface;
import source.services.StudentService;
import source.services.TutorInterface;
import source.services.TutorService;
import source.utils.AppSession;


/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter writer = response.getWriter();
		doGet(request, response);
		TutorInterface ts=new TutorService();
		StudentInterface ss=new StudentService();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String type = request.getParameter("type");
		System.out.println(type);
		
		UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
		token.setRememberMe(true);
		Subject currentUser = SecurityUtils.getSubject();
		String view = "/index.jsp";
		Member m;
		try {
			currentUser.login(token);
			if (type.equals("admin")) {
				view = "/dashboard.jsp";
				m = new Member();
				m.setUserName("admin");
				m.setId(000);
			}
			else if (type.equals("tutor")) {
				
				m = ts.getTutor(userName);
				System.out.println("Tutor"+m.getFirstName());
				view = "/welcome.jsp";
			}
			else {
				m = ss.getStudent(userName);
				System.out.println("Student"+m.getFirstName());
				view = "/welcome.jsp";
			}
			AppSession.init(m);
		} catch (UnknownAccountException | IncorrectCredentialsException e) {
			writer.println("username or password does not exit");
		} finally {
			ServletContext servletContext = getServletContext();
			RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(view);
			requestDispatcher.forward(request,response);
		
		}
		
		
		/*
		if (userName.equals("admin") && passWord.equals("admin") && type.equals("admin")) {
			response.sendRedirect("dashboard.jsp");
		} 
		else {
			
			if(type.equals("tutor")) {
				Tutor t=ts.getTutor(userName, passWord);
				if(t.getUserName().equals(null)) {
					writer.println("username or password does not exist");
				}
				else {
					response.sendRedirect("welcome.jsp");
				}
				
			}
			else if(type.equals("student")) {
				Student s = ss.getStudent(userName, passWord);
				if(s.getUserName().equals(null)) {
					writer.println("username or password does not exit");
				}
				else {
					response.sendRedirect("welcome.jsp");
				}
			}
	    }*/
	}

}
