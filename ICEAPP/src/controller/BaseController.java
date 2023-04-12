package controller;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import web.exception.ICException;

@SuppressWarnings("serial")
@Named
@ApplicationScoped
public class BaseController implements Serializable {
	
	public void exibirErros(ICException erro) {
		
	}
}
