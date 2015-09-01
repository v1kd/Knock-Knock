package com.asu.sva.assign.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.dao.SVAAppDAO;
import com.asu.sva.assign.exceptions.SVAAppException;

/**
 * @author Vikranth
 *
 */
public class SVAAppUserDetailsService implements UserDetailsService {
	
	@Autowired
	private SVAAppDAO svaAppDao;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// Get the details of the user for 
		// that particular username from database
		
		AppUserBean userBean = null;

		try {
			userBean = svaAppDao.getUser(username);
		} catch (SVAAppException e) {
			// error
		}
		
		if (userBean == null) {
			throw new UsernameNotFoundException("Invalid username/password.");
		}
		
		
		return userBean;

	}
}
