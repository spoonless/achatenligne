package com.achatenligne.model;

public class CommandeService {

	public void valider(Commande commande) throws CommandeVideException {
		if (commande == null || commande.getProduits().isEmpty()) {
			throw new CommandeVideException("Votre commande est vide");
		}
		// pour l'instant on vide la commande
		commande.getProduits().clear();
		
	}

}
