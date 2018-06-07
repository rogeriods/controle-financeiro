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
public class DespesaQueries {
	
	// Constant variables with SQL queries
	public static final String LISTA_TODAS = "SELECT a.id, a.id_usuario, a.id_tipo_despesa, b.descricao "
			+ "AS tipo_descricao, a.data, a.data_vencimento, a.data_pagamento, a.descricao, a.valor "
			+ "FROM ctlf_despesas a, ctlf_tipos_despesas b "
			+ "WHERE a.id_tipo_despesa = b.id "
			+ "AND a.id_usuario = ? "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_vencimento)";
	
	public static final String LISTA_TODAS_VENCIDAS = "SELECT a.id, a.id_usuario, a.id_tipo_despesa, "
			+ "b.descricao AS tipo_descricao, a.data, a.data_vencimento, a.data_pagamento, a.descricao, a.valor "
			+ "FROM ctlf_despesas a, ctlf_tipos_despesas b "
			+ "WHERE a.id_tipo_despesa = b.id "
			+ "AND a.id_usuario = ? "
			+ "AND a.data_vencimento < CURDATE() "
			+ "AND a.data_pagamento IS NULL "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_vencimento)";
	
	public static final String LISTA_TODAS_ABERTAS = "SELECT a.id, a.id_usuario, a.id_tipo_despesa, b.descricao "
			+ "AS tipo_descricao, a.data, a.data_vencimento, a.data_pagamento, a.descricao, a.valor "
			+ "FROM ctlf_despesas a, ctlf_tipos_despesas b "
			+ "WHERE a.id_tipo_despesa = b.id "
			+ "AND a.id_usuario = ? "
			+ "AND a.data_pagamento IS NULL "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_vencimento)";
	
	public static final String LISTA_TODAS_ABERTAS_DO_MES = "SELECT a.id, a.id_usuario, a.id_tipo_despesa, "
			+ "b.descricao AS tipo_descricao, a.data, a.data_vencimento, a.data_pagamento, a.descricao, a.valor "
			+ "FROM ctlf_despesas a, ctlf_tipos_despesas b "
			+ "WHERE a.id_tipo_despesa = b.id "
			+ "AND a.id_usuario = ? "
			+ "AND YEAR(a.data_vencimento) = YEAR(CURDATE()) "
			+ "AND MONTH(a.data_vencimento) = MONTH(CURDATE()) "
			+ "AND a.data_pagamento IS NULL "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_vencimento)";
	
	public static final String LISTA_TODAS_PAGAS_DO_MES = "SELECT a.id, a.id_usuario, a.id_tipo_despesa, "
			+ "b.descricao AS tipo_descricao, a.data, a.data_vencimento, a.data_pagamento, a.descricao, a.valor "
			+ "FROM ctlf_despesas a, ctlf_tipos_despesas b "
			+ "WHERE a.id_tipo_despesa = b.id "
			+ "AND a.id_usuario = ? "
			+ "AND YEAR(a.data_vencimento) = YEAR(CURDATE()) "
			+ "AND MONTH(a.data_vencimento) = MONTH(CURDATE()) "
			+ "AND a.data_pagamento IS NOT NULL "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_vencimento)";
	
	public static final String LISTA_TODAS_DO_MES = "SELECT a.id, a.id_usuario, a.id_tipo_despesa, "
			+ "b.descricao AS tipo_descricao, a.data, a.data_vencimento, a.data_pagamento, a.descricao, a.valor "
			+ "FROM ctlf_despesas a, ctlf_tipos_despesas b "
			+ "WHERE a.id_tipo_despesa = b.id "
			+ "AND a.id_usuario = ? "
			+ "AND YEAR(a.data_vencimento) = YEAR(CURDATE()) "
			+ "AND MONTH(a.data_vencimento) = MONTH(CURDATE()) "
			+ "ORDER BY UNIX_TIMESTAMP(a.data_vencimento)";	
	
	public static final String TOTAL_VENCIDAS = "SELECT SUM(valor) AS valor FROM ctlf_despesas "
			+ "WHERE id_usuario = ? "
			+ "AND data_vencimento < CURDATE() "
			+ "AND data_pagamento IS NULL";
	
	public static final String TOTAL_MES = "SELECT SUM(valor) AS valor FROM ctlf_despesas "
			+ "WHERE id_usuario = ? "
			+ "AND YEAR(data_vencimento) = YEAR(CURDATE()) "
			+ "AND MONTH(data_vencimento) = MONTH(CURDATE()) "
			+ "AND data_pagamento IS NULL";
	
	public static final String TOTAL_PAGA_MES = "SELECT SUM(valor) AS valor FROM ctlf_despesas "
			+ "WHERE id_usuario = ? "
			+ "AND YEAR(data_vencimento) = YEAR(CURDATE()) "
			+ "AND MONTH(data_vencimento) = MONTH(CURDATE()) "
			+ "AND data_pagamento IS NOT NULL";
	
	public static final String TOTAL = "SELECT SUM(valor) AS valor FROM ctlf_despesas "
			+ "WHERE id_usuario = ? "
			+ "AND data_pagamento IS NOT NULL";
	
	public static final String SELECIONA_DESPESA = "SELECT * FROM ctlf_despesas WHERE id = ?";
	
	public static final String INSERE_NOVO = "INSERT INTO ctlf_despesas "
			+ "(id_usuario, id_tipo_despesa, data, data_vencimento, data_pagamento, descricao, valor) "
			+ "VALUES (?, ?, CURDATE(), ?, ?, ?, ?)";
	
	public static final String ATUALIZA = "UPDATE ctlf_despesas SET id_usuario = ?, id_tipo_despesa = ?, "
			+ "data = ?, data_vencimento = ?, data_pagamento = ?, descricao = ?, valor = ? "
			+ "WHERE id = ?";
	
	public static final String DELETA = "DELETE FROM ctlf_despesas WHERE id = ?";
}
