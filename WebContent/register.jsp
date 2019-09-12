<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
</head>
<body>
    <form action="RegisterServlet" method="post">
        <table>
            <tr>
                <td>UserName :</td>
                <td><input type="text" name="userName"></td>
            </tr>
            <tr>
                <td>Name :</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Password :</td>
                <td><input type="password" name="passWord1"></td>
            </tr>
            <tr>
                <td>Confirm Password :</td>
                <td><input type="password" name="passWord2"></td>
            </tr>
            <tr>
                <td><input type="submit" value="Register"></td>
                <td><a href="homepage.jsp">HomePage</a></td>
            </tr>
        </table>
    </form>
</body>
</html>