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
				+ "WHERE cupom.ativo = true";
		return query;
	}
	
	public void removerCupom(Cupom cupom) {
		Cupom aux = busca(cupom.getClass(), cupom.getId());
		remove(aux);
	}
	
//	private void inicializaGrafo(Cupom cupom) {
//		if (cupom == null) {
//			return;
//		}
//		Hibernate.initialize(cupom());
//	}
}
