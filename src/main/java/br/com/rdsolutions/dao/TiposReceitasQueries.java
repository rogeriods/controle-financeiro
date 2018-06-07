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
public class TiposReceitasQueries {

	// Constant variable with SQL query
	public static final String LISTA_TODOS = "SELECT * FROM ctlf_tipos_receitas ORDER BY descricao";
}
