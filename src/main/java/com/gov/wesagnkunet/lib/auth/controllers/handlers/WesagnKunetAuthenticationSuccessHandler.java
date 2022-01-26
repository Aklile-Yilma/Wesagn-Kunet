package com.gov.wesagnkunet.lib.auth.controllers.handlers;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gov.wesagnkunet.lib.auth.data.models.User.Role;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;



public class WesagnKunetAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	private Map<String, String> redirectionMap = new HashMap<String, String>(){{
		put("ROLE_"+Role.ADMIN.name(), "/admin/dashboard/requests/marriage");
		put("ROLE_"+Role.CLIENT.name(), "/");
	}};


	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String redirectUrl = getRedirectUrl(authentication);

		if(!response.isCommitted())
				redirectStrategy.sendRedirect(request, response, redirectUrl);

		clearAuthenticationAttributes(request);
	}

	private String getRedirectUrl(Authentication authentication){
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		
		if(authorities.size() != 1)
			throw new IllegalStateException();

		GrantedAuthority authority = authorities.iterator().next();
		String redirectUrl = redirectionMap.get(authority.getAuthority());
		if(redirectUrl == null)
			throw new IllegalStateException();
		return redirectUrl;
	}

	private void clearAuthenticationAttributes(HttpServletRequest request){

		HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

	}
	
}
