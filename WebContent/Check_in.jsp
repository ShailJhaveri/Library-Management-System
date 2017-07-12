<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page import = "java.util.ArrayList"%>
<%@ page import = "java.util.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>



<style>

ul {

  list-style-type: none;

  margin: 0;

  padding: 0;

  overflow: hidden;

  background-color: #333;

}



li {

  float: left;

}



li a {

  display: block;

  color: white;

  text-align: center;

  padding: 14px 16px;

  text-decoration: none;

}



li a:hover:not(.active) {

  background-color: #111;

}



.active {

  background-color: #4CAF50;

}

.fsSubmitButton

{

padding: 10px 15px 11px !important;

font-size: 18px !important;

background-color: #57d6c7;

font-weight: bold;

text-shadow: 1px 1px #57D6C7;

color: #ffffff;

margin: 10px 300px 10px 650px;

border-radius: 5px;

-moz-border-radius: 5px;

-webkit-border-radius: 5px;

border: 1px solid #57D6C7;

cursor: pointer;

box-shadow: 0 1px 0 rgba(255, 255, 255, 0.5) inset;

-moz-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.5) inset;

-webkit-box-shadow: 0 1px 0 rgba(255, 255, 255, 0.5) inset;

}

div {

  border-radius: 5px;

  background-color: #f2f2f2;

  padding: 25px;

}



input[type=text]{

  width: 30%;

  padding: 12px 20px;

  margin: 10px 300px 10px 500px;

  display: inline-block;

  border: 1px solid #ccc;

  border-radius: 4px;

  box-sizing: border-box;

}

</style>

</head>

<body><div>

<form method="post" action="Options.jsp">

<button  type="submit">Options</button>

</form>

<br>

<form method="post" action="Check_in">

<br>

<h2 align = center >Check In

</h2>



<input type="text" class="form-control" name="cin_val" placeholder="Book Information or Borrower ID" required>


<button class="fsSubmitButton" type="submit">Check - In</button>

<br/>

</form>
	
	<c:choose>

    <c:when test="${requestScope.number_of_result > 0}">

       

<h3 align="center">Your search returned 

<c:out value="${requestScope.number_of_result}" /> results.</h3>

</c:when>

 </c:choose>

<br>

<br>

<table border="1">

<c:choose>

    <c:when test="${requestScope.number_of_result > 0}">

<tr><td>Sr. No.</td>

<td align="center">Loan_ID</td>

<td align="center">Title</td>

<td align="center">ISBN</td>

<td align="center">FName</td>

<td align="center">LName</td>

<td align="center">Card_id</td>

<td align="center">Date_Out</td>

<td align="center">Due_Date</td>

<td align="center">Check_In</td></tr>

</c:when>

 </c:choose>


<c:forEach items="${requestScope.res}" var="resulrVar" varStatus="loop"> 



 <tr><td align="center"><c:out value= "${loop.index + 1}" /></td>

 <td align="center"><c:out value="${resulrVar.loan_id}" /></td>

 <td align="center"><c:out value="${resulrVar.title}" /></td>

 <td align="center"><c:out value="${resulrVar.isbn}" /></td>
	
 <td align="center"><c:out value="${resulrVar.fname}" /></td>
 
 <td align="center"><c:out value="${resulrVar.lname}" /></td>
  
<td align="center"><c:out value="${resulrVar.card_id}" /></td>

 <td align="center"><c:out value="${resulrVar.date_out}" /></td>
 
 <td align="center"><c:out value="${resulrVar.due_date}" /></td>
 
 <td align = "center"><a href="Check_in_update?item=${resulrVar.loan_id}"><input type="button" value="Check_in"></a></td>
 
</c:forEach>

 </table>
	
	

</body>
</html>