<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Customer</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<%-- <script src="<c:url value="/resources/js/script.js" />" type="text/javascript"></script> --%>
<script type="text/javascript">
function hello() {
	var dateString = document.getElementById("dob").value;
	if(dateString !=""){
	    var today = new Date();
	    var birthDate = new Date(dateString);
	    var age = today.getFullYear() - birthDate.getFullYear();
	    var m = today.getMonth() - birthDate.getMonth();
	    var da = today.getDate() - birthDate.getDate();
	    if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
	        age--;
	    }
	    if(m<0){
	        m +=12;
	    }
	    if(da<0){
	        da +=30;
	    }

	  	if(age < 18 || age > 100){
			alert("Age "+age+" is restricted");
			document.getElementById("dob").value="";
	  	}else{
			alert("Age "+age+" is allowed");
	  	}
	}else{
		alert("please provide your date of birth");
	}
}
</script>
</head>
<body style="margin: 10px">
<nav class="navbar navbar-inverse">
  <ul class="nav navbar-nav">
    <li><a href="Customers">Home</a></li>
    <li><a href="RegisterCustomer">Register</a></li>
    <li><a href="ListCustomers">List Users</a></li>
  </ul>
  <p style="float:right" class="navbar-text">Casino Admin Application</p>
</nav>

<div class="container">
	<c:url var="addAction" value="/customer/add" ></c:url>
  <form action="${addAction}" method="post" modelAttribute="customer" enctype="multipart/form-data">
    <div class="form-group">
      <label for="name">Name:</label>
      <input path="name" type="text" class="form-control" id="name" pattern="[a-zA-Z]{0,}" placeholder="Enter Name minimum 1 letters" name="name" required="true"/>
    </div>
    <div class="form-group">
      <label for="date">DOB:</label>
      <input path="dob" type="date" class="form-control" id="dob" placeholder="Enter date of birth" name="dob" required="true"  onblur="hello()"/>
    </div>
    <div class="form-group">
      <label for="contact">Contact:</label>
      <input path="contact" type="number" class="form-control" id="contact" min="1000000000" max="9999999999" placeholder="Enter contact" required="true" name="contact"/>
    </div>
    <div class="form-group">
      <label for="email">Email:</label>
      <input path="email" type="email" class="form-control" id="email" placeholder="Enter email id" name="email" required="true"/>
    </div>
    <div class="form-group">
      <label for="file">IdProof:</label>
      <input type="file" class="form-control" id="file" placeholder="Select file" name="file">
    </div>
    <button id="submitButton" type="submit" class="btn btn-default">Submit</button>
  </form>
</div>

</body>
</html>


