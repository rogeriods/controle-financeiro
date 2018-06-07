/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.rdsolutions.faces;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.servlet.http.HttpSession;

import br.com.rdsolutions.beans.Usuario;
import br.com.rdsolutions.dao.UsuarioDao;
import br.com.rdsolutions.dao.UsuarioDaoImpl;
import br.com.rdsolutions.utils.SessionUtils;

/**
 * Class responsible to make the communication FRONTEND to BACKAND codes.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class LoginFaces {

	// Private variables
	private String email;
	private String senha;
	private String messages;

	// DAO instances
	private Usuario objUsuario = new Usuario();
	private UsuarioDao daoUsuario = new UsuarioDaoImpl();

	// Form actions
	public String preparaParaInserir() {
		objUsuario = new Usuario();
		return "gerenciaUsuario";
	}

	public String insereNovo() {
		daoUsuario.insereNovo(objUsuario);
		return "novoUsuario";
	}

	public String atualizar() {
		daoUsuario.atualiza(objUsuario);
		return "atualizar";
	}

	public String esqueceuSenha() {
		daoUsuario.esqueceuSenha(email);
		return "esqueceuSenha";
	}

	// Validate e-mail and password
	public String validaEmailSenha() {
		boolean valida = daoUsuario.validaEmailSenha(email, senha);
		if(valida) {
			// If true fill my object
			objUsuario = daoUsuario.selecionaPorEmailSenha(email, senha);	
			// Set session and goes to home
			HttpSession session = SessionUtils.getSession();
			session.setAttribute("username", objUsuario.getEmail());
			session.setAttribute("userid", objUsuario.getId());
			return "home.xhtml";			
		} else {
			FacesContext.getCurrentInstance().addMessage(null,	
					new FacesMessage(FacesMessage.SEVERITY_WARN, 
							"Dados incorretos!", "Por favor tente novamente."));
			return "login.xhtml";
		}	
	}

	// Validate e-mail
	public void validaEmail(ComponentSystemEvent event) {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();
		// Looking for txt_email component
		UIInput uiInputEmail = (UIInput) components.findComponent("txt_email");
		String email = uiInputEmail.getLocalValue() == null ? "" : uiInputEmail.getLocalValue().toString();
		String emailId = uiInputEmail.getClientId();
		// Check if is empty
		if(email.isEmpty()) {
			return;
		}
		boolean valida = daoUsuario.validaEmail(email);
		// If is true the e-mail exists and return an alert message
		if(valida) {
			FacesMessage msg = new FacesMessage("Este e-mail já existe em nossos cadastros.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(emailId, msg);
			fc.renderResponse();	
		}
	}

	// Validate the password changing
	public void validaAlteracaoSenha(ComponentSystemEvent event) {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIComponent components = event.getComponent();
		// Looking for txt_email component
		UIInput uiInputEmail = (UIInput) components.findComponent("txt_email");
		String email = uiInputEmail.getLocalValue() == null ? "" : uiInputEmail.getLocalValue().toString();
		String emailId = uiInputEmail.getClientId();
		// Check if is empty
		if(email.isEmpty()) {
			return;
		}
		boolean valida = daoUsuario.validaEmail(email);
		// If is true the e-mail exists and return an alert message
		if(!valida) {
			FacesMessage msg = new FacesMessage("Este e-mail n�o existe em nossa base de dados.");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(emailId, msg);
			fc.renderResponse();	
		}
	}

	// Kill the session
	public String logout() {
		HttpSession session = SessionUtils.getSession();
		session.invalidate();
		return "login.xhtml";
	}

	// Getters and setters
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
	
	public void setObjUsuario(Usuario objUsuario) {
		this.objUsuario = objUsuario;
	}
	public Usuario getObjUsuario() {
		return objUsuario;
	}
}
