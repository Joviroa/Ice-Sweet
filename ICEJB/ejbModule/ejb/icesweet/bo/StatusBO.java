package ejb.icesweet.bo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.icesweet.dao.StatusDAO;
import ejb.icesweet.entidade.Status;

@Stateless
public class StatusBO {

	@EJB
	private StatusDAO statusDAO;
	
	public Status buscarStatusPorCodigo(String codigo) {
		return statusDAO.buscarStatusPorCodigo(codigo);
	}

}
