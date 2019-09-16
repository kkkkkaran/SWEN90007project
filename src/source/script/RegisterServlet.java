package source.script;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.dataSource.MyStudentDatabase;
import source.dataSource.MyTutorDatabase;
import source.dataSource.StudentDatabase;
import source.dataSource.TutorDatabase;
import source.domain.Student;

/**
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
		
		StudentDatabase sd = new MyStudentDatabase();
		TutorDatabase td = new MyTutorDatabase();
		
		String userName = request.getParameter("userName");
		String passWord1 = request.getParameter("passWord1");
		String passWord2 = request.getParameter("passWord2");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String education = request.getParameter("education");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		
		if(type=="tutor") {
			String[] subjects = request.getParameterValues("subjects");
			String price = request.getParameter("price");
			
			Tutor t = new Tutor();
			if (passWord1.equals(passWord2)) {
				t.setUserName(userName);
				t.setPassWord(passWord1);
				t.setFirstName(fname);
				t.setLastName(lname);
				t.setDateOfBirth(dob);
				t.setAddress(address);
				t.setSubjects(subjects);
				t.setPrice(price);
				td.insertTutor(t);
				writer.println("Welcome, please login");
				response.sendRedirect("login.jsp");
			} else{
				writer.println("password does not match");
			}
		}
		else {
			Student s = new Student();
			if (passWord1.equals(passWord2)) {
				s.setUserName(userName);
				s.setPassWord(passWord1);
				s.setFirstName(fname);
				s.setLastName(lname);
				s.setDateOfBirth(dob);
				s.setEducation(education);
				sd.insertStudent(s);
				writer.println("Welcome, please login");
				response.sendRedirect("login.jsp");
			} else{
				writer.println("password does not match");
			}
		}


		
	}

}