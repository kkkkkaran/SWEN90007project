package source.script;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.domain.Appointment;
import source.domain.TutorAvailability;
import source.services.AppointmentsService;
import source.services.TutorAvailabilityService;
import source.utils.AppSession;
import source.utils.LockManager;

/**
 * Servlet implementation class MakeBooking
 */

public class MakeBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeBooking() {
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
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Booking Tutor! </title>");
		out.println("</head>");
		out.println("<body>");
		if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.STUDENT_ROLE)) {
			
			
			System.out.println("selection"+request.getParameter("selection"));
			System.out.println("tutorId"+request.getParameter("tutorId"));
			int selection = Integer.parseInt(request.getParameter("selection"));
			int tutorId = Integer.parseInt(request.getParameter("tutorId"));
			
			//Fetching slot details 
			TutorAvailabilityService tas = new TutorAvailabilityService();
			TutorAvailability t = tas.getAvailability(tutorId);
			//Locking TutorAvailability
			try {
				LockManager.getInstance().acquireWriteLock(t);
			}catch(InterruptedException e) {
				System.out.println("Could not lock");
			}
			String slot = t.getAvailability()[selection];
			Boolean[] booked = t.isBooked();
			booked[selection] = true;
			t.setBooked(booked);
			
			
			//creating booking
			AppointmentsService as = new AppointmentsService();
			Appointment a = new Appointment();
			a.setStudentId(AppSession.getUser().getId());
			a.setTutorId(tutorId);
			a.setSlot(slot);
			a.setTutorAccepted(false);
			int status = as.insertAppointment(a);
			
			if (status == 1) {
				tas.setAvailability(t);
				out.println("Booking Created. Wait for Tutor Approval");
				
				
			}
			else {
				out.println("Failure");
			}
			
			//Release Lock
			LockManager.getInstance().releaseWriteLock(t);
			
		}
		else {
			out.println("You are not a student");
		}
		out.println("<a href=\"welcome.jsp\">Home</a>");
		out.println("</body>");
		out.println("</html>");
		
		
	}

}
