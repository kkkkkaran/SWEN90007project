
package source.script;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.domain.Student;
import source.domain.Tutor;
import source.services.StudentInterface;
import source.services.StudentService;
import source.services.TutorInterface;
import source.services.TutorService;


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
		
		if (userName.equals("admin") && passWord.equals("admin") && type.equals("admin")) {
			response.sendRedirect("dashboard.jsp");
		} 
		else {
			if(type.equals("tutor")) {
				Tutor t=ts.getTutor(userName, passWord);
				if(t.getUserName().equals(null)) {
					writer.println("username or password does not exit");
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
	    }
	}

}
