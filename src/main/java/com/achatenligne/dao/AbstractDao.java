package com.achatenligne.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDao {

	protected void checkTransactionAndClose(Connection connection, boolean transactionOk) {
		try {
			if (connection !=null) {
				if (transactionOk) {
					connection.commit();
				} else {
					connection.rollback();
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// Que faire si la fermeture de la connexion echoue ?
				e.printStackTrace();
			}
		}
	}

}