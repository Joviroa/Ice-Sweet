package controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.icesweet.bo.ClienteBO;
import ejb.icesweet.entidade.Cliente;
import exceptions.ICException;
import utils.InputUtils;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class ClienteController extends BaseController{
	
	@EJB
	private ClienteBO clienteBO;

	private List<Cliente> listaClientes;
	private Cliente clienteAux;
	
	private Boolean ehEdicao;
	
	@PostConstruct
	private void inicializar() {
		carregarNovoClienteAux();
		inicializarListaCliente();
		inicializarBooleansTela();
	}

	public void salvarCliente() {
		try {
			validarCliente();
			clienteBO.salvarCliente(getClienteAux());
			carregarNovoClienteAux();
			inicializarListaCliente();
			System.out.println("Cliente salvo com sucesso.");
		}catch(ICException ex) {
			if(!ex.getListaErros().isEmpty()) {
				this.setListaMensagemErro(ex.getListaErros());
			}else {
				this.setMensagemErro(ex.getMensagemErro());
			}
		}finally {
			fechaStatusDialog();
		}
	}
	
	public void excluirCliente(Cliente cliente) {
		try {
			clienteBO.excluirCliente(cliente);
			inicializarListaCliente();
			System.out.println("Cliente removido com sucesso.");
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			fechaStatusDialog();
		}
	}
	
	private void validarCliente() throws ICException{
		ICException exception = new ICException();
		exception.instanciaListaErros();
		if(getClienteAux() == null) {
			return;
		}
		if(!InputUtils.validarString(getClienteAux().getNome())) {
			exception.adicionarErroLista("O nome do cliente não é válido, está vazio ou contém números.");
		}
		
		if(getClienteAux().getCpf() == null || !InputUtils.validarCPF(getClienteAux().getCpf())) {
			exception.adicionarErroLista("O CPF inserido não é válido. ");
		}
		
		if(getClienteAux().getTelefone() != null) {
			if(!InputUtils.validarTelefone(getClienteAux().getTelefone())) {
				exception.adicionarErroLista("O telefone inserido é inválido.");
			}
			
		}
		
		if(!exception.getListaErros().isEmpty()) {
			throw exception;
		}
	}
	
	
	public void carregarNovoClienteAux() {
		setClienteAux(new Cliente());
	}
	
	public void carregarClienteModalEditar(Cliente cliente) {
		setClienteAux(cliente);
	}
	
	public void fecharModal() {
		setEhEdicao(Boolean.FALSE);
		setClienteAux(new Cliente());
	}
	
	private void inicializarListaCliente() {;
		setListaClientes(clienteBO.listarTodosClientes());
	}
	
	private void inicializarBooleansTela() {
		setEhEdicao(Boolean.FALSE);
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public Cliente getClienteAux() {
		return clienteAux;
	}

	public void setClienteAux(Cliente clienteAux) {
		this.clienteAux = clienteAux;
	}

	public Boolean getEhEdicao() {
		return ehEdicao;
	}

	public void setEhEdicao(Boolean ehEdicao) {
		this.ehEdicao = ehEdicao;
	}

	
}
