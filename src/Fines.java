

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Fines
 */
@WebServlet("/Fines")
public class Fines extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Fines() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			 Class.forName("com.mysql.jdbc.Driver");
			 Connection conn;
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/lcd","root","root");
			 Statement st = conn.createStatement();
			 Statement st1 = conn.createStatement();
			 Statement st2 = conn.createStatement();
			 Statement st3 = conn.createStatement();
			 
			 String query = "Select * from book_loans;";
			 ResultSet rs = st.executeQuery(query);
			 
			 String date_in;
			 String due_date;
			 float fines;
			 float fines_check;
			 boolean paid = false;
			 while(rs.next()){
				 date_in = rs.getString(6);
				 Statement st4 = conn.createStatement();
				 String queryp = "Select paid from fines where loan_id = '"+rs.getInt(1)+"'";
				 ResultSet rsp = st4.executeQuery(queryp);
				 if(rsp.next()){
					 paid = rsp.getString(1).equals("1") ? false : true;
				 }
				 
				 if((date_in == null )){
					 due_date = rs.getString(5);
					 Date d = new Date();
                     Date d2 = null;
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                     d2=sdf.parse(due_date);
                     
                     fines = (float) (((d.getTime()-d2.getTime())/(1000*60*60*24))*0.25);
                    // System.out.println(fines);
                    
                     if(fines > 0){
                    	     System.out.println(fines);
                    	     
                    	     query = "Select fine_amt from fines where loan_id = '"+rs.getInt(1)+"'";
                    	     ResultSet rs2 = st2.executeQuery(query);
		                    	if(rs2.next()){
		                    		 query = "Update fines SET fine_amt= '"+fines+"' where loan_id = '"+rs.getInt(1)+"'";
		                    		 st3.executeUpdate(query);
		                    	 }
		                    	 else{
		                    	 query = "Insert into fines (fine_amt,loan_id) values ("+fines+",'"+rs.getInt(1)+"');";
		                    	 int b = st1.executeUpdate(query);
		                    	 }
		                    	 
		              }
                     
                  
                 }
				 else if(!(paid)){
					 due_date = rs.getString(5);
					 Date d2 = null;
					 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					 d2=sdf.parse(due_date);
					 
					 Date d3 = null;
					 d3=sdf.parse(due_date);
					 fines = (float) (((d3.getTime()-d2.getTime())/(1000*60*60*24))*0.25);
					 if(fines > 0){
                	     System.out.println(fines);
                	     
                	 query = "Select fine_amt from fines where loan_id = '"+rs.getInt(1)+"'";
                	 ResultSet rs2 = st2.executeQuery(query);
	                    	 if(rs2.next()){
	                    		 query = "Update fines SET fine_amt= '"+fines+"' where loan_id = '"+rs.getInt(1)+"'";
	                    		 st3.executeUpdate(query);
	                    	 }
	                    	 else{
	                    	 query = "Insert into fines (fine_amt,loan_id) values ("+fines+",'"+rs.getInt(1)+"');";
	                    	 int b = st1.executeUpdate(query);
	                    	 }
					 
					 
				 }
				 
				 
			 }
			 }
			 
			 String query1 = "Select book_loans.loan_id,isbn,card_id,fine_amt,paid from book_loans , fines where book_loans.loan_id = fines.loan_id;";
			 Statement st4 = conn.createStatement();
			 List<ResultBean> list = new ArrayList<ResultBean>();
			 ResultSet rss = st4.executeQuery(query1);
			 while(rss.next()){
				 ResultBean temp1 = new ResultBean();
				 temp1.setLoan_id(rss.getInt(1));
				 temp1.setIsbn(rss.getString(2));
				 temp1.setCard_id(rss.getInt(3));
				 temp1.setFine(rss.getFloat(4));
				 temp1.setPaid(rss.getBoolean(5));
				 
				 list.add(temp1);
				 
				 
			 }
			 request.setAttribute("number_of_result",list.size());
             conn.close();
             request.setAttribute("res",list);
             
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Fines.jsp");
             dispatcher.forward(request, response);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	
	}
	}
