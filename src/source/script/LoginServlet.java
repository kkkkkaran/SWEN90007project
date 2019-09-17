
package source.script;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter writer = response.getWriter();
		doGet(request, response);
		
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		String type = request.getParameter("type");
		
		if (userName.equals("admin") && passWord.equals("admin") && type.equals("admin")) {
			response.sendRedirect("dashboard.jsp");
		} else {
			try {
			    Connection conn = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/", "postgres", "jie");
			    PreparedStatement ps = conn.prepareStatement("select * from "+type+" where username=? and password=?");
				ps.setString(1, userName);
				ps.setString(2, passWord);
				ResultSet rs = ps.executeQuery();
				
				if(rs.next()) {
					response.sendRedirect("welcome.jsp");
				} else {
					writer.println("username or password does not exit");
				}
				
			}catch(Exception e){
				System.out.println(e);
				
			}
	    }
	}

}
