package com.asu.sva.assign.processor;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.asu.sva.assign.bean.AppMessageBean;
import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.bean.UserRegisterBean;
import com.asu.sva.assign.dao.SVAAppDAO;
import com.asu.sva.assign.exceptions.SVAAppBException;
import com.asu.sva.assign.exceptions.SVAAppException;
import com.asu.sva.assign.util.AppUtils;

/**
 * @author Vikranth
 *
 */
public class SVAAppProcessor {

	private static final String HASH_ALGORITHM = "MD5";

	private static final int HASH_LENGTH = 4;

	private static final String SECRET_CODE = "1";

	private static final String NORMAL_CODE = "0";

	private static final Logger LOGGER = Logger
			.getLogger(SVAAppProcessor.class);

	@Autowired
	private SVAAppDAO svaAppDao;

	/**
	 * @param username
	 * @return userBean
	 * @throws SVAAppException
	 * @throws SVAAppBException
	 */
	public AppUserBean getUserByUsername(final String username)
			throws SVAAppException, SVAAppBException {
		
		AppUserBean userBean = null;
		try {
			userBean = svaAppDao.getUser(username);
		} catch (SVAAppException e) {
			//
		}

		if (userBean == null) {
			throw new SVAAppBException("User not found");
		}

		return userBean;
	}
	
	/**
	 * @param username
	 * @return status
	 * @throws SVAAppException
	 * @throws SVAAppBException
	 */
	public boolean isUserExists(final String username) throws SVAAppException,
			SVAAppBException {

		AppUserBean userBean = null;
		userBean = svaAppDao.getUser(username);

		return null != userBean;

	}

	/**
	 * @param user
	 * @return status
	 * @throws SVAAppException
	 * @throws SVAAppBException
	 */
	public boolean registerUser(final UserRegisterBean user)
			throws SVAAppException, SVAAppBException {

		// hash
		String hash;

		try {
			hash = AppUtils.getHash(user.getUsername(), HASH_ALGORITHM,
					HASH_LENGTH);
			user.setHash(hash);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e);
			throw new SVAAppException(e);
		}

		return svaAppDao.registerUser(user);

	}

	/**
	 * @param user
	 * @return messages
	 * @throws SVAAppException
	 * @throws SVAAppBException
	 */
	public List<AppMessageBean> getMessages(final AppUserBean user)
			throws SVAAppException, SVAAppBException {

		return svaAppDao.getMessages(user.getUserId(),
				user.isSecretAgent() ? SECRET_CODE : NORMAL_CODE);

	}

	/**
	 * @param user
	 * @param message
	 * @return status
	 * @throws SVAAppException
	 * @throws SVAAppBException
	 */
	public boolean saveMessage(final AppUserBean user,
			final AppMessageBean message) throws SVAAppException,
			SVAAppBException {

		message.setUserId(user.getUserId());
		return svaAppDao.saveMessage(message,
				user.isSecretAgent() ? SECRET_CODE : NORMAL_CODE);

	}

}
