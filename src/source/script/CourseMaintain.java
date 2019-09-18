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
import source.services.CourseService;

/**
 * Servlet implementation class CourseMaintain
 */

public class CourseMaintain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseMaintain() {
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
			out.println("<title> Maintain Courses </title>");
			out.println("</head>");
			out.println("<body>");
			
			out.println("<form action=\"CourseAdd\" method=\"post\">");
			out.println("<table>");
			out.println("<tr><td>Add Course:</td><td><input type=\"text\" name=\"subjectToAdd\"></td></tr>");
			out.println("<tr><td><input type=\"submit\" value=\"Go\"></td></tr>");
			out.println("</table>");
	        out.println("</form>");
	        
			out.println("<form action=\"CourseMaintain\" method=\"post\">");
			out.println("<table>");
			out.println("<tr><td>Delete Courses</td></tr>");
			Iterator<Course> iterator = courses.iterator();
			int i=0;
			while(iterator.hasNext()) {
				Course c=courses.get(i);
				out.println("<tr><td>"+c.getCourseName()+"<input type=\"checkbox\" name=\"subjectSelection\" value=\""+c.getCourseName()+"\"></td></tr> ");
				i++;		
			}
	        out.println("<tr><td><input type=\"submit\" value=\"Go\"></td></tr>");
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
		doGet(request, response);
		CourseService cs=new CourseService();
		String[] coursesToDelete = request.getParameterValues("subjectSelection");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Deleted Courses </title>");
		out.println("</head>");
		out.println("<body>");
		if(coursesToDelete.length > 0) {
			
			for(int i=0;i<coursesToDelete.length;i++) {
				try {
					cs.delete(coursesToDelete[i]);
					out.println(coursesToDelete[i]+" deleted");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					out.println(coursesToDelete[i]+" delete unsuccessful");
					e.printStackTrace();
				}
			}
		}
		out.println("</body></html>");
		
	}

}
