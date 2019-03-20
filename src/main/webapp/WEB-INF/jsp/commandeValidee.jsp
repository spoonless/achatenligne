<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Commande validée</title>
</head>
<body>
	<p>Votre commande a bien été validée. Merci&nbsp;!</p>
	<p><a href="<c:url value='/produits'/>">Retour à la liste des produits</a></p>
</body>
</html>