<%@ page import="source.utils.AppSession" %>
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
    <% if (AppSession.isAuthenticated())	{ %>
    <a href="<%=request.getContextPath()%>/ViewProfileServlet">View Profile</a>
    <% if  (AppSession.hasRole(AppSession.STUDENT_ROLE))	{ %>
    <a href="<%=request.getContextPath()%>/CourseServlet">View Courses</a>
    <a href="<%=request.getContextPath()%>/ViewBookings">View Bookings</a>
    <% } else { %>
    <a href="<%=request.getContextPath()%>/ViewBookings">View Bookings</a>
    <a href="subjectRegister.jsp">Select Subjects to Teach</a>
    <a href = "tutoravailability.jsp">Set Availablity</a>
    <% } %>
    <a href="logout.jsp">Logout</a>
    <% } else { %>
    You are not authenticated.
    <a href="login.jsp">Login</a>
    <% } %>
</body>
</html>