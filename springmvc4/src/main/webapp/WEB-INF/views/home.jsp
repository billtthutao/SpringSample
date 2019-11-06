<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page isELIgnored="false" %>
<html>
<head>
<title>Spittr</title>
<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
</head>
<body>
<h1>Welcome to Spittr</h1>
<a href="<c:url value="/spittles" />">Spittles</a> |
<a href="<c:url value="/spitter/register" />">Register</a>
${message}
	<form method="POST" enctype="multipart/form-data">
		<label>Profile Picture</label>:
		<input type="file" name="file" accept="image/jpeg,image/png,image/gif" /><br/>
		<input type="submit" value="Register" />
	</form>
</body>
</html>