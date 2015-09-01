package com.asu.sva.assign.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.asu.sva.assign.bean.AppMessageBean;

/**
 * @author Vikranth
 *
 */
public class MessageBeanRowMapper implements RowMapper<AppMessageBean> {

	private static final String USER_ID = "USER_ID";
	private static final String MESSAGE = "MESSAGE";
	private static final String TITLE = "TITLE";
	private static final String IS_SECRET = "IS_SECRET";
	private static final String TIMESTAMP = "TIMESTAMP";

	@Override
	public AppMessageBean mapRow(ResultSet rs, int rownum) throws SQLException {
		
		AppMessageBean message = new AppMessageBean();
		
		message.setMessage(rs.getString(MESSAGE));
		message.setTitle(rs.getString(TITLE));
		message.setSecret(rs.getInt(IS_SECRET) == 0 ? false : true);
		message.setUserId(rs.getString(USER_ID));
		message.setDate(AppUtils.sqlDate2UtilDate(rs.getDate(TIMESTAMP)));

		
		return message;
	}

}
