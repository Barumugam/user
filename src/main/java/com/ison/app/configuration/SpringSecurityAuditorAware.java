package com.ison.app.configuration;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ison.app.services.impl.UserPrinciple;

@Service
public class SpringSecurityAuditorAware {

	public UserPrinciple getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}

		return ((UserPrinciple) authentication.getPrincipal());
	}
	
	public String getUserName() {
		return getCurrentAuditor().getEmployeeId();
	}

}
