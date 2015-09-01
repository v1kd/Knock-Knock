package com.asu.sva.assign.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.asu.sva.assign.bean.AppMessageBean;

/**
 * @author Vikranth
 *
 */
public class MessageAddValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		if (clazz == AppMessageBean.class)
			return true;

		return false;
	}

	@Override
	public void validate(Object obj, Errors errors) {

		AppMessageBean bean = (AppMessageBean) obj;

		if (!StringUtils.hasText(bean.getTitle())) {
			errors.reject("title", "Invalid title");
		}

		if (!StringUtils.hasText(bean.getMessage())) {
			errors.reject("password", "Invalid message");
		}

	}

}
