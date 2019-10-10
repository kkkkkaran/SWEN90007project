<%@ page import="source.utils.AppSession" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
</head>
<body>
    <h3>Welcome to Management System</h3>
    The time is now <%= new java.util.Date() %>
    <% if (AppSession.isAuthenticated() && AppSession.hasRole(AppSession.ADMIN_ROLE))	{ %>
    <a href="CourseMaintain">Courses Maintenance</a>
    <a href="ApproveTutors">Approve Tutors</a>
    <a href="DeleteProfiles">Delete Accounts</a>
    
    <a href="logout.jsp">Logout</a>
    <% } else { %>
    You are not authenticated as admin.
    <a href="login.jsp">Login</a>
    <% } %>
</body>
</html>