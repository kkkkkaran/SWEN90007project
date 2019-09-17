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

import source.dataSource.MyTutorDatabase;
import source.dataSource.TutorDatabase;
import source.domain.Tutor;

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out = response.getWriter();
		TutorDatabase td = new MyTutorDatabase();
		List<Tutor> tutorList;
		try {
			tutorList = td.listUnapprovedTutors();
			Iterator<Tutor> iterator = tutorList.iterator();
			int i=0;
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title> Approve Tutors </title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<form action=\"ApproveTutors\" method=\"post\">");
			out.println("<table>");
			while(iterator.hasNext()) {
				Tutor t=tutorList.get(i);
				out.println("<tr><td>"+t.getFirstName()+"</td>"+" "+"<td>"+t.getAddress());
				out.println("</td><td><input type=\"checkbox\" name=\"approve\" value=\""+t.getId()+"\"</td> </tr> ");
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
		doGet(request, response);
		TutorDatabase td=new MyTutorDatabase();
		String[] tutorsToApprove = request.getParameterValues("approve");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Deleted Courses </title>");
		out.println("</head>");
		out.println("<body>");
		if(tutorsToApprove.length > 0) {
			
			for(int i=0;i<tutorsToApprove.length;i++) {
				
				Tutor t = td.getTutorAtId(Integer.parseInt(tutorsToApprove[i]));
				t.setApproved(true);
				td.updateTutor(t);
				out.println(t.getFirstName()+" approved");	
				 
			}
		}
		out.println("</body></html>");
	}

}