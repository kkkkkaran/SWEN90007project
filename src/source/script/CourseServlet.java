package source.script;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.domain.Course;
import source.domain.Tutor;
import source.services.CourseService;
import source.services.TutorSubjectService;
import source.utils.AppSession;
import source.services.TutorSubjectInterface;

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
		
		CourseService cs=new CourseService();
		
		List<Course> courses;
		try {
			courses = cs.listCourses();

			PrintWriter out = response.getWriter();
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title> View Course List </title>");
			out.println("</head>");
			if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.STUDENT_ROLE)) {
			out.println("<body>");
			out.println("<form action=\"CourseServlet\" method=\"post\">");
			out.println("<table>");
			
			for(int i=0;i<courses.size();i++) {
				Course c=courses.get(i);
				out.println("<tr><td>"+c.getCourseName()+"</td><td><input type=\"radio\" name=\"subjectSelection\" value=\""+c.getCourseId()+"\"></td></tr> ");
					
			}
	        out.println("<tr><td><input type=\"submit\" value=\"Go\"></td><td><a href=\"homepage.jsp\">HomePage</a></td></tr>");
	        out.println("</table>");
	        out.println("</form>");
			}
			else {
				out.println("You are not authorised as a student to view this page.");
				out.println("<a href=\"homepage.jsp\">Go Home</a>");
			}
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
		TutorSubjectInterface tss = new TutorSubjectService();
		int courseId = Integer.parseInt(request.getParameter("subjectSelection"));
		List<Tutor> tutorList = tss.getTutorForCourse(courseId);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Select Tutor </title>");
		out.println("</head>");
		out.println("<body>");
		if(tutorList.size()>0) {
		out.println("<form action = \"ViewAvailability\">");
		out.println("<table>");
		out.println("<tr><th>Tutor Name</th><th>Tutor Location</th><th>Selection</th></tr>");
		for(int i=0;i<tutorList.size();i++) {
			Tutor t=tutorList.get(i);
			
			out.println("<tr><td>"+t.getFirstName()+"</td>"+" "+"<td>"+t.getAddress()+"<td><input type = \"radio\" name = \"selection\" value = \""+t.getId()+"\"></tr> ");	
		}
		
		out.println("</table>");
		out.println("<input type = \"submit\" value = \"View Availability\">");
		}
		else {
			out.println("No tutors have registered for this course");
		}
		out.println("</body></html>");
        
		
			

		
	}

}
