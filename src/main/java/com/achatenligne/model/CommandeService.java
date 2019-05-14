package com.achatenligne.model;

import com.achatenligne.dao.CommandeDao;

public class CommandeService {

	public void enregistrer(Commande commande) throws CommandeVideException {
		if (commande == null || commande.getProduits().isEmpty()) {
			throw new CommandeVideException("Votre commande est vide");
		}

		CommandeDao commandeDao = new CommandeDao();
		commandeDao.enregistrer(commande);
		
		commande.getProduits().clear();
		
	}

}
