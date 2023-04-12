package ejb.icesweet.bo;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import ejb.icesweet.dao.ProdutoDAO;
import ejb.icesweet.entidade.Produto;

@Stateless
public class ProdutoBO {

	@EJB
	private ProdutoDAO produtoDAO;
	
	
	public Produto salvarProduto(Produto produto) {
		
		return null;
	}
	
	public List<Produto> listarProdutos() {
		
		return null; /* produtoDAO.listarProdutos(); */
	}
}
