package source.script;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import source.services.TutorSubjectService;
import source.utils.AppSession;
/**
 * Servlet implementation class AddTutorCourse
 */

public class AddTutorCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTutorCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] courses = request.getParameterValues("coursesAdd");
		TutorSubjectService tss = new TutorSubjectService();
		for (int i=0;i<courses.length;i++) {
			int courseId = Integer.parseInt(courses[i]);
			tss.insertSubject(AppSession.getUser().getId(), courseId);
			
		}
		response.sendRedirect(request.getContextPath() + "/welcome.jsp");
		
	}

}
