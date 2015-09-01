package com.asu.sva.assign.bean;

import java.util.Date;

/**
 * @author Vikranth
 *
 */
public class AppMessageBean {

	private String title;

	private String message;

	private String userId;

	private boolean isSecret;

	private Date date;

	/**
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return secret
	 */
	public boolean isSecret() {
		return isSecret;
	}

	/**
	 * @param isSecret
	 */
	public void setSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}

	/**
	 * @return date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
