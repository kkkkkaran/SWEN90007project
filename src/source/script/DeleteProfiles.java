package source.script;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.domain.Student;
import source.domain.Tutor;
import source.services.StudentService;
import source.services.TutorService;
import source.services.StudentInterface;
import source.services.TutorInterface;

/**
 * Servlet implementation class DeleteProfiles
 */

public class DeleteProfiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProfiles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		StudentInterface ss = new StudentService();
		TutorInterface td = new TutorService();
		List<Tutor> tutorList;
		List<Student> studentList;
		try {
			tutorList = td.listAllTutors();
			studentList = ss.listAllStudents();

			
	
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Delete Tutors </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"DeleteProfiles\" method=\"post\">");
			out.println("Tutors");
			out.println("<table>");
			/* Test Data
			Tutor t=new Tutor();
			t.setFirstName("abcd");
			t.setAddress("defg");
			t.setId(1234);
			tutorList.add(t);
			*/
			for(int k=0;k<tutorList.size();k++) {
				Tutor tt = tutorList.get(k);
				out.println("<tr><td>"+tt.getFirstName()+"</td>"+" "+"<td>"+tt.getAddress());
				out.println("</td><td><input type=\"checkbox\" name=\"deleteTutor\" value=\""+tt.getId()+"\"</td> </tr> ");
				
			}
			out.println("</table>");
			
			out.println("Students");
			out.println("<table>");
			
			/* Test Data
			Student s=new Student();
			s.setFirstName("abcd");
			s.setAddress("defg");
			s.setId(1234);
			studentList.add(s);
			*/
			for(int j=0;j<studentList.size();j++) {
				Student sss=studentList.get(j);
				out.println("<tr><td>"+sss.getFirstName()+"</td>"+" "+"<td>"+sss.getAddress());
				out.println("</td><td><input type=\"checkbox\" name=\"deleteStudent\" value=\""+sss.getId()+"\"</td> </tr> ");
					
			}
			out.println("</table>");
			
		 	out.println("<tr><td><input type=\"submit\" value=\"Go\"></td></tr>");
	        
	        out.println("</form>");
	        out.println("</body></html>");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		doGet(request, response);
		doGet(request, response);
		TutorInterface td=new TutorService();
		StudentInterface ss=new StudentService();
		String[] tutorsToDelete = request.getParameterValues("deleteTutor");
		String[] studentsToDelete = request.getParameterValues("deleteStudent");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Deleted Profiles </title>");
		out.println("</head>");
		out.println("<body>");
		
			
		for(String value : tutorsToDelete) {
			
			Tutor t = td.getTutorAtId(Integer.parseInt(value));
			td.deleteTutor(t);
			out.println(t.getFirstName()+" deleted");	
			 
		}
		
		
		
		for(String value : studentsToDelete) {
			
			Student s = ss.getStudentAtId(Integer.parseInt(value));
			ss.deleteStudent(s);
			out.println(s.getFirstName()+" deleted");	
			 
		}
		
		out.println("</body></html>");
	}

}
