package controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.icesweet.bo.CupomBO;
import ejb.icesweet.entidade.Cupom;
import exceptions.ICException;
import utils.DataUtils;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class CupomController extends BaseController{
	
	@EJB
	private CupomBO cupomBO;
	
	private Cupom cupom;
	private List<Cupom> listaCupons;
	
	private Boolean editarCupom;
	
	@PostConstruct
	private void inicializar() {
		inicializarCupons();
		inicializarBooleans();
	}
	
	public void salvarCupom() {
		try{
		cupomBO.salvarCupom(getCupom());
		inicializarCupons();
		redefinirCupomModal();
		System.out.println("Cupom adicionado com sucesso.");
		}catch(Exception ex) {
			
		}finally {
			fechaStatusDialog();
		}
	}
	
	public void desativarOutrosCupons(Cupom cupom) {
		try {
			for (Cupom cupomAux : getListaCupons()) {
				if (!cupomAux.equals(cupom)) {
					cupomAux.setAtivo(Boolean.FALSE);
					cupomBO.salvarCupom(cupomAux);
				}
			}
			inicializarCupons();
		} catch (Exception ex) {

		} finally {
			fechaStatusDialog();
		}
	}
	
	public void carregarCupomModalEditar(Cupom cupom) {
		setCupom(cupom);
		setEditarCupom(Boolean.TRUE);
	}
	
	public void validarCupom() throws ICException {
		ICException ex = new ICException();
		ex.instanciaListaErros();
		
		if(getCupom().getDataInicio() == null) {
			ex.adicionarErroLista("A data de início do cupom é obrigatória, preencha para prosseguir.");
		}else {
			if(DataUtils.verificarDataDepoisDe(getCupom().getDataInicio(), getCupom().getDataFim())) {
				ex.adicionarErroLista("A data de início deve ser anterior ou igual a data de fim.");
			}
		}
		
		if(getCupom().getDataFim() == null) {
			ex.adicionarErroLista("A data de fim do cupom é obrigatória, preencha para prosseguir.");
		}else {
			if(DataUtils.verificarDataDepoisDe(getCupom().getDataFim(), getCupom().getDataInicio())) {
				ex.adicionarErroLista("A data de fim deve ser posterior ou igual data de início.");
			}
		}
		
		if(getCupom().getValorDesconto() == null) {
			ex.adicionarErroLista("O valor de desconto do cupom é obrigatório, preencha para prosseguir.");
		}
		
		if(!ex.getListaErros().isEmpty()) {
			throw ex;
		}
	}
	
	public void excluirCupom(Cupom cupom) {
		try {
			cupomBO.excluirCupom(cupom);
			inicializarCupons();
			System.out.println("Cupom removido com sucesso.");
		}catch(Exception ex) {
			//ex.printStackTrace();
		}finally {
			fechaStatusDialog();
		}
	}
	
	public void redefinirCupomModal() {
		setCupom(new Cupom());
	}
	
	private void inicializarBooleans() {
		setEditarCupom(Boolean.FALSE);
	}

	private void inicializarCupons() {
		setListaCupons(cupomBO.buscarListaCupons());
	}
	
	public Cupom getCupom() {
		return cupom;
	}

	public void setCupom(Cupom cupom) {
		this.cupom = cupom;
	}

	public List<Cupom> getListaCupons() {
		return listaCupons;
	}

	public void setListaCupons(List<Cupom> listaCupons) {
		this.listaCupons = listaCupons;
	}

	public Boolean getEditarCupom() {
		return editarCupom;
	}

	public void setEditarCupom(Boolean editarCupom) {
		this.editarCupom = editarCupom;
	}
	
}
