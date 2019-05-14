package com.achatenligne.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProduitDataSource {
	
	private static final String DATASOURCE_NAME = "bdd/produits";
	private static ProduitDataSource singleton = new ProduitDataSource();
	
	public static ProduitDataSource getSingleton() {
		return singleton;
	}
	
	private DataSource dataSource;

	private ProduitDataSource() {
		try {
			Context envContext = InitialContext.doLookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup(DATASOURCE_NAME);
		} catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

}
