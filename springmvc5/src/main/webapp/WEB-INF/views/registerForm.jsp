<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css"
href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>Register</h1>
<form method="POST">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
First Name: <input type="text" name="firstName" /><br/>
Last Name: <input type="text" name="lastName" /><br/>
Username: <input type="text" name="username" /><br/>
Email: <input type="text" name="email" /><br/>       
Password: <input type="password" name="password" /><br/>
<input type="submit" value="Register" />
</form>
</body>
</html>