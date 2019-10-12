package source.script;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import source.domain.TutorAvailability;
import source.services.TutorAvailabilityInterface;
import source.services.TutorAvailabilityService;

/**
 * Servlet implementation class ViewAvailability
 */

public class ViewAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAvailability() {
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
		PrintWriter out = response.getWriter();
		int tutorId = Integer.parseInt(request.getParameter("selection"));
		TutorAvailabilityInterface tas = new TutorAvailabilityService();
		TutorAvailability t = tas.getAvailability(tutorId);
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Select Tutor </title>");
		out.println("</head>");
		out.println("<body>");
		if(t.getAvailability().length>0) {
			String[] avail = t.getAvailability();
			Boolean[] booked = t.isBooked();
			out.println("<form action = \"MakeBooking\" method=\"post\">");
			out.println("<input type = \"text\" name=\"tutorId\" value = \""+tutorId+"\" hidden>");
			out.println("<table>");
			out.println("<tr><th>Time Slot<th>Selection</th></tr>");
			boolean bookedForAll = true;
			System.out.println("booked array"+booked);
			for(int i=0;i<avail.length;i++) {
				if (booked[i] == false) {
					bookedForAll = false;
					out.println("<tr><td>"+avail[i]+"<td><input type = \"radio\" name = \"selection\" value = \""+i+"\"></tr> ");	
				}
			}
			if(bookedForAll == true) {
				out.println("<tr><td>No Slots Available</td></tr>");
				
			}
			else {
				out.println("</table>");
				out.println("<input type = \"submit\" value = \"Make Booking\">");
			
			}
		}
		else {
			out.println("Tutor has not registered Availability");
		}
		out.println("</body></html>");
		
	}

}
