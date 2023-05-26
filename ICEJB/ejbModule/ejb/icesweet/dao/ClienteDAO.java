package ejb.icesweet.dao;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import ejb.icesweet.entidade.Cliente;

@Stateless
public class ClienteDAO extends DAO{

	public Cliente buscarClientePorCPF(String cpf) {
		try {
			Query query = getEntityManager().createQuery(montaQueryBuscaClientePorCPF());
			query.setParameter("cpf", cpf);
			Cliente cliente = (Cliente)query.getSingleResult();
			return cliente;
		}catch(NoResultException e) {
			return null;
		}
	}

	private String montaQueryBuscaClientePorCPF() {
		return  "SELECT cliente "
				+ "FROM Cliente cliente "
				+ "WHERE cliente.cpf = :cpf";
	}
}
