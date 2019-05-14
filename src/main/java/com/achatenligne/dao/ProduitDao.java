package com.achatenligne.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.achatenligne.model.Produit;

public class ProduitDao {

	private static final String SELECT_ALL_PRODUITS = "select id, code, libelle, prix from produit";

	public List<Produit> getAll() {
		try (Connection connection = ProduitDataSource.getSingleton().getConnection();
				Statement stmt = connection.createStatement()) {

			List<Produit> produits = new ArrayList<Produit>();
			try (ResultSet rs = stmt.executeQuery(SELECT_ALL_PRODUITS)) {
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

	public Optional<Produit> getById(int id) {
		try (Connection connection = ProduitDataSource.getSingleton().getConnection();
			 Statement stmt = connection.createStatement()) {

			try (ResultSet rs = stmt.executeQuery(SELECT_ALL_PRODUITS + " where id = " + id)) {
				if (rs.next()) {
					Produit p = new Produit(rs.getInt("id"), rs.getString("code"), rs.getString("libelle"),
							rs.getBigDecimal("prix"));
					return Optional.of(p);
				}
			}
			return Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
