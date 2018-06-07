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

import br.com.rdsolutions.beans.Despesa;

/**
 * Class implementation of my interface and extension of my connection.
 * This class are responsible for communication APP and database. 
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class DespesaDaoImpl extends DBConnection implements DespesaDao {

	// Private data access variables
	private Statement stm;
	private ResultSet rs;
	
	// Date format variable
	private DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public List<Despesa> listaTodas(int idUsuario) {
		conn = createConnection();
		Despesa objDespesa = null;
		List<Despesa> lstDespesas = new ArrayList<Despesa>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.LISTA_TODAS);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objDespesa = new Despesa();
				objDespesa.setId(rs.getInt("id"));
				objDespesa.setIdUsuario(rs.getInt("id_usuario"));
				objDespesa.setIdTipoDespesa(rs.getInt("id_tipo_despesa"));
				objDespesa.setTipoDescricao(rs.getString("tipo_descricao"));
				objDespesa.setData(rs.getDate("data"));
				objDespesa.setDataVencimento(rs.getDate("data_vencimento"));
				objDespesa.setDataPagamento(rs.getDate("data_pagamento"));
				objDespesa.setDescricao(rs.getString("descricao"));
				objDespesa.setValor(rs.getBigDecimal("valor"));
				lstDespesas.add(objDespesa);
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
		return lstDespesas;
	}

	@Override
	public void insereNovo(int idUsuario, Despesa objDespesa) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.INSERE_NOVO);		
			pstm.setInt(1, idUsuario);
			pstm.setInt(2, objDespesa.getIdTipoDespesa());
			pstm.setString(3, df.format(objDespesa.getDataVencimento()));
			if(objDespesa.getDataPagamento() == null) {
				pstm.setString(4, null);
			} else {
				pstm.setString(4, df.format(objDespesa.getDataPagamento()));
			}
			pstm.setString(5, objDespesa.getDescricao());
			pstm.setBigDecimal(6, objDespesa.getValor());
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
	public void atualiza(Despesa objDespesa) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.ATUALIZA);
			pstm.setInt(1, objDespesa.getIdUsuario());
			pstm.setInt(2, objDespesa.getIdTipoDespesa());
			// N�o trato estas duas datas pois uma � default e a outra � obrigat�rio preenchimento
			pstm.setString(3, df.format(objDespesa.getData()));
			pstm.setString(4, df.format(objDespesa.getDataVencimento()));
			if(objDespesa.getDataPagamento() == null) {
				pstm.setString(5, null);
			} else {
				pstm.setString(5, df.format(objDespesa.getDataPagamento()));
			}
			pstm.setString(6, objDespesa.getDescricao());
			pstm.setBigDecimal(7, objDespesa.getValor());
			pstm.setInt(8, objDespesa.getId());
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
	public void deleta(Despesa objDespesa) {
		conn = createConnection();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.DELETA);
			pstm.setInt(1, objDespesa.getId());
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
	public Despesa selecionaDespesa(int id) {
		conn = createConnection();
		Despesa objDespesa = null;
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.SELECIONA_DESPESA);
			pstm.setInt(1, id);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objDespesa = new Despesa();
				objDespesa.setId(rs.getInt("id"));
				objDespesa.setIdUsuario(rs.getInt("id_usuario"));
				objDespesa.setIdTipoDespesa(rs.getInt("id_tipo_despesa"));
				objDespesa.setData(rs.getDate("data"));
				objDespesa.setDataVencimento(rs.getDate("data_vencimento"));
				objDespesa.setDataPagamento(rs.getDate("data_pagamento"));
				objDespesa.setDescricao(rs.getString("descricao"));
				objDespesa.setValor(rs.getBigDecimal("valor"));
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
		return objDespesa;
	}

	@Override
	public List<Despesa> listaTodasVencidas(int idUsuario) {
		conn = createConnection();
		Despesa objDespesa = null;
		List<Despesa> lstDespesas = new ArrayList<Despesa>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.LISTA_TODAS_VENCIDAS);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objDespesa = new Despesa();
				objDespesa.setId(rs.getInt("id"));
				objDespesa.setIdUsuario(rs.getInt("id_usuario"));
				objDespesa.setIdTipoDespesa(rs.getInt("id_tipo_despesa"));
				objDespesa.setTipoDescricao(rs.getString("tipo_descricao"));
				objDespesa.setData(rs.getDate("data"));
				objDespesa.setDataVencimento(rs.getDate("data_vencimento"));
				objDespesa.setDataPagamento(rs.getDate("data_pagamento"));
				objDespesa.setDescricao(rs.getString("descricao"));
				objDespesa.setValor(rs.getBigDecimal("valor"));
				lstDespesas.add(objDespesa);
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
		return lstDespesas;
	}

	@Override
	public List<Despesa> listaTodasAbertas(int idUsuario) {
		conn = createConnection();
		Despesa objDespesa = null;
		List<Despesa> lstDespesas = new ArrayList<Despesa>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.LISTA_TODAS_ABERTAS);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objDespesa = new Despesa();
				objDespesa.setId(rs.getInt("id"));
				objDespesa.setIdUsuario(rs.getInt("id_usuario"));
				objDespesa.setIdTipoDespesa(rs.getInt("id_tipo_despesa"));
				objDespesa.setTipoDescricao(rs.getString("tipo_descricao"));
				objDespesa.setData(rs.getDate("data"));
				objDespesa.setDataVencimento(rs.getDate("data_vencimento"));
				objDespesa.setDataPagamento(rs.getDate("data_pagamento"));
				objDespesa.setDescricao(rs.getString("descricao"));
				objDespesa.setValor(rs.getBigDecimal("valor"));
				lstDespesas.add(objDespesa);
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
		return lstDespesas;
	}

	@Override
	public List<Despesa> listaTodasAbertasDoMes(int idUsuario) {
		conn = createConnection();
		Despesa objDespesa = null;
		List<Despesa> lstDespesas = new ArrayList<Despesa>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.LISTA_TODAS_ABERTAS_DO_MES);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objDespesa = new Despesa();
				objDespesa.setId(rs.getInt("id"));
				objDespesa.setIdUsuario(rs.getInt("id_usuario"));
				objDespesa.setIdTipoDespesa(rs.getInt("id_tipo_despesa"));
				objDespesa.setTipoDescricao(rs.getString("tipo_descricao"));
				objDespesa.setData(rs.getDate("data"));
				objDespesa.setDataVencimento(rs.getDate("data_vencimento"));
				objDespesa.setDataPagamento(rs.getDate("data_pagamento"));
				objDespesa.setDescricao(rs.getString("descricao"));
				objDespesa.setValor(rs.getBigDecimal("valor"));
				lstDespesas.add(objDespesa);
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
		return lstDespesas;
	}

	@Override
	public List<Despesa> listaTodasPagasDoMes(int idUsuario) {
		conn = createConnection();
		Despesa objDespesa = null;
		List<Despesa> lstDespesas = new ArrayList<Despesa>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.LISTA_TODAS_PAGAS_DO_MES);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objDespesa = new Despesa();
				objDespesa.setId(rs.getInt("id"));
				objDespesa.setIdUsuario(rs.getInt("id_usuario"));
				objDespesa.setIdTipoDespesa(rs.getInt("id_tipo_despesa"));
				objDespesa.setTipoDescricao(rs.getString("tipo_descricao"));
				objDespesa.setData(rs.getDate("data"));
				objDespesa.setDataVencimento(rs.getDate("data_vencimento"));
				objDespesa.setDataPagamento(rs.getDate("data_pagamento"));
				objDespesa.setDescricao(rs.getString("descricao"));
				objDespesa.setValor(rs.getBigDecimal("valor"));
				lstDespesas.add(objDespesa);
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
		return lstDespesas;
	}

	@Override
	public List<Despesa> listaTodasDoMes(int idUsuario) {
		conn = createConnection();
		Despesa objDespesa = null;
		List<Despesa> lstDespesas = new ArrayList<Despesa>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.LISTA_TODAS_DO_MES);
			pstm.setInt(1, idUsuario);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objDespesa = new Despesa();
				objDespesa.setId(rs.getInt("id"));
				objDespesa.setIdUsuario(rs.getInt("id_usuario"));
				objDespesa.setIdTipoDespesa(rs.getInt("id_tipo_despesa"));
				objDespesa.setTipoDescricao(rs.getString("tipo_descricao"));
				objDespesa.setData(rs.getDate("data"));
				objDespesa.setDataVencimento(rs.getDate("data_vencimento"));
				objDespesa.setDataPagamento(rs.getDate("data_pagamento"));
				objDespesa.setDescricao(rs.getString("descricao"));
				objDespesa.setValor(rs.getBigDecimal("valor"));
				lstDespesas.add(objDespesa);
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
		return lstDespesas;
	}

	@Override
	public BigDecimal totalVencidas(int idUsuario) {
		conn = createConnection();
		BigDecimal valor = new BigDecimal("0");
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.TOTAL_VENCIDAS);
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
	public BigDecimal totalMes(int idUsuario) {
		conn = createConnection();
		BigDecimal valor = new BigDecimal("0");
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.TOTAL_MES);
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
	public BigDecimal totalPagaMes(int idUsuario) {
		conn = createConnection();
		BigDecimal valor = new BigDecimal("0");
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.TOTAL_PAGA_MES);
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
			PreparedStatement pstm = conn.prepareStatement(DespesaQueries.TOTAL);
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
