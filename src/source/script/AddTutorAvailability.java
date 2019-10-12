package source.script;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import source.utils.AppSession;
import source.domain.TutorAvailability;
import source.services.TutorAvailabilityService;

/**
 * Servlet implementation class AddTutorAvailability
 */

public class AddTutorAvailability extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTutorAvailability() {
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
		PrintWriter writer = response.getWriter();
		if (AppSession.isAuthenticated() && AppSession.hasRole(AppSession.TUTOR_ROLE))	{
			int userID = AppSession.getUser().getId();
			String[] availabilityArr = request.getParameter("availability").split("[\\r\\n]+");
			Boolean[] booked = new Boolean[availabilityArr.length];
			for(int i=0;i<availabilityArr.length;i++) {
				booked[i]= false;
			}
			TutorAvailability t = new TutorAvailability();
			t.setId(userID);
			t.setAvailability(availabilityArr);
			t.setBooked(booked);
			TutorAvailabilityService tas = new TutorAvailabilityService();
			int status = tas.setAvailability(t);
			if(status == 1) {
				writer.println("Success");
				response.sendRedirect("welcome.jsp");
			}
			else {
				writer.println("Failure");
				response.sendRedirect("welcome.jsp");
			}
			
		}
		
	}

}
