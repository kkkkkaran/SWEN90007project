<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
</head>
<body>
    <h3>Welcome to Tutor Booking System</h3>
    <a href="<%=request.getContextPath()%>/ViewProfileServlet">View Profile</a>
    <a href="<%=request.getContextPath()%>/CourseServlet">View Courses</a>
    <a href="logout.jsp">Logout</a>
</body>
</html>