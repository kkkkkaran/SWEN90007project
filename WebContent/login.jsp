<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
    <form action="LoginServlet" method="post">
        <table>
            <tr>
                <td>UserName :</td>
                <td><input type="text" name="userName"></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><input type="password" name="passWord"></td>
            </tr>
            <tr>
                <td>Login Type :</td>
                <td>
                	<select name="type" id="type">
                	  <option  value="student" selected="selected" >Student</option>
					  <option value="tutor" >Tutor</option>
					  <option value="admin">Admin</option>
					</select>
				</td>
            </tr>
            <tr>
                <td><input type="submit" value="Login"></td>
                <td><a href="register.jsp">Registration</a></td>
                <td><a href="homepage.jsp">HomePage</a></td>
            </tr>
        </table>
    </form>
</body> 
</html>