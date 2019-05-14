package com.achatenligne.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.achatenligne.model.Produit;

public class ProduitDao {

	public List<Produit> getAll() {
		try (Connection connection = ProduitDataSource.getSingleton().getConnection(); 
		     Statement stmt = connection.createStatement()) {

			List<Produit> produits = new ArrayList<Produit>();
			try (ResultSet rs = stmt.executeQuery("select id, code, libelle, prix from produit")) {
				while (rs.next()) {
					Produit p = new Produit(rs.getInt("id"), rs.getString("code"), rs.getString("libelle"),
							rs.getBigDecimal("prix"));
					produits.add(p);
				}
			}
			return produits;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
