package com.asu.sva.assign.bean;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author Vikranth
 *
 */
public class AppUserBean extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7570882343309863757L;

	/**
	 * @param username
	 * @param password
	 * @param authorities
	 */
	public AppUserBean(String username, String password,
			Collection<GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	private String userId;

	private String hash;

	private int[] secretKey;

	private int[] currentKey;

	private boolean isSecretAgent;

	/**
	 * @return md5Hash
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
	 * @return isSecretAgent
	 */
	public boolean isSecretAgent() {
		return isSecretAgent;
	}

	/**
	 * @param isSecretAgent
	 */
	public void setSecretAgent(boolean isSecretAgent) {
		this.isSecretAgent = isSecretAgent;
	}

	/**
	 * @return key
	 */
	public int[] getSecretKey() {
		return secretKey;
	}

	/**
	 * @param key
	 */
	public void setSecretKey(int[] key) {
		this.secretKey = key;
	}

	/**
	 * @return currentKey
	 */
	public int[] getCurrentKey() {
		return currentKey;
	}

	/**
	 * @param currentKey
	 */
	public void setCurrentKey(int[] currentKey) {
		this.currentKey = currentKey;
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

	@Override
	public String toString() {
		return "AppUserBean [userId=" + userId + ", hash=" + hash
				+ ", secretKey="
				+ Arrays.toString(secretKey) + ", currentKey="
				+ Arrays.toString(currentKey) + ", isSecretAgent="
				+ isSecretAgent + "]";
	}

}
