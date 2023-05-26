package ejb.icesweet.bo;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.icesweet.dao.ClienteDAO;
import ejb.icesweet.entidade.Cliente;

@Stateless
public class ClienteBO {

	@EJB
	private ClienteDAO clienteDAO;

	public void salvarCliente(Cliente cliente) {
		if(cliente.getId() == null) {
			cliente.setValorCupomAcumulado(BigDecimal.ZERO);
			cliente.setSaldoCupom(BigDecimal.ZERO);
			clienteDAO.persiste(cliente);
		}else {
			clienteDAO.atualiza(cliente);
		}
	}
	
	public void excluirCliente(Cliente cliente) {
		clienteDAO.remove(cliente);
	}
	
	public Cliente buscarClientePorCPF(String cpf) {
		return clienteDAO.buscarClientePorCPF(cpf);
	}

	public List<Cliente> listarTodosClientes() {
		return clienteDAO.buscaTodos(Cliente.class);
	}
	
	
}
