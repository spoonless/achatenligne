<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produits</title>
</head>
<body>

<section>
	<p>Voici la liste de nos produits&nbsp;:</p>
	<table>
		<thead>
			<tr>
				<th>Code</th>
				<th>Libellé</th>
				<th>Prix TTC</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${produits}" var="produit">
			<tr>
				<td><c:out value="${produit.code}"/></td>
				<td><c:out value="${produit.libelle}"/></td>
				<td><fmt:formatNumber maxFractionDigits="2" currencyCode="EUR" type="CURRENCY" value="${produit.prix}"/></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</section>

</body>
</html>