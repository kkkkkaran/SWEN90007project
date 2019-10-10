package source.script;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.domain.Tutor;
import source.services.TutorService;
import source.services.TutorInterface;

/**
 * Servlet implementation class ApproveTutors
 */

public class ApproveTutors extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveTutors() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		TutorInterface ti = new TutorService();
		List<Tutor> tutorList;
		try {
			tutorList = ti.listUnapprovedTutors();
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Approve Tutors </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"ApproveTutors\" method=\"post\">");
			out.println("<table>");
			/* Test Data 
			Tutor tt=new Tutor();
			tt.setFirstName("abcd");
			tt.setAddress("defg");
			tt.setId(1234);
			tutorList.add(tt);
			*/
			for(int i=0;i<tutorList.size();i++) {
				Tutor t=tutorList.get(i);
				if(!t.getFirstName().isEmpty()) {
					out.println("<tr><td>"+t.getFirstName()+"</td>"+" "+"<td>"+t.getAddress());
					out.println("</td><td><input type=\"checkbox\" name=\"approve\" value=\""+t.getId()+"\"</td> </tr> ");
				}
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
		TutorInterface ti=new TutorService();
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Approved </title>");
		out.println("</head>");
		out.println("<body>");
		if (request.getParameterMap().containsKey("approve")) {
			String[] tutorsToApprove = request.getParameterValues("approve");
			if(tutorsToApprove.length > 0) {
				for(int i=0;i<tutorsToApprove.length;i++) {
					int a = Integer.parseInt(tutorsToApprove[i]);
					System.out.println(a);
					ti.approveTutor(a);
					out.println("approved");	
					 
				}
			}
		}
		out.println("</body></html>");
	}

}
