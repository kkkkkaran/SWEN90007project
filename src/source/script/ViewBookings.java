package source.script;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.domain.Appointment;
import source.domain.Student;
import source.domain.Tutor;
import source.services.AppointmentsInterface;
import source.services.AppointmentsService;
import source.services.StudentInterface;
import source.services.StudentService;
import source.services.TutorInterface;
import source.services.TutorService;
import source.utils.AppSession;

/**
 * Servlet implementation class ViewBookings
 */

public class ViewBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewBookings() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Booking Tutor! </title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table>");
		AppointmentsInterface as = new AppointmentsService();
		
		if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.STUDENT_ROLE)) {
			List<Appointment> appointmentList = as.getAppointmentsForStudent(AppSession.getUser().getId());
			out.println("<tr><th>Tutor Name</th><th>Location</th><th>Slot</th><th>Approval Status</th></tr>");
			for(int i=0; i<appointmentList.size();i++){
				Appointment appointment = appointmentList.get(i);
				TutorInterface ts = new TutorService();
				Tutor t = ts.getTutorAtId(appointment.getTutorId());
				out.println("<tr><td>"+t.getFirstName()+"</td><td>"+t.getAddress()+"</td><td>"+appointment.getSlot()+"</td><td>"+appointment.isTutorAccepted()+"</td></td>");
			}
			out.println("</table>");
			
		}
		
		if(AppSession.isAuthenticated() && AppSession.hasRole(AppSession.TUTOR_ROLE)) {
			out.println("<form action = \"ViewBookings\" method = \"post\"");
			List<Appointment> appointmentList = as.getAppointmentsForTutor(AppSession.getUser().getId());
			out.println("<tr><th>Student Name</th><th>Slot</th><th>Approval Status</th><th><Approve></th></tr>");
			for(int i=0; i<appointmentList.size();i++){
				Appointment appointment = appointmentList.get(i);
				StudentInterface ss = new StudentService();
				Student s = ss.getStudentAtId(appointment.getStudentId());
				out.println("<tr><td>"+s.getFirstName()+"</td><td>"+appointment.getSlot()+"</td><td>"+appointment.isTutorAccepted()+"</td>");
				if(appointment.isTutorAccepted() == false) {
					out.println("<td><input type = \"checkbox\" name = \"approve\" value = \""+appointment.getAppointmentId()+"\"></td></tr>");
				}
				else {
					out.println("<td><input type = \"checkbox\" value = \""+appointment.getAppointmentId()+"\" disabled></td></tr>");
				}
				
			}
			out.println("</table>");
			out.println("<input type = \"submit\" value = \"Approve\">");
		}
		
		out.println("<a href=\"welcome.jsp\">Home</a>");
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String[] approvals = request.getParameterValues("approve");
		AppointmentsInterface as = new AppointmentsService();
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Booking Tutor! </title>");
		out.println("</head>");
		out.println("<body>");
		for (int i = 0;i<approvals.length;i++) {
			
			Appointment a = as.getAppointment(Integer.parseInt(approvals[i]));
			a.setTutorAccepted(true);
			int status = as.updateAppointment(a);
			if (status == 1) {
				out.println("Appointment for slot = "+a.getSlot()+" has been approved");
			}
			else {
				out.println("Appointment for slot = "+a.getSlot()+" could not be approved");
			}
		}
		out.println("<a href=\"welcome.jsp\">Home</a>");
		out.println("</body>");
		out.println("</html>");
		
		
		
		
	}

}
