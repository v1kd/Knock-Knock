package com.asu.sva.assign.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.constants.AppConstants;

/**
 * @author Vikranth
 *
 */
public class UserBeanRowMapper implements RowMapper<AppUserBean> {
	
	private static final String USER_NAME = "USERNAME";

	private static final String PASSWORD = "PASSWORD";

	private static final String HASH = "HASH";

	private static final String USER_ID = "USER_ID";

	@Override
	public AppUserBean mapRow(ResultSet rs, int rownum) throws SQLException {
		
		String userId = rs.getString(USER_ID);
		String username = rs.getString(USER_NAME);
		String password = rs.getString(PASSWORD);
		String hash = rs.getString(HASH);
		
		
		AppUserBean bean = new AppUserBean(
				username,
				password,
				AppConstants.DEFAULT_AUTHORITIES);

		bean.setHash(hash);
		bean.setUserId(userId);

		return bean;
	}

}
