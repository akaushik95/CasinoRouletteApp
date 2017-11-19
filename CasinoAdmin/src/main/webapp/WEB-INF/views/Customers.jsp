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
<title>Home page</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
	.navbar navbar-inverse{
		color:"red";
	}
	.nav navbar-nav{
		color:"red";
	}
	
</style>
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
  <img src="<c:url value="/resources/images/casino.jpg" />" alt="Welcome to Casino"  width="100%" height="100%"/>
</div>
</body>
</html>


