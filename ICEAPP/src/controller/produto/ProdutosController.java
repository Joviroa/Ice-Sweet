package controller.produto;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import controller.BaseController;
import ejb.icesweet.bo.ProdutoBO;
import ejb.icesweet.entidade.Produto;
import web.exception.ICException;

@SuppressWarnings("serial")
@Named
@ViewScoped
public class ProdutosController extends BaseController{
	
	@EJB
	private ProdutoBO produtoBO;
	
	private List<Produto> produtos;
	private Produto produto;
	
	@PostConstruct
	private void init() {
		listarProdutos();
	}
	
	public void listarProdutos() {
		setProdutos(produtoBO.listarProdutos());
	}

    public void novo() {
        produto = new Produto();
    }

    public void salvar() {
    	validarProduto();
    	
        produtoBO.salvarProduto(produto);
        setProduto(null);
        setProdutos(null);
        // exibir mensagem que o produto foi adicionado com sucesso.
    }
    
    public void adicionarProduto() {
    	
    }
    
    public void excluirProduto(Produto produto) {
    	
    }
    
    public void editarProduto(Produto produto) {
    	
    }
    
    private void validarProduto() {
    	ICException erros = new ICException();
    	
    	if(true) {
    		
    	}
    	if(true) {
    		
    	}
    	
    	if(!erros.getListaErros().isEmpty()) {
    		this.exibirErros(erros);
    	}
    }

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}
	
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
    
}

