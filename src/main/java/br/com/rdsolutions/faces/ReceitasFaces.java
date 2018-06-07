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

import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.rdsolutions.beans.Receita;
import br.com.rdsolutions.dao.ReceitaDao;
import br.com.rdsolutions.dao.ReceitaDaoImpl;

/**
 * Class responsible to make the communication FRONTEND to BACKAND codes.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class ReceitasFaces {

	// List to populate my data table
	private List<Receita> lstReceitas = null;

	// User control variable
	private static int idUsuario = 0;

	// Filters tag
	private int tipoFiltro = 0;

	// DAO instances
	private Receita objReceita = new Receita();
	private ReceitaDao daoReceita = new ReceitaDaoImpl();

	// Session variable control
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	// Constructor
	public ReceitasFaces() {
		// Get the user id from sessions
		idUsuario = Integer.parseInt(session.getAttribute("userid").toString());
	}

	// Filters
	public void filtrar() {
		getLstReceitas();
	}

	// Form actions
	public String preparaParaInserir() {
		objReceita = new Receita();
		return "receitas_form.xhtml";
	}

	public String preparaParaDeletar(int id) {
		objReceita = daoReceita.selecionaReceita(id);
		return "receitas_form_delete.xhtml";
	}

	public String preparaParaEditar(int id) {
		objReceita = daoReceita.selecionaReceita(id);
		return "receitas_form.xhtml";
	}

	public String gravar() {
		if(objReceita.getId() != 0) {
			daoReceita.atualiza(objReceita);
		} else {
			daoReceita.insereNovo(idUsuario, objReceita);
		}
		return "receitas.xhtml";
	}

	public String deleta() {
		daoReceita.deleta(objReceita);
		return "receitas.xhtml";
	}

	// Getters and setters
	public List<Receita> getLstReceitas() {
		switch (tipoFiltro) {
		case 1 :
			lstReceitas = daoReceita.listaTodas(idUsuario);
			break;	
		case 2 :
			lstReceitas = daoReceita.listaTodasDoMes(idUsuario);
			break;
		default:
			lstReceitas = daoReceita.listaTodasDoMes(idUsuario);
			break;
		}
		return lstReceitas;	
	}
	public void setLstReceitas(List<Receita> lstReceitas) {
		this.lstReceitas = lstReceitas;
	}
	
	public int getTipoFiltro() {
		return tipoFiltro;
	}
	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
	
	public Receita getObjReceita() {
		return objReceita;
	}
	public void setObjReceita(Receita objReceita) {
		this.objReceita = objReceita;
	}
}
