package controllers;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.icesweet.bo.ComandasBO;
import ejb.icesweet.entidade.Cliente;
import ejb.icesweet.entidade.Comanda;
import ejb.icesweet.entidade.Cupom;
import exceptions.ICException;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class ComandaController extends BaseController{
	
	private Comanda comanda;
	private Cupom cupomAtivo;
	private Set<Comanda> listaComandas = new HashSet<Comanda>();
	
	private Boolean inserirDadosComanda;
	private Boolean editarDadosComanda;
	private Boolean edicaoComanda;
	private Boolean comandaClienteCadastrado;
	private Boolean usarCupomFinalizarComanda;
	
	@EJB
	private ComandasBO comandasBO;
	
	@PostConstruct
	private void inicializar() {
		setComanda(new Comanda());
		inicializarBooleansTela();
		setCupomAtivo(comandasBO.buscarCupomAtivo());
		inicializarComandas();
		//getListaComandas().stream().map(comanda -> comanda.getStatus().getStatus()).forEach(System.out::println);;
	}
	
	public void novoClienteComanda(Comanda comanda) {
		setInserirDadosComanda(Boolean.TRUE);
		setEditarDadosComanda(Boolean.FALSE);
		carregarComanda(comanda);
	}
	
	public void editarClienteComanda(Comanda comanda) {
		setEditarDadosComanda(Boolean.TRUE);
		setInserirDadosComanda(Boolean.FALSE);
		setComanda(comanda);
	}
	
	public void visualizarComanda(Comanda comanda) {
		setEditarDadosComanda(Boolean.FALSE);
		setInserirDadosComanda(Boolean.FALSE);
		setComanda(comanda);
	}

	public void salvarComanda() {
		try {
			validarComanda();
			if(getEdicaoComanda()) {
				setEditarDadosComanda(Boolean.FALSE);
			}else {
				if(getComanda().clienteCadastrado()) {
					setarDadosComanda();
				}
				setInserirDadosComanda(Boolean.FALSE);
			}
			comandasBO.atualizarComanda(getComanda());
			System.out.println("Comanda salva com sucesso.");
			fecharModal();
			inicializarComandas();
		}catch(ICException ex) {
			System.out.println(ex.getListaErros());
		}finally {
			fechaStatusDialog();
		}
	}
	
	public void finalizarComanda() {
		try {
			if(comanda.clienteCadastrado() && comanda.cupomAtivo()) {
				if(getUsarCupomFinalizarComanda()) {
					comandasBO.processarDescontoCupomComanda(getComanda());
				}else {
					comandasBO.processaGanhoCupomCliente(getComanda()); 
				}
			}
			comandasBO.finalizarComanda(getComanda());
			System.out.println("Comanda finalizada com sucesso.");
			fecharModal();
			inicializarComandas();
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			fechaStatusDialog();
		}
	}

	
	public void fecharModal() {
		setComandaClienteCadastrado(Boolean.FALSE);
		setComanda(null);
	}
	
	
	private void setarDadosComanda() {
		getComanda().setCliente(comandasBO.buscarClientePorCPF(getComanda().getCliente().getCpf()));
	}

	public void validarComanda() throws ICException {
		ICException exception = new ICException();
		exception.instanciaListaErros();
		if(getComanda().clienteCadastrado()) {
			if(comandasBO.buscarClientePorCPF(getComanda().getCliente().getCpf()) == null){
				exception.adicionarErroLista("O CPF não foi encontrado, este cliente não é cadastrado.");
			}
		}
		if(getComanda().getValorTotal() == null || getComanda().getValorTotal().equals(BigDecimal.ZERO)) {
			exception.adicionarErroLista("O valor da comanda é obrigatório enão pode ser zero.");
		}
		if(getComanda().getNumero() == null) {
			 exception.adicionarErroLista("O número da comanda não pode ser inválido.");
		}
		if(!exception.getListaErros().isEmpty()) {
			throw exception;
		}
	}
	
	public String nomeCliente(Comanda comanda) {
		if(comanda.getCliente()!= null) {
			return comanda.getCliente().getNome();
		}else if(comanda.getNomeCliente() != null) {
			return comanda.getNomeCliente();
		}else {
			return null;
		}
	}
	
	public BigDecimal calcularValorTotalComanda() {
		BigDecimal valorTotal = BigDecimal.ZERO;
		if(getComanda() != null) {
			if(getComanda().clienteCadastrado() && !getComanda().getValorTotal().equals(BigDecimal.ZERO)) {
				if(getUsarCupomFinalizarComanda()) {
					valorTotal = getComanda().getValorTotal().subtract(getComanda().getCliente().getSaldoCupom());
				}else {
					valorTotal = getComanda().getValorTotal();
				}
			}else {
				valorTotal = getComanda().getValorTotal();
			}
		}
		return valorTotal;
	}
	
	public void setarClienteComanda() {
		getComanda().setCliente(new Cliente());
	}
	
	private void carregarComanda(Comanda comanda) {
		Comanda comandaAux = new Comanda();
		comandaAux.setId(comanda.getId());
		comandaAux.setCliente(comanda.getCliente());
		comandaAux.setCupom(getCupomAtivo());
		comandaAux.setNomeCliente("");
		comandaAux.setNumero(comanda.getNumero());
		comandaAux.setStatus(comanda.getStatus());
		comandaAux.setValorTotal(comanda.getValorTotal());
		setComanda(comandaAux);	
	}
	
	public String tituloModalComanda() {
		String titulo = "";
		if(getEditarDadosComanda().equals(Boolean.TRUE)) {
			titulo = "Editando dados na";
		}else if(getInserirDadosComanda().equals(Boolean.TRUE)) {
			titulo = "Inserindo dados na";
		}
		return titulo;
	}
	
//	private void limparDadosComanda() {
//		getComanda().setCliente(new Cliente());
//		getComanda().setNomeCliente(null);
//		getComanda().setStatus(comandasBO.buscarStatus(Status.Disponivel));
//		getComanda().setValorTotal(BigDecimal.ZERO);
//		getComanda().setCupom(null);
//	}
	
//	private void inicializarComanda() {
//		setComanda(new Comanda());
//		getComanda().setCliente(new Cliente());
//	}
	
	private void inicializarBooleansTela() {
		setInserirDadosComanda(Boolean.FALSE);
		setEditarDadosComanda(Boolean.FALSE);
		setEdicaoComanda(Boolean.FALSE);
		setComandaClienteCadastrado(Boolean.FALSE);
		setUsarCupomFinalizarComanda(Boolean.FALSE);
	}
	
	private void inicializarComandas() {
		setListaComandas(comandasBO.listarComandas());
	}
	
	public Boolean exibeBtnUsarCupom() {
		if(getComanda() != null && getComanda().getCliente() != null && getComanda().getCliente().getSaldoCupom() != null) {
			if(getComanda().clienteCadastrado() && getComanda().getCliente().getSaldoCupom().compareTo(new BigDecimal("1")) == 1) {
					return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
//	public void buscarMesasDisponiveis() {
//		
//	}
	
	public Comanda getComanda() {
		return comanda;
	}

	public void setComanda(Comanda comanda) {
		this.comanda = comanda;
	}

	public Cupom getCupomAtivo() {
		return cupomAtivo;
	}

	public void setCupomAtivo(Cupom cupomAtivo) {
		this.cupomAtivo = cupomAtivo;
	}

	public Set<Comanda> getListaComandas() {
		return listaComandas;
	}

	public void setListaComandas(Set<Comanda> listaComandas) {
		this.listaComandas = listaComandas;
	}
	
	public Boolean getInserirDadosComanda() {
		return inserirDadosComanda;
	}

	public void setInserirDadosComanda(Boolean inserirDadosComanda) {
		this.inserirDadosComanda = inserirDadosComanda;
	}

	public Boolean getEditarDadosComanda() {
		return editarDadosComanda;
	}

	public void setEditarDadosComanda(Boolean editarDadosComanda) {
		this.editarDadosComanda = editarDadosComanda;
	}

	public Boolean getEdicaoComanda() {
		return edicaoComanda;
	}

	public void setEdicaoComanda(Boolean edicaoComanda) {
		this.edicaoComanda = edicaoComanda;
	}

	public Boolean getComandaClienteCadastrado() {
		return comandaClienteCadastrado;
	}

	public void setComandaClienteCadastrado(Boolean comandaClienteCadastrado) {
		this.comandaClienteCadastrado = comandaClienteCadastrado;
	}

	public Boolean getUsarCupomFinalizarComanda() {
		return usarCupomFinalizarComanda;
	}

	public void setUsarCupomFinalizarComanda(Boolean usarCupomFinalizarComanda) {
		this.usarCupomFinalizarComanda = usarCupomFinalizarComanda;
	}
	
}
