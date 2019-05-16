package com.achatenligne.web.api;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.achatenligne.model.Produit;
import com.achatenligne.model.ProduitService;

@Path("/produits")
public class ListeProduitsResource {
	
	private UriInfo uriInfo;
	
	public ListeProduitsResource(@Context UriInfo uriInfo) {
		this.uriInfo = uriInfo;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProduitDto> getAll() {
		ProduitService produitService = new ProduitService();
		return ProduitDto.asList(produitService.getAll(), uriInfo);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response ajouter(Produit produit) {
		ProduitService produitService = new ProduitService();
		produitService.ajouter(produit);
		URI uri = uriInfo.getRequestUriBuilder().path(String.valueOf(produit.getId())).build();
		return Response.created(uri).entity(produit).build();
	}

}