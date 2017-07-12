

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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static final String USER = "root";
	   static final String PASS = "root";
	   
       
   
    public Search() {
        super();
        //System.out.println("Hello");
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 //System.out.println("Hello");
		doPost(request, response);
	}

	Connection conn = null;
	   Statement stmt = null;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   
		   try
		    {
			      Class.forName("com.mysql.jdbc.Driver");
			      String url = "jdbc:mysql://localhost/lcd";
			      conn = DriverManager.getConnection(url, USER, PASS);
			      PreparedStatement stmt = null;
			      String a = request.getParameter("isbn");
			     
			      String query = "SELECT book.isbn, title , name , book.availability  FROM book, authors, book_authors "
			      		+ "WHERE book.isbn=book_authors.isbn AND book_authors.author_id=authors.author_id "
			      		+ "AND (book.isbn = ? OR authors.name like ? OR book.title like ?) "
			      		+ "order by book.isbn ;";
			 
			      //System.out.println(query);
			      stmt = conn.prepareStatement(query);
			      
			      stmt.setString(1, request.getParameter("isbn"));
			      stmt.setString(2, "%"+request.getParameter("isbn")+"%");
			      stmt.setString(3, "%"+request.getParameter("isbn")+"%");
		      
			      ResultSet rs = stmt.executeQuery();
			      
			      List<ResultBean> list = new ArrayList<ResultBean>();
			      ResultBean trb;
			      String s = null;
		      
			      while(rs.next())
			      {
			    	  trb = new ResultBean();
			    	  trb.setIsbn(rs.getString(1));
			    	  trb.setTitle(rs.getString(2));
			    	  trb.setAuthorname(rs.getString(3));
			    	  trb.setAvailabilty(rs.getInt(4));
			    	  
			    	  while(rs.next()){
			    		  if(trb.getIsbn().equals(rs.getString(1))){
			    			  trb.setAuthorname(trb.getAuthorname() + "," + rs.getString(3));
			    		  }
			    		  else{
			    			  rs.previous();
			    			  break;
			    		  }
			    	  }
			    	  System.out.println(trb.getIsbn());
			    	  list.add(trb);
			      }	
			      
			      request.setAttribute("size1", list.size());
			      conn.close();
			      
			      request.setAttribute("res", list);
			      RequestDispatcher rd = request.getRequestDispatcher("/Main.jsp");
			      rd.forward(request, response);
		    }
		   catch (Exception e){
			   e.printStackTrace();
		    }
	}

}
