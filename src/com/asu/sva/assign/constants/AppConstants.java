package com.asu.sva.assign.constants;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 * @author Vikranth
 *
 */
public class AppConstants {



	/**
	 * DOC Title
	 */
	public static final String DOC_TITLE = "SVA Assignment 2";
	
	/**
	 * 
	 */
	public static final String DEFAULT_USER_ROLE = "ROLE_USER";

	/**
	 * session auth user str
	 */
	public static final String AUTH_USER_STR = "authUser";

	/**
	 * 
	 */
	public static Collection<GrantedAuthority> DEFAULT_AUTHORITIES = AuthorityUtils
			.createAuthorityList(DEFAULT_USER_ROLE);


	private AppConstants() {

	}

}
