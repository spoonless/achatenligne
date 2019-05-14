package com.achatenligne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.achatenligne.model.Commande;
import com.achatenligne.model.Produit;

public class CommandeDao {
	
	public void enregistrer(Commande commande) {
		try(Connection connection = ProduitDataSource.getSingleton().getConnection()) {
			try(Statement statement = connection.createStatement()) {
				statement.executeUpdate("insert into commande () values ()");
				
				// insert into ligne_commande (commande_id, produit_id) values ()
				try(PreparedStatement pStatement = connection.prepareStatement("insert into ligne_commande "
						                                                     + " (commande_id, produit_id) "
						                                                     + " values(last_insert_id(), ?)")){
					for(Produit produit : commande.getProduits()) {
						pStatement.setInt(1, produit.getId());
						pStatement.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
