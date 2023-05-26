package ejb.icesweet.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ejb.icesweet.entidade.Status;

@Stateless
public class StatusDAO extends DAO{

	public Status buscarStatusPorCodigo(String codigo) {
		try {
			Query query = getEntityManager().createQuery(queryBuscarStatusPorCodigo());
			query.setParameter("codigo", codigo);
			Status status = (Status)query.getSingleResult();
			return status;
		}catch(NoResultException e) {
			return null;
		}
	}

	private String queryBuscarStatusPorCodigo() {
		String query = "SELECT status "
				+ "FROM Status status "
				+ "WHERE status.status = :codigo";
		return query;
	}
	
}
