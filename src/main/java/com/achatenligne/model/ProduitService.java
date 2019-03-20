package com.achatenligne.model;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {
	
	public List<Produit> getAll() {
		List<Produit> all = new ArrayList<Produit>();
		all.add(new Produit(1, "XTV-12", "Vélo", 250.00));
		all.add(new Produit(2, "CTK-55", "Bouillotte", 12.40));
		all.add(new Produit(3, "XGK-80", "Souris PC", 3.23));
		return all;
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
