


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Check_out
 */
@WebServlet("/Check_out")
public class Check_out extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Check_out() {
        super();
        
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		try{
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn;
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/lcd","root","root");
			 Statement st1 = conn.createStatement();
			 int available = 0, c_out_books = 0;
			 String query1;
			 String cb_isbn = request.getParameter("co_isbn");
			 String cb_id = request.getParameter("card_id_co");
			 
			 query1 = "Select book.availability from book where book.isbn = '"+cb_isbn +"'"; 
			 ResultSet rs = st1.executeQuery(query1);
			 if(rs.next()){
				 available = rs.getInt(1);
			 }
			 
			 String query2;
			 Statement st2 = conn.createStatement();
			 query2 = "Select borrower.checkedoutbooks from borrower where borrower.card_id = '"+cb_id+"'";
			 ResultSet rs2 = st2.executeQuery(query2);
			 if(rs2.next()){
				 c_out_books=rs2.getInt(1);
			 }
			 
			 
			 System.out.println(c_out_books);
			 System.out.println(available);
			 
			 java.util.Date date = new java.util.Date();
			 String date_out = new SimpleDateFormat("yyyy-MM-dd").format(date);
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 
			 Calendar c = Calendar.getInstance();
			 try{
				 c.setTime(sdf.parse(date_out));
			 }catch(ParseException p){
				 p.printStackTrace();
			 }
			 c.add(Calendar.DATE, 14);
			 String due_date = sdf.format(c.getTime());
					 
			 int flag = 0;
			 if(available != 1){
				 flag = 1;
			 }
			 
			 if(c_out_books >= 3){
				 flag =2;
			 }
			 
			 if(flag ==0){
					 String query3;
					 query3 = "Insert into book_loans (isbn,card_id,date_out,due_date) values ('"+cb_isbn+"','"+cb_id+"','"+date_out+"','"+due_date+"')";
					 Statement st3 = conn.createStatement();
					 st3.executeUpdate(query3);
					 query3 = "Update book SET availability=0 where book.isbn = '"+cb_isbn+"'";
					 st3.executeUpdate(query3);
					 query3 = "Update borrower SET checkedoutbooks = checkedoutbooks+1 where borrower.card_id = '"+cb_id+"'";
					 st3.executeUpdate(query3);
			 }
			 
			 if(flag == 1){
				 out.println("<html><body>");
				 out.println("<script type = \"text/javascript\">");
				 out.println("var popwin = window.alert(\"The book is not available! Try Later..\")");
				 out.println("</script>");
				 out.println("</body></html>");
				 response.setHeader("Refresh", "1;url=Options.jsp");
				 		
			 }
			 else if(flag == 2){
				 out.println("<html><body>");
				 out.println("<script type = \"text/javascript\">");
				 out.println("var popwin = window.alert(\"You can not borrow book! Limit exceeds..\")");
				 out.println("</script>");
				 out.println("</body></html>");
				 response.setHeader("Refresh", "1;url=Options.jsp");
			 }
			 else{
				 out.println("<html><body>");
				 out.println("<script type = \"text/javascript\">");
				 out.println("var popwin = window.alert(\"Checkout Successful\")");
				 out.println("</script>");
				 out.println("</body></html>");
				 response.setHeader("Refresh", "1;url=Options.jsp");
				 		
			 }
			 }
			  	
		
		catch (Exception e){
			   e.printStackTrace();
		    }
	}

}
