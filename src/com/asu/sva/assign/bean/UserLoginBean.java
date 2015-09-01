package com.asu.sva.assign.bean;

/**
 * @author Vikranth
 *
 */
public class UserLoginBean {

	private String username;

	private String password;
	
	private String md5;

	/**
	 * @return md5
	 */
	public String getMd5() {
		return md5;
	}

	/**
	 * @param md5
	 */
	public void setMd5(String md5) {
		this.md5 = md5;
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
	
	@Override
	public String toString() {
		return "LoginFormBean [username=" + username + ", md5=" + md5 + "]";
	}

}
