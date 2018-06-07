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
package br.com.rdsolutions.utils;

import java.util.Random;

/**
 * Class responsible for passwords.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
public class GeraSenhaRandomico {

	// Method that generates a random password
	public static String gerarSenha() {
		// Dictionary of characters to apply in my random password
		char[] chars = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		// Fill array with 6 digits
		for (int i = 0; i < 6; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		// Return the random password
		return sb.toString();
	}
}
