
package source.script;
import java.io.IOException;

import source.dataSource.MyStudentDatabase;
import source.dataSource.MyTutorDatabase;
import source.dataSource.StudentDatabase;
import source.dataSource.TutorDatabase;
import source.domain.*;
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
		
		int userType=0; //insert credentials for a registered user. Change user type to 1 for tutor profile
		String username="admin"; 
		String password="admin";
		Member s;
		Tutor t;
		MyStudentDatabase ms=new MyStudentDatabase();
		MyTutorDatabase mt=new MyTutorDatabase();
		
		if (userType == 0){
			s = ms.getStudent(username,password);
		}
		else {
			s = mt.getTutor(username,password);
		}
		PrintWriter out = response.getWriter();
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> View Profile </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<form action=\"ViewProfile\" method=\"post\">");
		out.println("<table>");
		out.println("input type=\"hidden\" name=\"type\" value=\""+userType+"\"");
		out.println("<tr><td>First Name :</td><td><input type=\"text\" name=\"fname\" value=\""+ s.getFirstName()+"\"></td></tr>");
		out.println("<tr><td>Last Name :</td><td><input type=\"text\" name=\"lname\" value=\""+ s.getLastName()+"\"></td></tr>");
		out.println("<tr><td>Date of Birth (DD/MM/YYYY):</td><td><input type=\"text\" name=\"dob\" value=\""+ s.getFirstName()+"\"></td></tr>");
		out.println("<tr><td>Education :</td><td><input type=\"text\" name=\"education\" value=\""+ s.getDateOfBirth()+"\"></td></tr>");
		out.println("<tr><td>Address :</td><td><input type=\"text\" name=\"address\" value=\""+ s.getAddress()+"\"></td></tr>");
		if(userType == 1) { //printing tutor specific fields
			t = mt.getTutor(username,password);
			String[] subjectList = t.getSubjects();
			String subjects = String.join(",", subjectList);
			out.println("<tr> <td>Subject List :</td> <td><input type=\"text\" name=\"subjects\" id=\"subjects\" value=\""+subjects+"\" disabled></td> </tr>");
			out.println("<tr><td>Rate per hour :</td><td><input type=\"text\" name=\"price\" id=\"price\" value=\""+t.getPrice()+"\"></td></tr>");	
		}
		out.println("<tr><td>UserName :</td> <td><input type=\"text\" name=\"userName\" value=\""+ s.getUserName()+"\" disabled></td> </tr>");
		out.println(" <tr> <td>Password :</td><td><input type=\"password\" name=\"passWord1\" value=\""+ s.getPassWord()+"\"></td></tr>");
        out.println("<tr><td><input type=\"submit\" value=\"Register\"></td><td><a href=\"homepage.jsp\">HomePage</a></td></tr>");
        out.println("</table>");
        out.println("</form>");
	
		
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
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String education = request.getParameter("education");
		String address = request.getParameter("address");
		String type = request.getParameter("type");
		
		if(type=="tutor") {
			String[] subjects = request.getParameterValues("subjects");
			String price = request.getParameter("price");
			
			Tutor t = td.getTutor(userName, passWord1);
			
			t.setUserName(userName);
			t.setPassWord(passWord1);
			t.setFirstName(fname);
			t.setLastName(lname);
			t.setDateOfBirth(dob);
			t.setAddress(address);
			t.setSubjects(subjects);
			t.setPrice(price);
			td.updateTutor(t);
			writer.println("Done");
			
			
		}
		else {
			Student s = sd.getStudent(userName, passWord1);
			
			s.setUserName(userName);
			s.setPassWord(passWord1);
			s.setFirstName(fname);
			s.setLastName(lname);
			s.setDateOfBirth(dob);
			s.setEducation(education);
			sd.updateStudent(s);
			writer.println("Done");
				
			
		}
	}

}
