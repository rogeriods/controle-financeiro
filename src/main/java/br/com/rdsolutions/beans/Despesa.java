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
package br.com.rdsolutions.beans;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Class encapsulation.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class Despesa {
	
	private int id;
	private int idUsuario;
	private int idTipoDespesa;
	private String tipoDescricao;
	private Date data;
	private Date dataVencimento;
	private Date dataPagamento;
	private String descricao;
	private BigDecimal valor;
	
	// Getters and setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public int getIdTipoDespesa() {
		return idTipoDespesa;
	}
	public void setIdTipoDespesa(int idTipoDespesa) {
		this.idTipoDespesa = idTipoDespesa;
	}
	
	public String getTipoDescricao() {
		return tipoDescricao;
	}
	public void setTipoDescricao(String tipoDescricao) {
		this.tipoDescricao = tipoDescricao;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public Date getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
