package web.exception;

import java.util.List;

@SuppressWarnings("serial")
public class ICException extends Exception {
	private String mensagemErro;
	private List<String> listaErros;
	
	public ICException() {
		
	} 
	public ICException(String mensagemErro) {
		setMensagemErro(mensagemErro);
	}
	
	public String getMensagemErro() {
		return mensagemErro;
	}
	public void setMensagemErro(String mensagemErro) {
		this.mensagemErro = mensagemErro;
	}
	public List<String> getListaErros() {
		return listaErros;
	}
	public void setListaErros(List<String> listaErros) {
		this.listaErros = listaErros;
	}
	
}
