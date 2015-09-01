package com.asu.sva.assign.bean;

/**
 * @author Vikranth
 *
 */
public class UserRegisterBean {

	private String username;

	private String password;

	private String password_confirm;

	private String hash;

	/**
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * @param hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * @return password_confirm
	 */
	public String getPassword_confirm() {
		return password_confirm;
	}

	/**
	 * @param password_confirm
	 */
	public void setPassword_confirm(String password_confirm) {
		this.password_confirm = password_confirm;
	}

	@Override
	public String toString() {
		return "UserRegisterBean [username=" + username + ", password="
				+ password + ", password_confirm=" + password_confirm
				+ ", hash=" + hash + "]";
	}

}
