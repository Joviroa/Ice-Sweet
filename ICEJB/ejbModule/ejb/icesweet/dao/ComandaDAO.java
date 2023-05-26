package ejb.icesweet.dao;

import java.util.List;

import javax.ejb.Stateless;

import ejb.icesweet.entidade.Comanda;

@Stateless
public class ComandaDAO extends DAO{

	public List<Comanda> buscarListaComandas(){
		return this.buscaTodos(Comanda.class);
	}
	
	public Comanda salvar(Comanda comanda) {
		if(comanda.getId() == null) {
			persiste(comanda);
			return null;
		}else{
			return atualiza(comanda);
		}
	}

}
