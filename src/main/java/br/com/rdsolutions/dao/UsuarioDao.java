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

import br.com.rdsolutions.beans.Usuario;

/**
 * Interface with methods signatures.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public interface UsuarioDao {
	
	// Those signatures validating e-mails and return a boolean
	public boolean validaEmailSenha(String email, String senha);
	public boolean validaEmail(String email);
	
	// This signature return an object
	public Usuario selecionaPorEmailSenha(String email, String senha);
	
	// Those signatures implement the CRUD actions
	public void insereNovo(Usuario objUsuario);
	public void atualiza(Usuario objUsuario);
	public void esqueceuSenha(String email);
}
