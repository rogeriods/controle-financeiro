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

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Class make a connection with the MySQL database.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class DBConnection {

	// Protect my variable connection to use only at the same package
	protected Connection conn;
	private String url = "jdbc:mysql://localhost:3306/rdsolutions";
	private String username = "root";
	private String password = "";

	// Method that return a connection
	public Connection createConnection() {
		conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(this.url,this.username,this.password);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
