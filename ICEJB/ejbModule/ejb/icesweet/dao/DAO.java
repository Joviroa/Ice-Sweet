package ejb.icesweet.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

public abstract class DAO {
	
	private static EntityManagerFactory emf;

	public static EntityManager getEntityManager() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("icedb");
		}
		return emf.createEntityManager();
	}

	public <T> T busca(Class<T> classe, Object id) {
		return getEntityManager().find(classe, id);
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> buscaTodos(Class<T> classe) {
		return getEntityManager().createQuery("SELECT E FROM " + classe.getSimpleName() + " E").getResultList();
	}

	public <T> void recarrega(T entidade) {
		getEntityManager().refresh(entidade);
	}

	public <T> void persiste(T entidade) {
		getEntityManager().persist(entidade);
	}

	public <T> T atualiza(T entidade) {
		try {
			return getEntityManager().merge(entidade);
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public <T> void remove(T entidade) {
		if (!getEntityManager().contains(entidade)) {
			entidade = getEntityManager().merge(entidade);
		}
		getEntityManager().remove(entidade);
	}
	
	
}
