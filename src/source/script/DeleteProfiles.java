package source.script;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.dataSource.MyStudentDatabase;
import source.dataSource.MyTutorDatabase;
import source.dataSource.StudentDatabase;
import source.dataSource.TutorDatabase;
import source.domain.Student;
import source.domain.Tutor;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		StudentDatabase sd = new MyStudentDatabase();
		TutorDatabase td = new MyTutorDatabase();
		List<Tutor> tutorList;
		List<Student> studentList;
		try {
			tutorList = td.listAllTutors();
			studentList = sd.listAllStudents();
			
			Iterator<Tutor> iterator = tutorList.iterator();
			Iterator<Student> iteratorS = studentList.iterator();
			int i=0;
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Delete Tutors </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"DeleteProfiles\" method=\"post\">");
			out.println("Tutors");
			out.println("<table>");
			
			while(iterator.hasNext()) {
				Tutor t=tutorList.get(i);
				out.println("<tr><td>"+t.getFirstName()+"</td>"+" "+"<td>"+t.getAddress());
				out.println("</td><td><input type=\"checkbox\" name=\"deleteTutor\" value=\""+t.getId()+"\"</td> </tr> ");
				i++;		
			}
			out.println("</table>");
			
			out.println("Students");
			out.println("<table>");
			
			while(iterator.hasNext()) {
				Student s=studentList.get(i);
				out.println("<tr><td>"+s.getFirstName()+"</td>"+" "+"<td>"+s.getAddress());
				out.println("</td><td><input type=\"checkbox\" name=\"deleteStudent\" value=\""+s.getId()+"\"</td> </tr> ");
				i++;		
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
		TutorDatabase td=new MyTutorDatabase();
		StudentDatabase sd=new MyStudentDatabase();
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
			
			Student s = sd.getStudentAtId(Integer.parseInt(value));
			sd.deleteStudent(s);
			out.println(s.getFirstName()+" deleted");	
			 
		}
		
		out.println("</body></html>");
	}

}
