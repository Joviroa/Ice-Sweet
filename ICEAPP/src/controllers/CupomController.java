package controllers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import ejb.icesweet.bo.CupomBO;
import ejb.icesweet.entidade.Cupom;
import exceptions.ICException;

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
		cupomBO.salvarCupom(getCupom());
	}
	
	public void desativarOutrosCupons(Cupom cupom) {
		for(Cupom cupomAux: getListaCupons()){
			if(!cupomAux.equals(cupom)) {
				cupomAux.setAtivo(Boolean.FALSE);
			}
		}
	}
	
	public void carregarCupomModalEditar(Cupom cupom) {
		setCupom(cupom);
		setEditarCupom(Boolean.TRUE);
	}
	
	public void excluirCupom(Cupom cupom) {
		try {
			cupomBO.excluirCupom(cupom);
			System.out.println("Cupom removido com sucesso.");
		}catch(Exception ex) {
			ex.printStackTrace();
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
