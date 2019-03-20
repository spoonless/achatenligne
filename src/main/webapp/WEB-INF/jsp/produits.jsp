<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produits</title>
<script type="text/javascript" src="achatenligne.js"></script>
</head>
<body onload="updateInfoPanier()">
<section>
	<p>Voici la liste de nos produits&nbsp;:</p>
	<p><c:out value="${message}"/></p>
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
				<td><fmt:formatNumber currencyCode="EUR" type="CURRENCY" value="${produit.prix}"/></td>
				<td><button type="button" onclick="ajouterAuPanier(${produit.id})">Ajouter</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<p id="infoPanier"></p>
	<p>
		<a href="javascript:afficherPanier()">Afficher le panier</a>
	</p>
</section>
</body>
</html>