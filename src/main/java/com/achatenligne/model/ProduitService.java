package com.achatenligne.model;

import java.util.List;

import com.achatenligne.dao.ProduitDao;

public class ProduitService {
	
	public List<Produit> getAll() {
		ProduitDao produiDao = new ProduitDao();
		return produiDao.getAll();
	}

	public Produit ajouter(Commande commande, int idProduit) {
		for (Produit produit : getAll()) {
			if (produit.getId() == idProduit) {
				commande.add(produit);
				return produit;
			}
		}
		return null;
	}

}
