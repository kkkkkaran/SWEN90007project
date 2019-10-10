<%@ page import="source.utils.AppSession" %>
<%@ page import="source.domain.Course" %>
<%@ page import="source.domain.Tutor" %>

<%@ page import="source.services.TutorSubjectService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Subjects</title>
</head>
<body>
<% if (AppSession.isAuthenticated() && AppSession.hasRole(AppSession.TUTOR_ROLE))	{ %>
	<form action="AddTutorCourse" method="post">
	Add Courses To Teach:
	<table>
	<% 
	TutorSubjectService tss = new TutorSubjectService();
	for (Course course : tss.getCoursesNotRegistered((Tutor)AppSession.getUser()) ) { %>
	<tr>
		<td><%= course.getCourseName()%></td><td><input type="checkbox" name="coursesAdd" value="<%= course.getCourseId()%>"></td>
	</tr>
	<% } %>
	<tr><td><input type="submit" value="register"></td></tr>
	</table>
	</form>
	
	<form action="DeleteTutorCourse" method="post">
	Remove Courses To Teach:
	<table>
	<% 
	
	for (Course course : tss.getCoursesRegistered((Tutor)AppSession.getUser()) ) { %>
	<tr>
		<td><%= course.getCourseName()%></td><td><input type="checkbox" name="coursesDel" value="<%= course.getCourseId()%>"></td>
	</tr>
	<% } %>
	<tr><td><input type="submit" value="register"></td></tr>
	</table>
	</form>
	
<% } else { %>
You are not a tutor
<% } %>
</body>
</html>