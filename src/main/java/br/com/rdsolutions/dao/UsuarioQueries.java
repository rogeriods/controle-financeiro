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
public class UsuarioQueries {

	// Constant variables with SQL queries
	public static final String SELECIONA_POR_EMAIL_SENHA = "SELECT * FROM ctlf_usuarios "
			+ "WHERE email = ? "
			+ "AND senha = SHA1(?)";
	
	public static final String SELECIONA_POR_EMAIL = "SELECT * FROM ctlf_usuarios "
			+ "WHERE email = ?";

	public static final String INSERE_NOVO = "INSERT INTO ctlf_usuarios (nome, email, senha, data) "
			+ "VALUES (?, ?, SHA1(?), now())";
	
	public static final String ATUALIZA = "UPDATE ctlf_usuarios SET nome = ?, senha = SHA1(?) "
			+ "WHERE id = ?";
	
	public static final String ESQUECEU_SENHA = "UPDATE ctlf_usuarios SET senha = SHA1(?) "
			+ "WHERE email = ?";
}
