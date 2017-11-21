<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Customers</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<style type="text/css">
tfoot input {
	width: 100%;
	padding: 3px;
	box-sizing: border-box;
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
	<p style="float: right" class="navbar-text">Casino Admin
		Application</p>
	</nav>
	<div class="container">

		<input type="text" placeholder="Name" class="column_filter"
			id="col1_filter" data-column="1" />

		<table id="example" class="display">
			<thead>
				<tr class="danger">
					<th width="40">ID</th>
					<th width="60">Name</th>
					<th width="60">DOB</th>
					<th width="60">Contact</th>
					<th width="80">Email</th>
					<th width="60">Balance</th>
					<th width="50">Recharge</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${listCustomers}" var="customer">
					<tr class="success">
						<td>${customer.id}</td>
						<td>${customer.name}</td>
						<td>${customer.dob}</td>
						<td>${customer.contact}</td>
						<td>${customer.email}</td>
						<td>${customer.balance}</td>
						<td><button id=${customer.id } type="button"
								class="btn btn-info btn-lg" data-toggle="modal"
								data-target="#myModal${customer.id}">Recharge</button></td>
						<div class="container">

							<!-- Modal -->
							<div class="modal fade" id="myModal${customer.id}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Recharge</h4>
										</div>
										<div class="modal-body">
											<form action="/recharge/${customer.id}" method="post"
												modelAttribute="recharge">
												<%-- <div class="form-group">
							      <label for="id">ID:</label>
							      <input path="id" type="text" class="form-control" id="id" value="${customer.id}" name="id" readonly="true"/>
								</div>
							    <div class="form-group"> --%>
												<label for="name">Recharge:</label> <input type="number"
													min=0 class="form-control" id="balance"
													placeholder="Enter the amount" name="balance" />
										</div>
										<button type="submit" class="btn btn-default modal-action">Recharge</button>

										</form>

									</div>
								</div>

							</div>
						</div>
	</div>
	</tr>
	</c:forEach>
	</tbody>
	</table>
	</div>

	<script>
		var table = null;
		$(document)
				.ready(
						function() {
							// Setup - add a text input to each footer cell
							$('#example tfoot th')
									.each(
											function() {
												var title = $(this).text();
												$(this)
														.html(
																'<input type="text" placeholder="Search '+title+'" />');
											});

							// DataTable
							table = $('#example').DataTable();

							// Apply the search
							/* table.columns().every( function () {
							    var that = this;
							
							    $( 'input', this.footer() ).on( 'keyup change', function () {
							        if ( that.search() !== this.value ) {
							            that
							                .search( this.value )
							                .draw();
							        }
							    } );
							} ); */

							$('input.column_filter').on(
									'keyup click',
									function() {
										filterColumn($(this)
												.attr('data-column'));
									});

						});

		function filterColumn(i) {
			console.log(table);
			table.column(i).search($('#col' + i + '_filter').val(),
					$('#col' + i + '_regex').prop('checked'),
					$('#col' + i + '_smart').prop('checked')).draw();
		}
	</script>

</body>
</html>


