<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="source.utils.AppSession" %>
<%@ page import="source.domain.TutorAvailability" %>
<%@ page import="source.services.TutorAvailabilityService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View and Set Availability</title>
</head>
<body>

<% if (AppSession.isAuthenticated() && AppSession.hasRole(AppSession.TUTOR_ROLE))	{ %>
	<form action="AddTutorAvailability" method="post">
	View and Set Availability
	<table>
	<% 
	TutorAvailabilityService tss = new TutorAvailabilityService();
	TutorAvailability t = tss.getAvailability(AppSession.getUser().getId());
	String avail = "a";
	if(t.getAvailability().length>0){
		avail = String.join("\n",t.getAvailability());
	}
	System.out.print(avail);
	
	%>
	<tr><td>Please enter each slot on a new line<td><tr>
	<tr>
		<td><textarea name="availability"><%= avail %></textarea></td>
	</tr>
	<tr><td><input type="submit" value="Update"></td></tr>
	
	
	</table>
	</form>
	
<% } else { %>
You are not a tutor <a href="login.jsp">Login</a>
<% } %>

</body>
</html>