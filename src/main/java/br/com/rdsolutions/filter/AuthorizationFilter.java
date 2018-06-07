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
package br.com.rdsolutions.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Class responsible for my login session and allowed pages.
 * 
 * @author Rogério Domingos
 * @since 1.0.0
 */
@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" })
public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest reqt = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			HttpSession ses = reqt.getSession(false);
			String reqURI = reqt.getRequestURI();
			// If there isn't any session only those pages can be access by a user
			if (reqURI.indexOf("/login.xhtml") >= 0
					|| reqURI.indexOf("/register.xhtml") >= 0
					|| reqURI.indexOf("/altera_senha.xhtml") >= 0
					|| reqURI.indexOf("/altera_senha_sucesso.xhtml") >= 0
					|| (ses != null && ses.getAttribute("username") != null)
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource"))
				chain.doFilter(request, response);
			else
				resp.sendRedirect(reqt.getContextPath() + "/login.xhtml"); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}
}
