package com.semend.soa;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class OrderInstanceHeaderFilter extends OncePerRequestFilter {

	private final Environment environment;

	public OrderInstanceHeaderFilter(Environment environment) {
		this.environment = environment;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		response.setHeader("X-Order-Service-Port", environment.getRequiredProperty("server.port"));
		chain.doFilter(request, response);
	}
}
