package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/ComplainsAPI")
public class ComplainsAPI extends HttpServlet {
private static final long serialVersionUID = 1L;

Complain complainObj = new Complain();


public ComplainsAPI() {
super();
// TODO Auto-generated constructor stub
}




protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
response.getWriter().append("Served at: ").append(request.getContextPath());
}




protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub
// doGet(request, response);

{
String output = complainObj.insertComplain(request.getParameter("cuscmID"),
request.getParameter("accountNo"),
request.getParameter("cDate"),
request.getParameter("descri"));
response.getWriter().write(output);
}



}



private static Map getParasMap(HttpServletRequest request)
{
Map<String, String> map = new HashMap<String, String>();
try
{
Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
String queryString = scanner.hasNext() ?
scanner.useDelimiter("\\A").next() : "";
scanner.close();
String[] params = queryString.split("&");
for (String param : params)
{

String[] p = param.split("=");
map.put(p[0], p[1]);
}
}
catch (Exception e)
{
}
return map;
}



protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub

{
Map paras = getParasMap(request);
String output = complainObj.updateComplain(paras.get("hidComplainIDSave").toString(),
paras.get("cuscmID").toString(),
paras.get("accountNo").toString(),
paras.get("cDate").toString(),
paras.get("descri").toString());
response.getWriter().write(output);
}



}




protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// TODO Auto-generated method stub

{
Map paras = getParasMap(request);
String output = complainObj.deleteComplain(paras.get("complainID").toString());
response.getWriter().write(output);
}
}



}