package org.ppr.patterns.dao.impl;

import org.ppr.database.Database;
import org.ppr.entity.Relatorio;
import org.ppr.patterns.dao.impl.generic.GenericDaoImpl;

public class RelatorioDao extends GenericDaoImpl<Relatorio> {

	public RelatorioDao(Database database) {
		super(Relatorio.class, database);
	}

}
