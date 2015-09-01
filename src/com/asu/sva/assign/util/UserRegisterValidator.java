package com.asu.sva.assign.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.sva.assign.bean.UserRegisterBean;

/**
 * @author Vikranth
 *
 */
public class UserRegisterValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if (clazz == UserRegisterBean.class) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {

		UserRegisterBean bean = (UserRegisterBean) obj;

		if (StringUtils.hasText(bean.getUsername())) {
			bean.setUsername(bean.getUsername().trim());
		} else {
			errors.reject("username", "Invalid username");
		}

		if (StringUtils.hasText(bean.getPassword())) {
			if (!bean.getPassword().equals(bean.getPassword_confirm())) {
				errors.reject("password", "Password do not match");
			}
		} else {
			errors.reject("password", "Invalid password");
		}

	}

}
