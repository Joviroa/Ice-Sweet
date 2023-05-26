package controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import exceptions.ICException;
import utils.JSFUtils;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class BaseController implements Serializable {
	
	private List<String> listaMensagemErro = new ArrayList<String>();
	private String mensagemErro;
	
	public void redirecionarComandas() {
		redireciona("/pages/comandas.xhtml");
	}
	
	public void redirecionarCliente() {
		redireciona("/pages/clientes.xhtml");
	}
	
	public void redirecionarCupons() {
		redireciona("/pages/cupons.xhtml");
	}
	
	public void exibirErros(ICException erro) {
		
	}
	
	public void redireciona(String pagina) {
		try {
			JSFUtils.redireciona(JSFUtils.getRequest().getContextPath() + pagina);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void setarObjetoNoFlash(Object object, String nomeParametro) {
		JSFUtils.put(nomeParametro, object);
	}
	
	public void redirecionaPaginaExterna(String url) {		
        FacesContext context = FacesContext.getCurrentInstance();	      
        try {
            context.getExternalContext().redirect(url);
        } catch (IOException ex) {
            //Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        	System.out.println("Erro ao redirecionar para a url: " + url +".");
        }	    
	}
	
	public void fechaStatusDialog() {
		PrimeFaces.current().executeScript("PF('statusDialog').hide();");
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}

	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}

	public List<String> getListaMensagemErro() {
		return listaMensagemErro;
	}

	public void setListaMensagemErro(List<String> listaMensagemErro) {
		this.listaMensagemErro = listaMensagemErro;
	}
	
}
