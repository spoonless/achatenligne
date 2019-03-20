<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produits</title>
</head>
<body>

<section>
	<p>Voici la liste de nos produits&nbsp;:</p>
	<p><c:out value="${message}"/></p>
	<form method="post" action="<c:url value="/produits"/>">
	<table>
		<thead>
			<tr>
				<th>Code</th>
				<th>Libellé</th>
				<th>Prix TTC</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${produits}" var="produit">
			<tr>
				<td><c:out value="${produit.code}"/></td>
				<td><c:out value="${produit.libelle}"/></td>
				<td><fmt:formatNumber maxFractionDigits="2" currencyCode="EUR" type="CURRENCY" value="${produit.prix}"/></td>
				<td><button type="submit" name="idProduit" value="${produit.id}">Ajouter</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</form>
	<c:choose>
		<c:when test="${empty commande.produits}">
			<p> Votre panier est vide</p>
		</c:when>
		<c:otherwise>
			<p>Votre panier contient ${fn:length(commande.produits)} articles</p>
			<p><a href="<c:url value='/commande'/>">Consultez et validez votre panier</a></p>
		</c:otherwise>
	</c:choose>
</section>
</body>
</html>