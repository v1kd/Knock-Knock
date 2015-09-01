package com.asu.sva.assign.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.constants.AppConstants;

/**
 * @author Vikranth
 *
 */
public class AppUtils {

	/**
	 * @param user
	 */
	public static void setSecretCode(final AppUserBean user) {
		if (null == user) {
			return;
		}

		user.setSecretKey(convertHexToIntArray(user.getHash(), user
				.getHash().length()));
		user.setCurrentKey(getEmptyArray(user.getHash().length()));
	}

	/**
	 * @param session
	 * @return authUser
	 */
	public static AppUserBean getAuthUserFromSession(final HttpSession session) {

		AppUserBean authUser = (AppUserBean) session
				.getAttribute(AppConstants.AUTH_USER_STR);
		return authUser;
	}

	/**
	 * @param authUser
	 * @param index
	 */
	public static void trackSecretCode(final AppUserBean authUser,
			final int index) {
		if (authUser.isSecretAgent())
			return;

		int[] currKey = authUser.getCurrentKey();
		int[] secKey = authUser.getSecretKey();

		// check the current key
		if (compareArrays(currKey, secKey, index)
				&& currKey[currKey.length - 1] > -1) {
			authUser.setSecretAgent(true);
		}

	}

	/**
	 * @return isLoggedIn
	 */
	public static boolean isLoggedIn() {
		Object principal = (SecurityContextHolder.getContext()
				.getAuthentication() == null ? null : SecurityContextHolder
				.getContext().getAuthentication().getPrincipal());

		if (principal != null && principal instanceof UserDetails
				&& isValidLogin((UserDetails) principal)) {
			// loggedIn
			return true;
		}

		return false;
	}

	/**
	 * @param str
	 * @param algo
	 * @param num
	 * @return status
	 * @throws NoSuchAlgorithmException
	 */
	public static String getHash(final String str, final String algo,
			final int num) throws NoSuchAlgorithmException {

		byte[] strBytes = str.getBytes();
		byte[] mdOutput;
		
		int[] hash = new int[num];
		String s;

		MessageDigest md = MessageDigest.getInstance(algo);
		mdOutput = md.digest(strBytes);
		
		StringBuilder sb = new StringBuilder();

		for (int j = 0; j < mdOutput.length; j++) {
			s = Integer.toHexString(mdOutput[j]);
			sb.append(s.substring(s.length() - 2));
		}
		
		for(int i = 0; i < num; i++) {
			hash[i] = Integer.valueOf(sb.substring(i, i + 1), 16) % num;
		}
		
		sb.setLength(0);
		
		for (int i = 0; i < num; i++) {
			sb.append(hash[i]);
		}

		return sb.toString();
	}

	/**
	 * @param sqlDate
	 * @return javaDate
	 */
	public static Date sqlDate2UtilDate(final java.sql.Date sqlDate) {
		if (null == sqlDate)
			return null;

		return new Date(sqlDate.getTime());
	}

	private static boolean isValidLogin(final UserDetails userDetails) {

		return userDetails.isAccountNonExpired()
				&& userDetails.isAccountNonLocked()
				&& userDetails.isCredentialsNonExpired()
				&& userDetails.isEnabled();
	}

	private static int[] convertHexToIntArray(final String hash, final int mod) {

		if (null == hash || hash.length() == 0 || mod < 1) {
			return new int[] {};
		}

		int[] code = new int[hash.length()];
		String str;
		int c;

		for (int i = 0; i < hash.length(); i++) {
			str = hash.substring(i, i + 1);
			try {
				c = Integer.valueOf(str, 16);
				code[i] = c % mod;
			} catch (Exception e) {
				code[i] = 0;
			}
		}
		
		return code;
	}

	private static int[] getEmptyArray(final int len) {
		if (len <= 0)
			return new int[] {};

		int[] array = new int[len];

		Arrays.fill(array, -1);

		return array;

	}

	private static boolean compareArrays(final int[] currKey,
			final int[] secKey, int index) {
		boolean equals = true;
		int len;
		int j;

		// add the key
		for (j = 0; j < currKey.length; j++) {
			if (currKey[j] == -1) {
				currKey[j] = index;
				j++;
				break;
			}
		}

		// length till it should be compared
		len = j;

		System.out.println("curr: " + Arrays.toString(currKey));

		for (j = len - 1; j >= 0; j--) {
			// System.out.println(j);

			equals = true;

			for (int i = 0; i <= j; i++) {
				if (currKey[i] != secKey[i]) {
					equals = false;
					break;
				}
			}
			
			if (equals) {
				break;
			}

			// left shift array
			for (int i = 0; i <= j - 1; i++) {
				currKey[i] = currKey[i + 1];
			}

			currKey[j] = -1;
		}

		return equals;
	}

}
