<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produits</title>
<script type="text/javascript">

function getPanier() {
	if (window.localStorage.getItem("panier")) {
		return JSON.parse(window.localStorage.getItem("panier"));
	}
	return [];
}

function savePanier(panier) {
	window.localStorage.setItem("panier", JSON.stringify(panier));
}

function ajouterAuPanier(idProduit) {
	var panier = getPanier();
	panier.push(idProduit);
	savePanier(panier);
	updateInfoPanier();
}

function updateInfoPanier() {
	var infoPanier = document.getElementById("infoPanier");
	var panier = getPanier();
	if (panier.length == 0) {
		infoPanier.innerText = "Votre panier est vide.";
	} else if (panier.length == 1) {
		infoPanier.innerText = "Votre panier contient 1 article.";
	} else{
		infoPanier.innerText = "Votre panier contient " + panier.length + " articles.";
	}
}

</script>
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
				<td><fmt:formatNumber maxFractionDigits="2" currencyCode="EUR" type="CURRENCY" value="${produit.prix}"/></td>
				<td><button type="button" onclick="ajouterAuPanier(${produit.id})">Ajouter</button></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<p id="infoPanier"></p>
</section>
</body>
</html>