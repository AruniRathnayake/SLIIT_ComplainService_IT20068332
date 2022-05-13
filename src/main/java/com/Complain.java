package com;
import java.sql.*;
public class Complain
{
private Connection connect()
 {
 Connection con = null;
 try
 {
 Class.forName("com.mysql.jdbc.Driver");
 con =
 DriverManager.getConnection(
 "jdbc:mysql://127.0.0.1:3306/ electrogrid", "paf", "12345678");
 }
 catch (Exception e)
 {
 e.printStackTrace();
 }
 return con;
 }
public String readComplain()
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for reading.";
 }
 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Customer ID</th><th>Account Number</th><th>Complain Date</th>" + "<th>Description</th><th>Update</th><th>Remove</th></tr>";
 String query = "select * from complain";
 Statement stmt = con.createStatement();
 ResultSet rs = stmt.executeQuery(query);

 // iterate through the rows in the result set
 while (rs.next())
 {
 String complainID = Integer.toString(rs.getInt("complainID"));
 String cuscmID = rs.getString("cuscmID");
 String accountNo = rs.getString("accountNo");
 String cDate = rs.getString("cDate");
 String descri = rs.getString("descri");

 // Add into the html table
 
 
output += "<tr><td><input id=\'hidComplainIDUpdate\' name=\'hidComplainIDUpdate\' type=\'hidden\' value=\'"+ complainID + "'>" + cuscmID + "</td>";


output += "<td>" + accountNo + "</td>";
output += "<td>" + cDate + "</td>";
output += "<td>" + descri + "</td>";
 
// buttons
output += "<td><input name='btnUpdate' type='button' value='Update' "
+ "class='btnUpdate btn btn-secondary' data-complainid='" + complainID + "'></td>"
+ "<td><input name='btnRemove' type='button' value='Remove' "
+ "class='btnRemove btn btn-danger' data-complainid='" + complainID + "'></td></tr>";
}
 con.close();
 
 // Complete the html table
 output += "</table>";
 }
 catch (Exception e)
 {
 output = "Error while reading the complain.";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String insertComplain(String cuscmID, String accountNo,
 String cDate, String descri)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for inserting.";
 }
 
 // create a prepared statement
 String query = " insert into complain(`complainID`,`cuscmID`,`accountNo`,`cDate`,`descri`)" + " values (?, ?, ?, ?, ?)";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, cuscmID);
 preparedStmt.setString(3, accountNo);
 preparedStmt.setString(4,cDate);
 preparedStmt.setString(5, descri);
 
 // execute the statement
 preparedStmt.execute();
 con.close();
 String newComplains = readComplain();
 output = "{\"status\":\"success\", \"data\": \"" +newComplains + "\"}";
 }
 catch (Exception e)
 {
 output = "{\"status\":\"error\", \"data\":\"Error while inserting the complain.\"}";
 System.err.println(e.getMessage());
 }
 return output;
 }
public String updateComplain(String complainID, String cuscmID, String accountNo,String cDate, String descri)
 {
 String output = "";
 try
 {
 Connection con = connect();
 if (con == null)
 {
 return "Error while connecting to the database for updating.";
 }
 
 // create a prepared statement
 String query = "UPDATE complain SET cuscmID=?,accountNo=?,cDate=?,descri=? WHERE complainID=?";
 PreparedStatement preparedStmt = con.prepareStatement(query);
 
 // binding values
 preparedStmt.setString(1, cuscmID);
 preparedStmt.setString(2, accountNo);
 preparedStmt.setString(3, cDate);
 preparedStmt.setString(4, descri);
 preparedStmt.setInt(5, Integer.parseInt(complainID));

 //execute the statement
preparedStmt.execute();
con.close();
String newComplains = readComplain();
output = "{\"status\":\"success\", \"data\": \"" +newComplains + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while updating the complain.\"}";
System.err.println(e.getMessage());
}
return output;
}
public String deleteComplain(String complainID)
{
String output = "";
try
{
Connection con = connect();
if (con == null)
{
return "Error while connecting to the database for deleting.";
}

// create a prepared statement
String query = "delete from complain where complainID=?";
PreparedStatement preparedStmt = con.prepareStatement(query);

// binding values
preparedStmt.setInt(1, Integer.parseInt(complainID));

// execute the statement
preparedStmt.execute();
con.close();
String newComplains = readComplain();
output = "{\"status\":\"success\", \"data\": \"" +newComplains + "\"}";
}
catch (Exception e)
{
output = "{\"status\":\"error\", \"data\":\"Error while deleting the complain.\"}";
System.err.println(e.getMessage());
}
return output;
}
}
