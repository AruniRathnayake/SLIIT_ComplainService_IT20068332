<%@page import="com.Complain"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Complain Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/complains.js"></script>
</head>
<body>
<div class="container"><div class="row"><div class="col-6">
<h1>Complain Management</h1>
<form id="formComplain" name="formComplain">
 Customer ID:
 <input id="cuscmID" name="cuscmID" type="text"
 class="form-control form-control-sm">
 <br> Account Number:
 <input id="accountNo" name="accountNo" type="text"
 class="form-control form-control-sm">
 <br> Complain Date:
 <input id="cDate" name="cDate" type="text"
 class="form-control form-control-sm">
 <br> Description:
 <input id="descri" name="descri" type="text"
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save"
 class="btn btn-primary">
 <input type="hidden" id="hidComplainIDSave"
 name="hidComplainIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divComplainGrid">
 <%
 Complain complainObj = new Complain();
 out.print(complainObj.readComplain());
 %>
</div>
</div> </div> </div>
</body>
</html>