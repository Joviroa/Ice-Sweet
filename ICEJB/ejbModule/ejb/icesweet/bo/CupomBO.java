package ejb.icesweet.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.icesweet.dao.CupomDAO;
import ejb.icesweet.entidade.Cupom;

@Stateless
public class CupomBO {
	@EJB
	private CupomDAO cupomDAO;
	
	public Cupom buscarCupomAtivo() {
		return cupomDAO.buscarCupomAtivo();
	}

	public List<Cupom> buscarListaCupons() {
		return cupomDAO.buscaTodos(Cupom.class);
	}

	public void excluirCupom(Cupom cupom) {
		cupomDAO.remove(cupom);
	}

	public void salvarCupom(Cupom cupom) {
		if(cupom.getId() == null) {
			cupomDAO.persiste(cupom);
		}else {
			cupomDAO.atualiza(cupom);
		}
	}
}
