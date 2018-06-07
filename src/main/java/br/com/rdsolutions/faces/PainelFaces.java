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

import java.math.BigDecimal;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.rdsolutions.beans.Despesa;
import br.com.rdsolutions.dao.DespesaDao;
import br.com.rdsolutions.dao.DespesaDaoImpl;
import br.com.rdsolutions.dao.ReceitaDao;
import br.com.rdsolutions.dao.ReceitaDaoImpl;

/**
 * Class responsible to make the communication FRONTEND to BACKAND codes.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class PainelFaces {

	// List to populate my data table
	private List<Despesa> lstDespesas = null;

	// Amount variables
	private BigDecimal totalDesVencidas = BigDecimal.ZERO;
	private BigDecimal totalDesMes = BigDecimal.ZERO;
	private BigDecimal totalDesPagMes = BigDecimal.ZERO;
	private BigDecimal totalRecMes = BigDecimal.ZERO;
	private BigDecimal saldoMes = BigDecimal.ZERO;
	private BigDecimal saldoGeral = BigDecimal.ZERO;

	// User control variable
	private static int idUsuario = 0;

	// DAO instances
	private DespesaDao daoDespesa = new DespesaDaoImpl();
	private ReceitaDao daoReceita = new ReceitaDaoImpl();

	// Session variable control
	HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	// Constructor
	public PainelFaces() {
		// Get the user id from sessions
		idUsuario = Integer.parseInt(session.getAttribute("userid").toString());				
	}

	// Getters and setters
	public List<Despesa> getLstDespesasVencidas() {
		lstDespesas = daoDespesa.listaTodasVencidas(idUsuario);
		return lstDespesas;
	}
	public void setLstDespesas(List<Despesa> lstDespesas) {
		this.lstDespesas = lstDespesas;
	}
	
	public BigDecimal getTotalDesVencidas() {
		totalDesVencidas = daoDespesa.totalVencidas(idUsuario);
		return totalDesVencidas;
	}
	public void setTotalDesVencidas(BigDecimal totalDesVencidas) {
		this.totalDesVencidas = totalDesVencidas;
	}
	
	public BigDecimal getTotalDesMes() {
		totalDesMes = daoDespesa.totalMes(idUsuario);
		return totalDesMes;
	}
	public void setTotalDesMes(BigDecimal totalDesMes) {
		this.totalDesMes = totalDesMes;
	}
	
	public BigDecimal getTotalRecMes() {
		totalRecMes = daoReceita.totalMes(idUsuario);
		return totalRecMes;
	}
	public void setTotalRecMes(BigDecimal totalRecMes) {
		this.totalRecMes = totalRecMes;
	}
	
	public BigDecimal getSaldoMes() {
		BigDecimal totalRMes = daoReceita.totalMes(idUsuario);
		BigDecimal totalDPagMes = daoDespesa.totalPagaMes(idUsuario);
		saldoMes = totalRMes.subtract(totalDPagMes);
		return saldoMes;
	}
	public void setSaldoMes(BigDecimal saldoMes) {
		this.saldoMes = saldoMes;
	}
	
	public BigDecimal getTotalDesPagMes() {
		totalDesPagMes = daoDespesa.totalPagaMes(idUsuario);
		return totalDesPagMes;
	}
	public void setTotalDesPagMes(BigDecimal totalDesPagMes) {
		this.totalDesPagMes = totalDesPagMes;
	}
	
	public BigDecimal getSaldoGeral() {
		BigDecimal totalR = daoReceita.total(idUsuario);
		BigDecimal totalDP = daoDespesa.total(idUsuario);
		saldoGeral = totalR.subtract(totalDP);
		return saldoGeral;
	}
	public void setSaldoGeral(BigDecimal saldoGeral) {
		this.saldoGeral = saldoGeral;
	}
}
