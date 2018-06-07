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

import br.com.rdsolutions.beans.Despesa;
import br.com.rdsolutions.dao.DespesaDao;
import br.com.rdsolutions.dao.DespesaDaoImpl;

/**
 * Class responsible to make the communication FRONTEND to BACKAND codes.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class DespesasFaces {

	// List to populate my data table
	private List<Despesa> lstDespesas = null;

	// User control variable
	private static int idUsuario = 0;

	// Filters tag
	private int tipoFiltro = 0;

	// DAO instances
	private Despesa objDespesa = new Despesa();
	private DespesaDao daoDespesa = new DespesaDaoImpl();

	// Session variable control
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	// Constructor
	public DespesasFaces() {
		// Get the user id from sessions
		idUsuario = Integer.parseInt(session.getAttribute("userid").toString());
	}

	// Filters
	public void filtrar() {
		getLstDespesas();
	}

	// Form actions
	public String preparaParaInserir() {
		objDespesa = new Despesa();
		return "despesas_form.xhtml";
	}

	public String preparaParaDeletar(int id) {
		objDespesa = daoDespesa.selecionaDespesa(id);
		return "despesas_form_delete.xhtml";
	}

	public String preparaParaEditar(int id) {
		objDespesa = daoDespesa.selecionaDespesa(id);
		return "despesas_form.xhtml";
	}

	public String gravar() {
		if(objDespesa.getId() != 0) {
			daoDespesa.atualiza(objDespesa);
		} else {
			daoDespesa.insereNovo(idUsuario, objDespesa);
		}
		return "despesas.xhtml";
	}

	public String deleta() {
		daoDespesa.deleta(objDespesa);
		return "despesas.xhtml";
	}

	// Getters and setters
	public List<Despesa> getLstDespesas() {
		switch (tipoFiltro) {
		case 1 :
			lstDespesas = daoDespesa.listaTodas(idUsuario);
			break;	
		case 2 :
			lstDespesas = daoDespesa.listaTodasAbertas(idUsuario);
			break;		
		case 3 :
			lstDespesas = daoDespesa.listaTodasVencidas(idUsuario);
			break;
		case 4 :
			lstDespesas = daoDespesa.listaTodasAbertasDoMes(idUsuario);
			break;
		case 5 :
			lstDespesas = daoDespesa.listaTodasPagasDoMes(idUsuario);
			break;	
		case 6 :
			lstDespesas = daoDespesa.listaTodasDoMes(idUsuario);
			break;	
		default:
			lstDespesas = daoDespesa.listaTodasDoMes(idUsuario);
			break;
		}
		return lstDespesas;	 
	}
	public void setLstDespesas(List<Despesa> lstDespesas) {
		this.lstDespesas = lstDespesas;
	}
	
	public Despesa getObjDespesa() {
		return objDespesa;
	}
	public void setObjDespesa(Despesa objDespesa) {
		this.objDespesa = objDespesa;
	}
	
	public int getTipoFiltro() {
		return tipoFiltro;
	}
	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
}
