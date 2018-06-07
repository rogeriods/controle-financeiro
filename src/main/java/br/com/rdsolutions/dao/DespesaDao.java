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
package br.com.rdsolutions.dao;

import java.math.BigDecimal;
import java.util.List;

import br.com.rdsolutions.beans.Despesa;

/**
 * Interface with methods signatures.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public interface DespesaDao {
	
	// Those signatures return a list of an object
	public List<Despesa> listaTodas(int idUsuario);
	public List<Despesa> listaTodasVencidas(int idUsuario);
	public List<Despesa> listaTodasAbertas(int idUsuario);
	public List<Despesa> listaTodasAbertasDoMes(int idUsuario);
	public List<Despesa> listaTodasPagasDoMes(int idUsuario);
	public List<Despesa> listaTodasDoMes(int idUsuario);	
	
	// This signature return an object
	public Despesa selecionaDespesa(int id);
	
	// Those signatures return the sum of my finances
	public BigDecimal totalVencidas(int idUsuario);
	public BigDecimal totalMes(int idUsuario);
	public BigDecimal totalPagaMes(int idUsuario);
	public BigDecimal total(int idUsuario);

	// Those signatures implement the CRUD actions
	public void insereNovo(int idUsuario, Despesa objDespesa);
	public void atualiza(Despesa objDespesa);
	public void deleta(Despesa objDespesa);
}
