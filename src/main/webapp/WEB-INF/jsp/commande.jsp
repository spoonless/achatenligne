<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Commande</title>
</head>
<body>

<section>
	<c:if test="${empty commande.produits}">
		<p>Votre commande est vide</p>
	</c:if>
	<c:if test="${not empty commande.produits}">
		<p>Votre commande&nbsp;:</p>
		<table>
			<thead>
				<tr>
					<th>Code</th>
					<th>Libellé</th>
					<th>Prix TTC</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${commande.produits}" var="produit">
				<tr>
					<td><c:out value="${produit.code}"/></td>
					<td><c:out value="${produit.libelle}"/></td>
					<td><fmt:formatNumber currencyCode="EUR" type="CURRENCY" value="${produit.prix}"/></td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		<p>Total TTC&nbsp;: <fmt:formatNumber currencyCode="EUR" type="CURRENCY" value="${commande.total}"/></p>
		<form method="post">
			<button type="submit">Valider votre commande</button>
		</form>
	</c:if>
	<p><a href="<c:url value='/produits'/>">Retour à la liste des produits</a></p>
</section>
</body>
</html>
