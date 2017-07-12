

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check_in_update
 */
@WebServlet("/Check_in_update")
public class Check_in_update extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Check_in_update() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try{
			int loan_id = Integer.parseInt(request.getParameter("item"));
			Class.forName("com.mysql.jdbc.Driver");
			 Connection conn;
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/lcd","root","root");
			 
			 java.util.Date date = new java.util.Date();
			 String date_in = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 
			 
			 Statement st1 = conn.createStatement();
			 String query = "Update book_loans SET date_in='"+date_in+"' where book_loans.loan_id = '"+loan_id+"';";
			 st1.executeUpdate(query);
			 query = "Select isbn , card_id from book_loans where loan_id = '"+loan_id+"'";
			 int flag =0;
			 ResultSet rs = st1.executeQuery(query);
			 
			 
			 if(rs.next()){
				 String a1 = rs.getString(1);
				 String a2 = rs.getString(2);
				 //rs.previous();
				 System.out.println(rs.getString(1));
				 query = "Update book SET availability = 1 where book.isbn = '"+a1+"'";
				 st1.executeUpdate(query);
				 //System.out.println(rs.getString(2));
				 query = "Update borrower SET checkedoutbooks = checkedoutbooks-1 where borrower.card_id = '"+a2+"'";
				 st1.executeUpdate(query);
				 flag = 1;
			 }
			 if(flag ==1){
				 out.println("<html><body>");
	             out.println("<script type=\"text/javascript\">");
	             out.println("var popwin = window.alert(\"Check in Successful !!\")");
	             out.println("</script>");
	             out.println("</body></html>");
	            response.setHeader("Refresh", "1;url=Options.jsp");
			 }
			 
			 
			 
			 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
