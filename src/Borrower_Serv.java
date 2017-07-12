

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Borrower_Serv
 */
@WebServlet("/Borrower_Serv")
public class Borrower_Serv extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Borrower_Serv() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn;
			int c = 0;
			conn = DriverManager.getConnection("jdbc:mysql://localhost/lcd","root","root");
			Statement st1 = conn.createStatement();
			String q = "select count(*) from borrower;";
			ResultSet rs = st1.executeQuery(q);
			if(rs.next()){
				c = rs.getInt(1);
			}
			
			PreparedStatement st = null;
			
			 String query = "Insert into borrower (ssn,fname,lname,address,city,state,phone) values (?,?,?,?,?,?,?);";
			 st = conn.prepareStatement(query); 
			 st.setString(1, request.getParameter("ssn"));
			 st.setString(2, request.getParameter("first_name"));
			 st.setString(3, request.getParameter("last_name"));
			 st.setString(4, request.getParameter("address"));
			 st.setString(5, request.getParameter("city"));
			 st.setString(6, request.getParameter("state"));
			 st.setString(7, request.getParameter("phone_number"));
			 
			 
			 int b=0;
	            
	            b=st.executeUpdate();
			System.out.println(b);
			PrintWriter out = response.getWriter();
			out.println("<html><body>");
			 out.println("<script type = \"text/javascript\">");
			 out.println("var popwin = window.alert(\"Registration Successful\")");
			 out.println("</script>");
			 out.println("</body></html>");
			 response.setHeader("Refresh", "1;url=Options.jsp");
			 		
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
