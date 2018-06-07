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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.rdsolutions.beans.Receita;

/**
 * Class implementation of my interface and extension of my connection.
 * This class are responsible for communication APP and database. 
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class ReceitaDaoImpl extends DBConnection implements ReceitaDao {

	// Private data access variables
	private Statement stm;
	private ResultSet rs;

	// Date format variable
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Receita> listaTodas(int idUsuario) {
		conn = createConnection();
		Receita objReceita = null;
		List<Receita> lstReceitas = new ArrayList<Receita>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.LISTA_TODAS);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objReceita = new Receita();
				objReceita.setId(rs.getInt("id"));
				objReceita.setIdUsuario(rs.getInt("id_usuario"));
				objReceita.setIdTipoReceita(rs.getInt("id_tipo_receita"));
				objReceita.setTipoDescricao(rs.getString("tipo_descricao"));
				objReceita.setData(rs.getDate("data"));
				objReceita.setDataRecebimento(rs.getDate("data_recebimento"));
				objReceita.setDescricao(rs.getString("descricao"));
				objReceita.setValor(rs.getBigDecimal("valor"));
				lstReceitas.add(objReceita);
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
		return lstReceitas;
	}

	@Override
	public List<Receita> listaTodasDoMes(int idUsuario) {
		conn = createConnection();
		Receita objReceita = null;
		List<Receita> lstReceitas = new ArrayList<Receita>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.LISTA_TODAS_DO_MES);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objReceita = new Receita();
				objReceita.setId(rs.getInt("id"));
				objReceita.setIdUsuario(rs.getInt("id_usuario"));
				objReceita.setIdTipoReceita(rs.getInt("id_tipo_receita"));
				objReceita.setTipoDescricao(rs.getString("tipo_descricao"));
				objReceita.setData(rs.getDate("data"));
				objReceita.setDataRecebimento(rs.getDate("data_recebimento"));
				objReceita.setDescricao(rs.getString("descricao"));
				objReceita.setValor(rs.getBigDecimal("valor"));
				lstReceitas.add(objReceita);
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
		return lstReceitas;
	}

	@Override
	public Receita selecionaReceita(int id) {
		conn = createConnection();
		Receita objReceita = null;
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.SELECIONA_RECEITA);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objReceita = new Receita();
				objReceita.setId(rs.getInt("id"));
				objReceita.setIdUsuario(rs.getInt("id_usuario"));
				objReceita.setIdTipoReceita(rs.getInt("id_tipo_receita"));
				objReceita.setData(rs.getDate("data"));
				objReceita.setDataRecebimento(rs.getDate("data_recebimento"));
				objReceita.setDescricao(rs.getString("descricao"));
				objReceita.setValor(rs.getBigDecimal("valor"));
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
		return objReceita;
	}

	@Override
	public void insereNovo(int idUsuario, Receita objReceita) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.INSERE_NOVO);		
			pstm.setInt(1, idUsuario);
			pstm.setInt(2, objReceita.getIdTipoReceita());
			pstm.setString(3, df.format(objReceita.getDataRecebimento()));
			pstm.setString(4, objReceita.getDescricao());
			pstm.setBigDecimal(5, objReceita.getValor());
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
	public void atualiza(Receita objReceita) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.ATUALIZA);		
			pstm.setInt(1, objReceita.getIdUsuario());
			pstm.setInt(2, objReceita.getIdTipoReceita());
			pstm.setString(3, df.format(objReceita.getDataRecebimento()));
			pstm.setString(4, objReceita.getDescricao());
			pstm.setBigDecimal(5, objReceita.getValor());
			pstm.setInt(6, objReceita.getId());
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
	public void deleta(Receita objReceita) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.DELETA);
			pstm.setInt(1, objReceita.getId());

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
	public BigDecimal totalMes(int idUsuario) {
		conn = createConnection();
		BigDecimal valor = new BigDecimal("0");
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.TOTAL_MES);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {				
				if(rs.getBigDecimal("valor") != null)
					valor = rs.getBigDecimal("valor");
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
		return valor;
	}

	@Override
	public BigDecimal total(int idUsuario) {
		conn = createConnection();
		BigDecimal valor = new BigDecimal("0");
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(ReceitaQueries.TOTAL);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {				
				if(rs.getBigDecimal("valor") != null)
					valor = rs.getBigDecimal("valor");
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
		return valor;
	}
}
