package com.achatenligne.web.api;

import java.math.BigDecimal;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.UriInfo;

import com.achatenligne.model.Produit;

public class ProduitDto {

	private Produit produit;
	private URI uri;

	public static List<ProduitDto> asList(List<Produit> produits, UriInfo uriInfo) {
		List<ProduitDto> resultat = new ArrayList<ProduitDto>();
		for (Produit produit : produits) {
			URI uri = uriInfo.getRequestUriBuilder().path(String.valueOf(produit.getId())).build();
			resultat.add(new ProduitDto(produit, uri));
		}
		return resultat;
	}

	public ProduitDto(Produit produit, URI uri) {
		this.produit = produit;
		this.uri = uri;
	}

	public URI getUri() {
		return uri;
	}

	public String getCode() {
		return produit.getCode();
	}

	public String getLibelle() {
		return produit.getLibelle();
	}

	public BigDecimal getPrix() {
		return produit.getPrix();
	}

}
