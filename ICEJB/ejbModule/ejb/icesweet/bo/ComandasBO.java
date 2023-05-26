package ejb.icesweet.bo;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.icesweet.dao.ComandaDAO;
import ejb.icesweet.entidade.Cliente;
import ejb.icesweet.entidade.Comanda;
import ejb.icesweet.entidade.Cupom;
import ejb.icesweet.entidade.Status;

@Stateless
public class ComandasBO {
	
	@EJB
	private ComandaDAO comandaDAO;
	
	@EJB
	private CupomBO cupomBO;
	
	@EJB
	private ClienteBO clienteBO;
	
	@EJB
	private StatusBO statusBO;
	
    public ComandasBO() {
    }
    
    public void salvarComanda(Comanda comanda) {
    	comandaDAO.salvar(comanda);
    }
    
    public Cupom buscarCupomAtivo() {
    	return cupomBO.buscarCupomAtivo();
    }

	public Set<Comanda> listarComandas() {
		return new HashSet<Comanda>(comandaDAO.buscarListaComandas());
	}

	public Cliente buscarClientePorCPF(String cpf) {
		return clienteBO.buscarClientePorCPF(cpf);
	}

	public void atualizarComanda(Comanda comanda) {
		comanda.setStatus(buscarStatus(Status.Ocupado));
		comandaDAO.atualiza(comanda);
	}

	public void novoCliente(Comanda comanda) {
		comanda.setStatus(buscarStatus(Status.Ocupado));
		salvarComanda(comanda);
	}
	
	public void finalizarComanda(Comanda comanda) {
		salvarComanda(comanda);
		comanda.setCliente(null);
		comanda.setNomeCliente(null);
		comanda.setStatus(buscarStatus(Status.Disponivel));
		comanda.setValorTotal(BigDecimal.ZERO);
		salvarComanda(comanda);
	}

	public Status buscarStatus(String codigo) {
		return statusBO.buscarStatusPorCodigo(codigo);
	}
	
	public void processaGanhoCupomCliente(Comanda comanda) {
		BigDecimal valorElegivelCupom = comanda.getCupom().getValorElegivel();
		BigDecimal valorDescontoCupom = comanda.getCupom().getValorDesconto();
		BigDecimal valorComanda = comanda.getValorTotal();
		BigDecimal valorCupomAcumuladoCliente = comanda.getCliente().getValorCupomAcumulado();
		
		if(valorCupomAcumuladoCliente.compareTo(valorElegivelCupom) == -1) {
			//Caso o cliente não tenha ainda o valor necessário pra ganhar o desconto, adicionamos ao valorAcumlado.
			if(valorElegivelCupom.compareTo(valorCupomAcumuladoCliente.add(valorComanda)) != 1) { // Caso o elegível não seja maior que o acumulado + totalComanda
				comanda.getCliente().setValorCupomAcumulado(comanda.getCliente().getValorCupomAcumulado().add(valorComanda));
				comanda.getCliente().setValorCupomAcumulado(comanda.getCliente().getValorCupomAcumulado().subtract(valorElegivelCupom));
				comanda.getCliente().setSaldoCupom(comanda.getCliente().getSaldoCupom().add(valorDescontoCupom));
			}else {
				comanda.getCliente().setValorCupomAcumulado(comanda.getCliente().getValorCupomAcumulado().add(valorComanda));
			}
			
		}else {
			//Caso o valor do cupom Acumulado Cliente seja maior que valor elegível do cupom ele adiciona o cupom.
			comanda.getCliente().setValorCupomAcumulado(comanda.getCliente().getValorCupomAcumulado().subtract(valorElegivelCupom));;
			comanda.getCliente().setSaldoCupom(comanda.getCliente().getSaldoCupom().add(valorDescontoCupom));;
		}
	}
	
	public void processarDescontoCupomComanda(Comanda comanda) {
		BigDecimal valorComanda = comanda.getValorTotal();
		BigDecimal valorCupom = comanda.getCliente().getSaldoCupom();
		if(valorComanda.compareTo(valorCupom) == -1 || valorComanda.compareTo(valorCupom) == 0) {
			comanda.getCliente().setSaldoCupom(valorCupom.subtract(valorComanda));
			comanda.setValorTotal(BigDecimal.ZERO);
		}else if(valorComanda.compareTo(valorCupom) == 1) {
			comanda.setValorTotal(valorComanda.subtract(valorCupom));
			comanda.getCliente().setSaldoCupom(valorCupom.subtract(valorComanda));
		}
	}
	
}
