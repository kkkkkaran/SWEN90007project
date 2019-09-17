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
    <a href="CourseMaintain">Courses Maintenance</a>
    <a href="ApproveTutors">Approve Tutors</a>
    <a href="DeleteProfiles">Delete Accounts</a>
    
    <a href="logout.jsp">Logout</a>
</body>
</html>