package source.script;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.dataSource.CourseGateway;

/**
 * Servlet implementation class CourseAdd
 */

public class CourseAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CourseAdd() {
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
		doGet(request, response);
		CourseGateway cg=new CourseGateway();
		String name = request.getParameter("subjectToAdd");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title> Add Course </title>");
		out.println("</head>");
		out.println("<body>");
		try {
			cg.insert(name);
			
			out.println("Inserted!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("Unsuccessful!");
		}
		out.println("</body></html>");
	}

}
