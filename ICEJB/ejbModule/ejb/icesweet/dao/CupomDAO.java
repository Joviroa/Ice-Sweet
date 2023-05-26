package ejb.icesweet.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ejb.icesweet.entidade.Cupom;

@Stateless
public class CupomDAO extends DAO {

	public Cupom buscarCupomAtivo() {
		try {
			Query query = getEntityManager().createQuery(queryBuscarCupomAtivo());
			Cupom cupom = (Cupom)query.getSingleResult();
			return cupom;
		}catch(NoResultException e) {
			return null;
		}
	}

	private String queryBuscarCupomAtivo() {
		String query = "SELECT cupom "
				+ "FROM Cupom cupom "
				+ "WHERE 1=1";
//				+ "WHERE cupom.data_inicio < now()"
//				+ "AND cupom.data_fim is NULL";
		return query;
	}
	
//	private void inicializaGrafo(Cupom cupom) {
//		if (cupom == null) {
//			return;
//		}
//		Hibernate.initialize(cupom());
//	}
}
