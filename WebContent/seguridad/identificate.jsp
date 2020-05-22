<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina de identificacion</title>
</head>
<body>
	
	<div id="container">
		<div id="header"></div>
		<form action="j_security_check" method="POST">
			<table cellspacing="2" cellpadding="3" border="0" width="100%">
				<tr>
					<td colspan="2">
						<h2>Introduzca sus datos de usuario</h2>
					</td>
				</tr>
				<tr>
					<td colspan="2"></td>
				</tr>
				<tr>
					<td width="11%">Username</td>
					<td><input type="text" name="j_username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="j_password" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td colspan="2"><input type="submit" name="login"
						value="Login" /> <a href="home.xhtml">Registrese</a>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<hr />
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>