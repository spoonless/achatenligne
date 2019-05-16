package com.achatenligne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.achatenligne.model.Commande;
import com.achatenligne.model.Produit;

public class CommandeDao extends AbstractDao {
	
	public void enregistrer(Commande commande) {
		Connection connection = null;
		boolean transactionOk = false;
		try {
			connection = ProduitDataSource.getSingleton().getConnection();
			try(Statement statement = connection.createStatement()) {
	
				statement.executeUpdate("insert into commande () values ()", Statement.RETURN_GENERATED_KEYS);
					
				try(ResultSet rs = statement.getGeneratedKeys()) {
					if (rs.next()) {
						int commandeId = rs.getInt(1);
						insererLigneCommande(connection, commandeId, commande);
					}
				}
			}
			transactionOk = true;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			checkTransactionAndClose(connection, transactionOk);
		}
	}

	private void insererLigneCommande(Connection connection, int commandeId, Commande commande) throws SQLException {
		// insert into ligne_commande (commande_id, produit_id) values ()
		try(PreparedStatement pStatement = connection.prepareStatement("insert into ligne_commande "
				                                                     + " (commande_id, produit_id) "
				                                                     + " values(?, ?)")){
			pStatement.setInt(1, commandeId);
			for(Produit produit : commande.getProduits()) {
				pStatement.setInt(2, produit.getId());
				pStatement.executeUpdate();
			}
		}
	}

}
