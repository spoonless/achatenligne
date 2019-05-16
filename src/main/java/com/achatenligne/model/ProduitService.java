package com.achatenligne.model;

import java.util.List;
import java.util.Optional;

import com.achatenligne.dao.ProduitDao;

public class ProduitService {
	
	public List<Produit> getAll() {
		ProduitDao produitDao = new ProduitDao();
		return produitDao.getAll();
	}
	
	public Optional<Produit> getById(int id) {
		ProduitDao produitDao = new ProduitDao();
		return produitDao.getById(id);
	}
	
	public void ajouter(Produit produit) {
		ProduitDao produitDao = new ProduitDao();
		produitDao.create(produit);
	}
	
	public boolean modifier(Produit produit) {
		ProduitDao produitDao = new ProduitDao();
		return produitDao.update(produit);
	}

	public void supprimer(int id) {
		ProduitDao produitDao = new ProduitDao();
		produitDao.delete(id);
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
