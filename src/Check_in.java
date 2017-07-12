

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Check_in
 */
@WebServlet("/Check_in")
public class Check_in extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public Check_in() {
        super();
       
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			 Connection conn;
			 conn = DriverManager.getConnection("jdbc:mysql://localhost/lcd","root","root");
			 Statement st1 = conn.createStatement();
			 String n = request.getParameter("cin_val");
             String query1 = "SELECT distinct book_loans.loan_id,book.title, book_loans.isbn, borrower.fname, borrower.lname, book_loans.card_id, book_loans.date_out, book_loans.due_date, book_loans.date_in "
             		+ "FROM book_loans, book, borrower,authors, book_authors "
             		+ "where (book.isbn = book_loans.isbn and borrower.card_id = book_loans.card_id"
             		+ " and (book.isbn = book_authors.isbn and authors.author_id = book_authors.author_id))"
             		+ " and (book.title LIKE '%" + n + "%' OR book.isbn = '"+n+"' OR borrower.fname LIKE '%" + n + "%' OR borrower.lname LIKE '%" + n + "%' OR borrower.card_id = '"+n+"')";
             List<ResultBean> list = new ArrayList<ResultBean>();
             ResultSet rs = st1.executeQuery(query1);
            
             String temp = null;
             while(rs.next()){
             temp = rs.getString(9);
             if (temp==null){
                 ResultBean temp1 = new ResultBean();
                 temp1.setLoan_id(rs.getInt(1));
                 temp1.setTitle(rs.getString(2));
                 temp1.setIsbn(rs.getString(3));
                 temp1.setFname(rs.getString(4));
                 temp1.setLname(rs.getString(5));
                 temp1.setCard_id(rs.getInt(6));
                 temp1.setDate_out(rs.getString(7));
                 temp1.setDue_date(rs.getString(8));
                 temp1.setDate_in(rs.getString(9));
                 System.out.println(temp1.getIsbn()); 
                 
                 list.add(temp1);
             }
             
             }
               
             request.setAttribute("number_of_result",list.size());
             conn.close();
             request.setAttribute("res",list);
             
             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/Check_in.jsp");
             dispatcher.forward(request, response);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
