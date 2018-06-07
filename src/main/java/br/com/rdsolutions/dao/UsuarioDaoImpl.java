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

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.rdsolutions.beans.Usuario;
import br.com.rdsolutions.utils.EnviaEmail;
import br.com.rdsolutions.utils.GeraSenhaRandomico;

/**
 * Class implementation of my interface and extension of my connection.
 * This class are responsible for communication APP and database. 
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class UsuarioDaoImpl extends DBConnection implements UsuarioDao {

	// Private data access variables
	private Statement stm;
	private ResultSet rs;

	@Override
	public Usuario selecionaPorEmailSenha(String email, String senha) {
		conn = createConnection();
		Usuario objUsuario = null;
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(UsuarioQueries.SELECIONA_POR_EMAIL_SENHA);
			pstm.setString(1, email);
			pstm.setString(2, senha);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objUsuario = new Usuario();
				objUsuario.setId(rs.getInt("id"));
				objUsuario.setData(rs.getTimestamp("data"));
				objUsuario.setNome(rs.getString("nome"));
				objUsuario.setEmail(rs.getString("email"));
				objUsuario.setSenha(rs.getString("senha"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objUsuario;
	}

	@Override
	public void insereNovo(Usuario objUsuario) {		
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(UsuarioQueries.INSERE_NOVO);
			pstm.setString(1, objUsuario.getNome());
			pstm.setString(2, objUsuario.getEmail());
			pstm.setString(3, objUsuario.getSenha());
			pstm.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void atualiza(Usuario objUsuario) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(UsuarioQueries.ATUALIZA);
			pstm.setString(1, objUsuario.getNome());
			pstm.setString(2, objUsuario.getSenha());
			pstm.setInt(3, objUsuario.getId());
			pstm.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean validaEmailSenha(String email, String senha) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(UsuarioQueries.SELECIONA_POR_EMAIL_SENHA);
			pstm.setString(1, email);
			pstm.setString(2, senha);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				// If there is a register return true
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			// If any exception return false
			return false;
		} finally {
			try {
				conn.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean validaEmail(String email) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(UsuarioQueries.SELECIONA_POR_EMAIL);
			pstm.setString(1, email);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				// If there is a register return true
				return true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
			// If any exception return false
			return false;
		} finally {
			try {
				conn.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public void esqueceuSenha(String email) {
		conn = createConnection();
		// Call static method return a random password
		String novaSenha = GeraSenhaRandomico.gerarSenha();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(UsuarioQueries.ESQUECEU_SENHA);
			pstm.setString(1, novaSenha);
			pstm.setString(2, email);
			pstm.executeUpdate();
			// Send an e-mail with the new password
			EnviaEmail.enviaSenhaNova(novaSenha, email);
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
