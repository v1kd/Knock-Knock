package com.asu.sva.assign.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.constants.AppConstants;

/**
 * @author Vikranth
 *
 */
public class SVAAppAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest req,
			HttpServletResponse res, Authentication auth) throws IOException,
			ServletException {

		AppUserBean authUser = (AppUserBean) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();

		AppUtils.setSecretCode(authUser);

		HttpSession session = req.getSession(true);
		session.setAttribute(AppConstants.AUTH_USER_STR, authUser);

		res.sendRedirect(req.getContextPath() + "/message/list");

	}

}
