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

import source.domain.Course;
import source.domain.Tutor;
import source.services.CourseGateway;
import source.services.MyTutorSubjectDatabase;
import source.services.TutorSubjectDatabase;

/**
 * Servlet implementation class RegisterServlet
 */

public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		CourseGateway cg=new CourseGateway();
		
		List<Course> courses;
		try {
			courses = cg.listCourses();

			PrintWriter out = response.getWriter();
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title> View Course List </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"CourseServlet\" method=\"post\">");
			out.println("<table>");
			Iterator<Course> iterator = courses.iterator();
			int i=0;
			while(iterator.hasNext()) {
				Course c=courses.get(i);
				out.println("<tr><td>"+c.getCourseName()+"<input type=\"radio\" name=\"subjectSelection\" value=\""+c.getCourseId()+"\"></td></tr> ");
				i++;		
			}
	        out.println("<tr><td><input type=\"submit\" value=\"Go\"></td><td><a href=\"homepage.jsp\">HomePage</a></td></tr>");
	        out.println("</table>");
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
		PrintWriter out = response.getWriter();
		TutorSubjectDatabase tsd = new MyTutorSubjectDatabase();
		int courseId = Integer.parseInt(request.getParameter("subjectSelection"));
		List<Tutor> tutorList = tsd.getTutorForCourse(courseId);
		Iterator<Tutor> iterator = tutorList.iterator();
		int i=0;
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> View Course List </title>");
		out.println("</head>");
		out.println("<body>");

		out.println("<table>");
		while(iterator.hasNext()) {
			Tutor t=tutorList.get(i);
			out.println("<tr><td>"+t.getFirstName()+"</td>"+" "+"<td>"+t.getAddress()+"</tr> ");
			i++;		
		}
		out.println("</table>");
		out.println("</body></html>");
        
		
			

		
	}

}
