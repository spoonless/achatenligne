package com.achatenligne.model;

import java.util.List;
import java.util.Optional;

import com.achatenligne.dao.ProduitDao;

public class ProduitService {
	
	public List<Produit> getAll() {
		ProduitDao produitDao = new ProduitDao();
		return produitDao.getAll();
	}

	public Commande creerCommande(int...idProduits) {
		Commande commande = new Commande();
		for (int idProduit : idProduits) {
			ajouter(commande, idProduit);
		}
		return commande;
	}

	private void ajouter(Commande commande, int idProduit) {
		ProduitDao produitDao = new ProduitDao();
		Optional<Produit> produit = produitDao.getById(idProduit);
		
		if (produit.isPresent()) {
			commande.add(produit.get());
		}
	}
}
