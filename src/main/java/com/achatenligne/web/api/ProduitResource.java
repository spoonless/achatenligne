package com.achatenligne.web.api;

import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.achatenligne.model.Produit;
import com.achatenligne.model.ProduitService;

@Path("/produits/{id}")
public class ProduitResource {
	
	private int id;
	
	public ProduitResource(@PathParam("id") int id) {
		this.id = id;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Produit get() {
		ProduitService produitService = new ProduitService();
		Optional<Produit> produit = produitService.getById(this.id);
		if (! produit.isPresent()) {
			throw new NotFoundException("Ce produit n'existe pas");
		}
		return produit.get();
	}
	
	@DELETE
	public void delete() {
		ProduitService produitService = new ProduitService();
		produitService.supprimer(this.id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Produit modifier(Produit produit) {
		ProduitService produitService = new ProduitService();
		produit.setId(this.id);
		if (! produitService.modifier(produit)) {
			throw new NotFoundException();
		}
		return produit;
	}

}
