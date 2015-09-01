package com.asu.sva.assign.dao;

import java.util.List;

import com.asu.sva.assign.bean.AppMessageBean;
import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.bean.UserRegisterBean;
import com.asu.sva.assign.exceptions.SVAAppException;

/**
 * @author Vikranth
 *
 */
public interface SVAAppDAO {

	/**
	 * @param username
	 * @return user
	 * @throws SVAAppException
	 */
	public AppUserBean getUser(final String username)
			throws SVAAppException;

	/**
	 * @param username
	 * @return status
	 * @throws SVAAppException
	 */
	public boolean isUserExists(final String username) throws SVAAppException;

	/**
	 * @param user
	 * @return user
	 * @throws SVAAppException
	 */
	public boolean registerUser(final UserRegisterBean user)
			throws SVAAppException;

	/**
	 * @param userId
	 * @param secretCode
	 * @return messages
	 * @throws SVAAppException
	 */
	public List<AppMessageBean> getMessages(final String userId,
			final String secretCode)
			throws SVAAppException;

	/**
	 * @param message
	 * @param secretCode
	 * @return status
	 * @throws SVAAppException
	 */
	public boolean saveMessage(final AppMessageBean message, String secretCode)
			throws SVAAppException;

}
