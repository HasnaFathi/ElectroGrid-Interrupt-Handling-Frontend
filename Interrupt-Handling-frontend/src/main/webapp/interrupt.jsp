<%@page import="com.interrupt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<%
//Save---------------------------------
if (request.getParameter("interruptCode") != null)
{
	interrupt interruptObj = new interrupt();
	String stsMsg = "";
	//Insert--------------------------
	if (request.getParameter("hidInterruptIDSave") == "")
	{
	stsMsg = interruptObj.insertInterrupt(request.getParameter("interruptCode"),
	request.getParameter("date"),
	request.getParameter("Duration"),
	request.getParameter("Start_time"),
	request.getParameter("End_time"),
	request.getParameter("Region"),
	request.getParameter("Reason"),
	request.getParameter("AdminID"));
}
else//Update----------------------
{
	stsMsg = interruptObj.updateInterrupt(request.getParameter("hidInterruptIDSave"),
	request.getParameter("interruptCode"),
	request.getParameter("date"),
	request.getParameter("Duration"),
	request.getParameter("Start_time"),
	request.getParameter("End_time"),
	request.getParameter("Region"),
	request.getParameter("Reason"),
	request.getParameter("AdminID"));
}
	session.setAttribute("statusMsg", stsMsg);
}
//Delete-----------------------------
if (request.getParameter("hidInterruptIDDelete") != null)
{
	interrupt itemObj = new interrupt();
	String stsMsg =
	itemObj.deleteInterrupt(request.getParameter("hidInterruptIDDelete"));
	session.setAttribute("statusMsg", stsMsg);
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
	<title>Interrupt Handling</title>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.6.0.min.js"></script>
	<script src="Components/interrupt.js"></script>
</head>
<body>
	<div class="container"><div class="row"><div class="col-6">
	<h1>Interrupt Handling</h1>
	<form id="formInterrupt" name="formInterrupt">
	 Interrupt code:
	 <input id="interruptCode" name="interruptCode" type="text"
	 class="form-control form-control-sm">
	 <br> Date:
	 <input id="date" name="date" type="text"
	 class="form-control form-control-sm">
	 <br> Duration:
	 <input id="Duration" name="Duration" type="text"
	 class="form-control form-control-sm">
	 <br> Start Time:
	 <input id="Start_time" name="Start_time" type="text"
	 class="form-control form-control-sm">
	 <br> End Time:
	 <input id="End_time" name="End_time" type="text"
	 class="form-control form-control-sm">
	 <br> Region:
	 <input id="Region" name="Region" type="text"
	 class="form-control form-control-sm">
	 <br> Reason:
	 <input id="Reason" name="Reason" type="text"
	 class="form-control form-control-sm">
	 <br> Admin ID:
	 <input id="AdminID" name="AdminID" type="text"
	 class="form-control form-control-sm">
	 
	 <br>
	 <input id="btnSave" name="btnSave" type="button" value="Save"
	 class="btn btn-primary">
	 <input type="hidden" id="hidInterruptIDSave"
	 name="hidItemIDSave" value="">
	</form>
	 <div id="alertSuccess" class="alert alert-success"></div>
	 <div id="alertError" class="alert alert-danger"></div>
	<br>
	 <div id="divInterruptGrid">
	 <%
	 interrupt interruptObj = new interrupt();
	 	 out.print(interruptObj.readInterrupt());
	 %>
</div>
</div> </div> </div>
</body>
</html>