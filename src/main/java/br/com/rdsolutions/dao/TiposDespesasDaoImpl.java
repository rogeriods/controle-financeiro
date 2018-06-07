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
import java.util.ArrayList;
import java.util.List;

import br.com.rdsolutions.beans.TipoDespesa;

/**
 * Class implementation of my interface and extension of my connection.
 * This class are responsible for communication APP and database. 
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class TiposDespesasDaoImpl extends DBConnection implements TiposDespesasDao {

	// Private data access variables
	private Statement stm;
	private ResultSet rs;
		
	@Override
	public List<TipoDespesa> listaTodosTiposDespesas() {
		conn = createConnection();
		TipoDespesa objTipoDespesa = null;
		List<TipoDespesa> lstTiposDespesas = new ArrayList<TipoDespesa>();
		try {
			stm = conn.createStatement();
			PreparedStatement pstm = conn.prepareStatement(TiposDespesasQueries.LISTA_TODOS);
			rs = pstm.executeQuery();
			while(rs.next() == true) {
				objTipoDespesa = new TipoDespesa();
				objTipoDespesa.setId(rs.getInt("id"));
				objTipoDespesa.setDescricao(rs.getString("descricao"));
				lstTiposDespesas.add(objTipoDespesa);
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
		return lstTiposDespesas;
	}
}
