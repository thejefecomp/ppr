package org.ppr.patterns.dao.impl;

import org.ppr.database.Database;
import org.ppr.entity.Conta;
import org.ppr.patterns.dao.impl.generic.GenericDaoImpl;

public class ContaDao extends GenericDaoImpl<Conta> {

	public ContaDao(Database database) {
		super(Conta.class, database);
	}
}
