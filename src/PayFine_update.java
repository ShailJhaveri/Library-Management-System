

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PayFine_update
 */
@WebServlet("/PayFine_update")
public class PayFine_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayFine_update() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			PrintWriter out = response.getWriter();
			int card_id = Integer.parseInt(request.getParameter("card_id"));
			Class.forName("com.mysql.jdbc.Driver");
			 Connection conn;
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/lcd","root","root");
			 Statement st = conn.createStatement();
			 Statement st1 = conn.createStatement();
			 String query = "Select checkedoutbooks from borrower where card_id = '"+card_id+"'";
			 ResultSet rs = st.executeQuery(query);
			 String query1 = "Select loan_id from book_loans where card_id = '"+card_id+"'";
			 if(rs.next()){
				
			 if(rs.getInt(1)>0){
				 out.println("<html><body>");
				 out.println("<script type = \"text/javascript\">");
				 out.println("var popwin = window.alert(\"Can not Pay Fine\")");
				 out.println("</script>");
				 out.println("</body></html>");
				 response.setHeader("Refresh", "1;url=Main.jsp");
			 }
			 else{
				 ResultSet rs1 = st1.executeQuery(query1);
				 while(rs1.next()){
					 String query2 = "Update fines SET paid = '1' where loan_id = '"+rs1.getInt(1)+"'";
					 Statement st3 = conn.createStatement();
					 st3.executeUpdate(query2);
					 
				 }
				 
				 out.println("<html><body>");
				 out.println("<script type = \"text/javascript\">");
				 out.println("var popwin = window.alert(\"Fine Paid\")");
				 out.println("</script>");
				 out.println("</body></html>");
				 response.setHeader("Refresh", "1;url=Options.jsp");
			 }
			 }
			 
			 
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
