function getPanier() {
	if (window.localStorage.getItem("panier")) {
		return JSON.parse(window.localStorage.getItem("panier"));
	}
	return [];
}

function savePanier(panier) {
	window.localStorage.setItem("panier", JSON.stringify(panier));
}

function viderPanier() {
	savePanier([]);
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

function afficherPanier() {
	var panier = getPanier();
	if (panier.length == 0) {
		window.location.href = "commande";
	} else {
		window.location.href = "commande?produitId=" + panier.join("&produitId=");
	}
}
