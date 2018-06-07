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

import br.com.rdsolutions.beans.TipoDespesa;
import br.com.rdsolutions.beans.TipoReceita;
import br.com.rdsolutions.dao.TiposDespesasDao;
import br.com.rdsolutions.dao.TiposDespesasDaoImpl;
import br.com.rdsolutions.dao.TiposReceitasDao;
import br.com.rdsolutions.dao.TiposReceitasDaoImpl;

/**
 * Class responsible to make the communication FRONTEND to BACKAND codes.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class TiposDRFaces {

	// List to populate my select one list
	private List<TipoDespesa> lstDespesas;
	private List<TipoReceita> lstReceitas;

	// DAO instances
	private TiposDespesasDao daoTipoDespesa = new TiposDespesasDaoImpl();
	private TiposReceitasDao daoTipoReceita = new TiposReceitasDaoImpl();

	// Constructor 
	public TiposDRFaces() {
		// Fill the select one lists
		lstDespesas = daoTipoDespesa.listaTodosTiposDespesas();
		lstReceitas = daoTipoReceita.listaTodosTiposReceitas();
	}

	// Getters and setters
	public List<TipoDespesa> getLstDespesas() {
		return lstDespesas;
	}
	public void setLstDespesas(List<TipoDespesa> lstDespesas) {
		this.lstDespesas = lstDespesas;
	}
	
	public List<TipoReceita> getLstReceitas() {
		return lstReceitas;
	}
	public void setLstReceitas(List<TipoReceita> lstReceitas) {
		this.lstReceitas = lstReceitas;
	}	
}
