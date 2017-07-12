import java.sql.*;

public class BookSearch {
	 static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   static final String DB_URL = "jdbc:mysql://localhost/EMP";

	   //  Database credentials
	   static final String USER = "root";
	   static final String PASS = "root";
	   
	   public static void main(String[] args) {
		   Connection conn = null;
		   Statement stmt = null;
		   
		   try
		    {
		      Class.forName("com.mysql.jdbc.Driver");
		      String url = "jdbc:mysql://localhost/lcd";
		      conn = DriverManager.getConnection(url, USER, PASS);
		      stmt = conn.createStatement();
		      
		      String query = "SELECT title, book_authors.author_id, name  FROM book, authors, book_authors WHERE book.isbn=book_authors.isbn AND book_authors.author_id=authors.author_id AND book.isbn='9780002005395'";
		      ResultSet rs = stmt.executeQuery(query);
		      
		      while(rs.next()){
		    	  int id  = rs.getInt("author_id");
		    	  String title = rs.getString("title");
		    	  String book_authors = rs.getString("name");
		    	  
		    	  //System.out.print(id);
		    	  System.out.print(title);
		    	  System.out.println(book_authors);
		    	  
		    
		      }
		      
		      
		      
		      
		      
		      
		      conn.close();
		    }
		   catch (Exception e){
			   e.printStackTrace();
		   }
	   }
}