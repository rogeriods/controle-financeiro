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

/**
 * Class that hold static final string queries SQL. 
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class ReceitaQueries {
	
	// Constant variables with SQL queries
	public static final String LISTA_TODAS = "SELECT a.id, a.id_usuario, a.id_tipo_receita, "
			+ "b.descricao AS tipo_descricao, a.data, a.data_recebimento, a.descricao, a.valor "
			+ "FROM ctlf_receitas a, ctlf_tipos_receitas b "
			+ "WHERE a.id_tipo_receita = b.id "
			+ "AND a.id_usuario = ? "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_recebimento)";
		
	public static final String LISTA_TODAS_DO_MES = "SELECT a.id, a.id_usuario, a.id_tipo_receita, "
			+ "b.descricao AS tipo_descricao, a.data, a.data_recebimento, a.descricao, a.valor "
			+ "FROM ctlf_receitas a, ctlf_tipos_receitas b "
			+ "WHERE a.id_tipo_receita = b.id "
			+ "AND a.id_usuario = ? "
			+ "AND YEAR(a.data_recebimento) = YEAR(CURDATE()) "
			+ "AND MONTH(a.data_recebimento) = MONTH(CURDATE()) "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_recebimento)";
	
	public static final String TOTAL_MES = "SELECT SUM(valor) AS valor FROM ctlf_receitas "
			+ "WHERE id_usuario = ? "
			+ "AND YEAR(data_recebimento) = YEAR(CURDATE()) "
			+ "AND MONTH(data_recebimento) = MONTH(CURDATE())";
	
	public static final String TOTAL = "SELECT SUM(valor) AS valor FROM ctlf_receitas "
			+ "WHERE id_usuario = ? ";
	
	public static final String SELECIONA_RECEITA = "SELECT * FROM ctlf_receitas "
			+ "WHERE id = ?";
	
	public static final String INSERE_NOVO = "INSERT INTO ctlf_receitas (id_usuario, id_tipo_receita, "
			+ "data, data_recebimento, descricao, valor) "
			+ "VALUES (?, ?, CURDATE(), ?, ?, ?)";
	
	public static final String ATUALIZA = "UPDATE ctlf_receitas SET id_usuario = ?, id_tipo_receita = ?, "
			+ "data_recebimento = ?, descricao = ?, valor = ? "
			+ "WHERE id = ?";
	
	public static final String DELETA = "DELETE FROM ctlf_receitas WHERE id = ?";
}
