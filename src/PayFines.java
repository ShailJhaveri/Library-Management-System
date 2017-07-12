

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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
 * Servlet implementation class PayFines
 */
@WebServlet("/PayFines")
public class PayFines extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PayFines() {
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
				 int card_id = Integer.parseInt(request.getParameter("card_id"));
				 String query = "Select book_loans.card_id, SUM(fines.fine_amt) AS TotalFine from book_loans,fines where book_loans.loan_id = fines.loan_id AND book_loans.card_id = '"+card_id+"' AND fines.paid not in "
				 		+ "('1') Group by card_id";
				 ResultSet rs = st1.executeQuery(query);
				 List<ResultBean> list = new ArrayList<ResultBean>();
				 while(rs.next()){
					 ResultBean temp1 = new ResultBean();
					 temp1.setCard_id(rs.getInt(1));
					 temp1.setTotalAmt(rs.getFloat(2));
					 
					 list.add(temp1);
				 }
				 
				 
				 request.setAttribute("number_of_result",list.size());
	             conn.close();
	             request.setAttribute("res",list);
	             
	             RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/PayFines.jsp");
	             dispatcher.forward(request, response);
				 
			}
			catch(Exception e){
				e.printStackTrace();
			}
	}

}
