<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<script type="text/javascript">
	function toggleTutorFields() {
		if (document.getElementById('type').value == "student"){
			document.getElementById("serviceRadius").disabled = true;
			document.getElementById("subjects").disabled = true;
			document.getElementById("price").disabled = true;
				
		}
		else{
			document.getElementById("serviceRadius").disabled = false;
			document.getElementById("subjects").disabled = false;
			document.getElementById("price").disabled = false;
			
			
		}
		
	}
	
	
	
</script>
</head>
<body>
    <form action="RegisterServlet" method="post">
        <table>
        	<tr>
                <td>First Name :</td>
                <td><input type="text" name="fname"></td>
            </tr>
            <tr>
                <td>Last Name :</td>
                <td><input type="text" name="lname"></td>
            </tr>
            <tr>
                <td>Date of Birth (DD/MM/YYYY):</td>
                <td><input type="text" name="dob"></td>
            </tr>
            <tr>
                <td>Education :</td>
                <td><input type="text" name="education"></td>
            </tr>
            <tr>
                <td>Address :</td>
                <td><input type="text" name="address"></td>
            </tr>
        	<tr>
            	<td>Registration Type :</td>
                <td>
                	<select name="type" id="type" onClick="javascript:toggleTutorFields()">
                	  <option  value="student" selected="selected" >Student</option>
					  <option value="tutor" >Tutor</option>
					</select>
				</td>
            </tr>
            <tr>
                <td>Service Radius :</td>
                <td><input type="text" name="serviceRadius" id="serviceRadius" disabled></td>
            </tr>
            <tr>
                <td>Subject List :</td>
                <td><input type="text" name="subjects" id="subjects" disabled></td>
            </tr>
            <tr>
                <td>Rate per hour :</td>
                <td><input type="text" name="price" id="price" disabled></td>
            </tr>
            <tr>
                <td>UserName :</td>
                <td><input type="text" name="userName"></td>
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