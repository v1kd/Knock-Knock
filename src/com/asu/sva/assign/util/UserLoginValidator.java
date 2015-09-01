package com.asu.sva.assign.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.sva.assign.bean.UserLoginBean;

/**
 * @author Vikranth
 *
 */
public class UserLoginValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if (clazz == UserLoginBean.class) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {
		UserLoginBean bean = (UserLoginBean) obj;

		if (StringUtils.hasText(bean.getUsername())) {
			bean.setUsername(bean.getUsername().trim());
		} else {
			errors.reject("username", "Invalid username");
		}

		if (StringUtils.hasText(bean.getPassword())) {
			errors.reject("password", "Invalid password");
		}

	}

}
