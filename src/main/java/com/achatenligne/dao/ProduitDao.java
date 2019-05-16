package com.achatenligne.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.achatenligne.model.Produit;

public class ProduitDao extends AbstractDao {

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
					Produit p = new Produit(rs.getInt("id"), rs.getString("code"), 
							                rs.getString("libelle"), rs.getBigDecimal("prix"));
					return Optional.of(p);
				}
			}
			return Optional.empty();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void create(Produit produit) {
		String sql = "insert into produit (code, libelle, prix) values (?, ?, ?)";
		try {
			boolean transactionOk = false;
			Connection connection = ProduitDataSource.getSingleton().getConnection();
			try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				stmt.setString(1, produit.getCode());
				stmt.setString(2, produit.getLibelle());
				stmt.setBigDecimal(3, produit.getPrix());
				stmt.executeUpdate();
				try (ResultSet rs = stmt.getGeneratedKeys()) {
					if (rs.next()) {
						produit.setId(rs.getInt(1));
					}
				}
				transactionOk = true;
			} finally {
				checkTransaction(connection, transactionOk);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void update(Produit produit) {
		String sql = "update produit set code = ?, libelle = ?, prix = ? where id = ?";
		try {
			boolean transactionOk = false;
			Connection connection = ProduitDataSource.getSingleton().getConnection();
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, produit.getCode());
				stmt.setString(2, produit.getLibelle());
				stmt.setBigDecimal(3, produit.getPrix());
				stmt.setInt(4, produit.getId());
				stmt.executeUpdate();
				transactionOk = true;
			} finally {
				checkTransaction(connection, transactionOk);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void delete(Produit produit) {
		String sql = "delete from produit where id = ?";
		try {
			boolean transactionOk = false;
			Connection connection = ProduitDataSource.getSingleton().getConnection();
			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setInt(1, produit.getId());
				stmt.executeUpdate();
				transactionOk = true;
			} finally {
				checkTransaction(connection, transactionOk);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
