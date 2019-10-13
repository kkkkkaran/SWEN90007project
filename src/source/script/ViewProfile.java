
package source.script;
import java.io.IOException;

import source.domain.*;
import source.services.StudentService;
import source.services.TutorService;
import source.utils.AppSession;
import source.services.StudentInterface;
import source.services.TutorInterface;

import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewProfile
 */

public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @return 
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> View Profile </title>");
		out.println("</head>");
		out.println("<body>");
		if (AppSession.isAuthenticated() && !AppSession.hasRole(AppSession.ADMIN_ROLE))  {
			int userType=0; //insert credentials for a registered user. Change user type to 1 for tutor profile
			Tutor t;
			TutorService mt=new TutorService();
			StudentService ss = new StudentService();
			Member s;
			if(AppSession.hasRole(AppSession.TUTOR_ROLE)) {
				System.out.println("Tutor Role");
				s = mt.lazyLoadedTutor((Tutor)AppSession.getUser());
			}
			else {
				s = ss.lazyLoadedStudent((Student)AppSession.getUser());
			}
			
			out.println("<form action=\"ViewProfileServlet\" method=\"post\">");
			out.println("<table>");
			out.println("<input type=\"hidden\" name=\"type\" value=\""+userType+"\">");
			out.println("<tr><td>First Name :</td><td><input type=\"text\" name=\"fname\" value=\""+s.getFirstName()+"\"></td></tr>");
			out.println("<tr><td>Last Name :</td><td><input type=\"text\" name=\"lname\" value=\""+s.getLastName()+"\"></td></tr>");
			out.println("<tr><td>Year of Birth (YYYY):</td><td><input type=\"text\" name=\"dob\" value=\""+ s.getFirstName()+"\"></td></tr>");
			out.println("<tr><td>Education :</td><td><input type=\"text\" name=\"education\" value=\""+ s.getDateOfBirth()+"\"></td></tr>");
			
			if(AppSession.hasRole(AppSession.TUTOR_ROLE)) { //printing tutor specific fields
				t = mt.getTutor(s.getUserName());
				/*String[] subjectList = t.getSubjects();
				String subjects = String.join(",", subjectList);
				out.println("<tr> <td>Subject List :</td> <td><input type=\"text\" name=\"subjects\" id=\"subjects\" value=\""+subjects+"\" disabled></td> </tr>);
				*/
				out.println("<tr><td>Location :</td><td><input type=\"text\" name=\"address\" value=\""+ t.getAddress()+"\"></td></tr>");
				out.println("<tr><td>Rate per hour :</td><td><input type=\"text\" name=\"price\" id=\"price\" value=\""+t.getPrice()+"\"></td></tr>");	
			}
			out.println("<tr><td>UserName :</td> <td><input type=\"text\" name=\"userName\" value=\""+ s.getUserName()+"\" disabled></td> </tr>");
			out.println(" <tr> <td>Password :</td><td><input type=\"password\" name=\"passWord1\" value=\""+ s.getPassWord()+"\"></td></tr>");
	        out.println("<tr><td><input type=\"submit\" value=\"Update\"></td><td><a href=\"welcome.jsp\">HomePage</a></td></tr>");
	        out.println("</table>");
	        out.println("</form>");
	
		}
		else {
			out.println("You are not authorised to view this page.");
		}
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
	
		
		StudentInterface sd = new StudentService();
		TutorInterface td = new TutorService();
		
		String userName = request.getParameter("userName");
		String passWord1 = request.getParameter("passWord1");
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String education = request.getParameter("education");
		String address = request.getParameter("address");
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> View Profile </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("yoyo");
		if(AppSession.hasRole(AppSession.TUTOR_ROLE)) {
			String price = request.getParameter("price");
			
			Tutor t = td.getTutor(userName);
			
			t.setUserName(userName);
			t.setPassWord(passWord1);
			t.setFirstName(fname);
			t.setLastName(lname);
			t.setDateOfBirth(dob);
			t.setAddress(address);
			t.setPrice(price);
			td.updateTutor(t);
			out.println("Done");
			doGet(request,response);
			
		}
		else {
			Student s = sd.getStudent(userName);
			
			s.setUserName(userName);
			s.setPassWord(passWord1);
			s.setFirstName(fname);
			s.setLastName(lname);
			s.setDateOfBirth(dob);
			s.setEducation(education);
			sd.updateStudent(s);
			out.println("Done");
			doGet(request,response);	
			
		}
		out.println("</body>");
		out.println("</html>");
	}

}
