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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.achatenligne.model.Produit;
import com.achatenligne.model.ProduitService;

@Path("/produits/{id}")
public class ProduitResource {
	
	private int id;
	private UriInfo uriInfo;
	
	public ProduitResource(@PathParam("id") int id, @Context UriInfo uriInfo) {
		this.id = id;
		this.uriInfo = uriInfo;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ProduitDto get() {
		ProduitService produitService = new ProduitService();
		Optional<Produit> produit = produitService.getById(this.id);
		if (! produit.isPresent()) {
			throw new NotFoundException("Ce produit n'existe pas");
		}
		return new ProduitDto(produit.get(), uriInfo.getRequestUriBuilder().build());
	}
	
	@DELETE
	public void delete() {
		ProduitService produitService = new ProduitService();
		produitService.supprimer(this.id);
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ProduitDto modifier(Produit produit) {
		ProduitService produitService = new ProduitService();
		produit.setId(this.id);
		if (! produitService.modifier(produit)) {
			throw new NotFoundException();
		}
		return new ProduitDto(produit, uriInfo.getRequestUriBuilder().build());
	}

}
