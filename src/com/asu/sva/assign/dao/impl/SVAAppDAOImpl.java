package com.asu.sva.assign.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.asu.sva.assign.bean.AppMessageBean;
import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.bean.UserRegisterBean;
import com.asu.sva.assign.dao.SVAAppDAO;
import com.asu.sva.assign.exceptions.SVAAppException;
import com.asu.sva.assign.util.MessageBeanRowMapper;
import com.asu.sva.assign.util.UserBeanRowMapper;

/**
 * @author Vikranth
 *
 */
public class SVAAppDAOImpl implements SVAAppDAO {

	private static final Logger LOGGER = Logger.getLogger(SVAAppDAOImpl.class);

	private static final String GET_USER_BY_USERNAME = "SELECT * FROM "
			+ "USERS WHERE USERNAME = ? LIMIT 1";

	private static final String SAVE_USER = "INSERT INTO USERS "
			+ "(USERNAME, PASSWORD, HASH) "
 + "VALUES(?, ?, ?)";

	// messages - is_secret = (1 -> secret, 0 -> not secret)
	private static final String GET_MESSAGES_BY_USER_ID = "SELECT * FROM "
			+ "MESSAGES WHERE USER_ID = ? AND IS_SECRET = ?";

	private static final String SAVE_MESSAGE = "INSERT INTO MESSAGES "
			+ "(USER_ID, TITLE, MESSAGE, IS_SECRET, TIMESTAMP) "
			+ "VALUES(?, ?, ?, ?, CURRENT_TIMESTAMP)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public AppUserBean getUser(final String username)
			throws SVAAppException {

		AppUserBean userBean = null;
		try {
			userBean = getUserByUsername(username);

		} catch (EmptyResultDataAccessException e) {
			// user empty
			LOGGER.error(e);
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new SVAAppException(e);
		}

		return userBean;
	}

	@Override
	public boolean isUserExists(final String username) throws SVAAppException {

		AppUserBean userBean = null;
		try {
			userBean = getUserByUsername(username);
		} catch (EmptyResultDataAccessException e) {
			// user empty
			LOGGER.error(e);
		} catch (DataAccessException e) {

			LOGGER.error(e);
			throw new SVAAppException(e);
		}

		return userBean != null;

	}

	private AppUserBean getUserByUsername(final String username)
			throws DataAccessException {
		AppUserBean userBean = null;

		userBean = jdbcTemplate.queryForObject(
GET_USER_BY_USERNAME,
				new Object[] { username }, new UserBeanRowMapper());


		return userBean;
	}

	@Override
	public boolean registerUser(UserRegisterBean user) throws SVAAppException {

		int rowsUpdated = 0;
		try {
			rowsUpdated = jdbcTemplate.update(
					SAVE_USER,
					new Object[] { user.getUsername(),
					user.getPassword(), user.getHash() });

		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new SVAAppException(e);
		}

		if (rowsUpdated == 1) {
			return true;
		}

		return false;
	}

	@Override
	public List<AppMessageBean> getMessages(final String userId,
			final String secretCode)
			throws SVAAppException {

		List<AppMessageBean> messages = null;
		
		try {
			messages = jdbcTemplate.query(GET_MESSAGES_BY_USER_ID,
					new Object[] { userId, secretCode },
					new MessageBeanRowMapper());
			LOGGER.info("Message count: " + messages.size());
		} catch (EmptyResultDataAccessException e) {
			messages = new ArrayList<AppMessageBean>();
		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new SVAAppException(e);
		}

		return messages;
	}

	@Override
	public boolean saveMessage(final AppMessageBean message, String secretCode)
			throws SVAAppException {
		// TODO Auto-generated method stub

		int rowsUpdated = 0;
		try {
			rowsUpdated = jdbcTemplate.update(
SAVE_MESSAGE,
					new Object[] { message.getUserId(), message.getTitle(),
							message.getMessage(),
 secretCode });

		} catch (DataAccessException e) {
			LOGGER.error(e);
			throw new SVAAppException(e);
		}

		if (rowsUpdated == 1) {
			return true;
		}

		return false;

	}

}
