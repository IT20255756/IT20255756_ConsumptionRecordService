<%@ page import="org.electro_grid.model.ConsumptionRecord"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<meta charset="ISO-8859-1">
<title>Consumption Record Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/ConsumptionRecord.js"></script>
</head>
<body>
<div class = "container">
<div class = "row">
<div class = "col">
	<h1>Consumption Record Management</h1>
	
	<form id="formCons" name="formCons" method="post" action="Index.jsp">
		 
		 Record Date :
		 <input id="recordDate" name="recordDate" type="date"
 						class="form-control form-control-sm">
		 
		 <br> Meter Number :
		 <input id="meterNo" name="meterNo" type="text"
 						class="form-control form-control-sm">
 						
		<br> Consumed Units:
		<input id="consumedUnits" name="consumedUnits" type="text"
 						class="form-control form-control-sm">
 						
 		<br> Pay Status:
		<input id="payStatus" name="payStatus" type="text"
 						class="form-control form-control-sm">
 						
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
						 class="btn btn-primary">
						 
		<input type="hidden" id="hidConsIDSave" name="hidConsIDSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success">
 	
 		
 	</div>
 	<div id = "alertError" class="alert-danger"></div>
	
	<br>
	<br>
	
	<div id ="divUserGrid">
	<%
	 ConsumptionRecord consObj = new ConsumptionRecord(); 
	 out.print(consObj.readConsumptionRecord()); 
	%>
	</div>

</body>
</html> 