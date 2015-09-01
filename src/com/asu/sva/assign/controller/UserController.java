package com.asu.sva.assign.controller;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.bean.PageViewBean;
import com.asu.sva.assign.bean.UserLoginBean;
import com.asu.sva.assign.bean.UserRegisterBean;
import com.asu.sva.assign.constants.AppConstants;
import com.asu.sva.assign.exceptions.SVAAppBException;
import com.asu.sva.assign.exceptions.SVAAppException;
import com.asu.sva.assign.processor.SVAAppProcessor;
import com.asu.sva.assign.util.AppUtils;

/**
 * @author Vikranth
 *
 */
@Controller
@RequestMapping(value = "/user")
@SessionAttributes({ "loginFormBean" })
public class UserController {

	private static final int USER_LOGIN_CODE = 1;

	private static final int USER_REGISTER_CODE = 0;

	private static final Logger LOGGER = Logger
			.getLogger(UserController.class);

	@Autowired
	SVAAppProcessor appProcessor;

	@Autowired
	@Qualifier("userRegisterValidator")
	Validator userRegisterValidator;

	@Autowired
	@Qualifier("userLoginValidator")
	Validator userLoginValidator;

	/**
	 * @return loginFormBean
	 */
	@ModelAttribute("loginFormBean")
	public UserLoginBean populateLoginForm() {
		return new UserLoginBean();
	}

	/**
	 * @param model
	 * @param principal
	 * @param invalidCred
	 * @param loginFormBean
	 * @param session
	 * @return loginPage
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String getLoginPage(
			final Model model,
			final Principal principal,
			@RequestParam(value = "invalidCred", required = false) String invalidCred,
			@ModelAttribute("loginFormBean") UserLoginBean loginFormBean,
			final HttpSession session) {

		AppUserBean authUser;

		PageViewBean page = new PageViewBean("Login page", "User login");
		model.addAttribute("pageView", page);

		if (AppUtils.isLoggedIn()) {
			// display a message that he has already logged in
			// already logged in

			page.setMessage("You have already logged in!");
			model.addAttribute("pageView", page);

			// util to handle secret code
			authUser = (AppUserBean) session
					.getAttribute(AppConstants.AUTH_USER_STR);

			// secret code compare
			AppUtils.trackSecretCode(authUser, USER_LOGIN_CODE);
			// debug secret code
			LOGGER.debug("auth user for login: " + authUser);

			return "message-page";

		} else {
			// Not logged in. Display the form

			if (invalidCred != null
					&& Boolean.TRUE.toString().equalsIgnoreCase(invalidCred)) {
				page.setMessage("Invalid username/password combination");
				page.setValid(false);
			}
		}

		return "login";
	}


	/**
	 * @param model
	 * @param session
	 * @param userRegisterBean
	 * @return registrationPage
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String getRegistrationForm(final Model model,
			final HttpSession session,
			@ModelAttribute("userRegisterBean") UserRegisterBean userRegisterBean) {
		
		AppUserBean authUser;

		PageViewBean page = new PageViewBean("Registration page",
				"User Details");

		model.addAttribute("pageView", page);

		if (AppUtils.isLoggedIn()) {
			// display a message that he has already logged in
			// already logged in

			page.setMessage("You have already registered!");

			// util to handle secret code
			authUser = (AppUserBean) session
					.getAttribute(AppConstants.AUTH_USER_STR);

			// secret code compare
			AppUtils.trackSecretCode(authUser, USER_REGISTER_CODE);
			// debug secret code
			LOGGER.debug("auth user for login: " + authUser);

			return "message-page";

		} else {
			// Not logged in. Display the form
			page.setMessage("You have to register!");
		}

		return "user-register";
	}

	/**
	 * @param model
	 * @param session
	 * @param userRegisterBean
	 * @param result
	 * @return registration
	 */
	@RequestMapping(value = "register", method = RequestMethod.POST)
	public String registerUser(
			final Model model,
			final HttpSession session,
			@ModelAttribute("userRegisterBean") UserRegisterBean userRegisterBean,
			final BindingResult result) {

		PageViewBean page = new PageViewBean("Registration page",
				"User Details");
		boolean isUserExists = false;
		boolean isUserRegistered = false;
		
		model.addAttribute("pageView", page);

		if (AppUtils.isLoggedIn()) {
			// display a message that he has already logged in
			// already logged in

			page.setMessage("You have already registered!");

			return "message-page";

		}
		
		userRegisterValidator.validate(userRegisterBean, result);
		
		// it has errors
		if (result.hasErrors()) {
			return "user-register";
		}

		// is user exists?
		try {
			isUserExists = appProcessor.isUserExists(userRegisterBean
					.getUsername());
		} catch (SVAAppException e) {
			// something's wrong
			page.setValid(false);
			page.setMessage("Something is wrong! Please try again");

			return "user-register";
		} catch (SVAAppBException e) {
		}

		if (isUserExists) {
			// display error message
			page.setValid(false);
			page.setMessage("User already exists");
		} else {
			// save user
			try {
				isUserRegistered = appProcessor.registerUser(userRegisterBean);
			} catch (SVAAppException e) {
				page.setMessage("Something's wrong. Please try again!");
			} catch (SVAAppBException e) {
				page.setMessage("Save action failed!");
			}

			page.setValid(isUserRegistered);

			if (isUserRegistered) {
				page.setMessage("User registered!!");
				return "message-page";
			}
		}

		return "user-register";
	}


	/*
	 * private static boolean isLoggedIn() {
	 * 
	 * Object principal = (SecurityContextHolder.getContext()
	 * .getAuthentication() == null ? null : SecurityContextHolder
	 * .getContext().getAuthentication().getPrincipal());
	 * 
	 * if (principal != null && principal instanceof UserDetails &&
	 * isValidLogin((UserDetails) principal)) { // loggedIn return true; }
	 * 
	 * return false; }
	 * 
	 * private static boolean isValidLogin(final UserDetails userDetails) {
	 * 
	 * return userDetails.isAccountNonExpired() &&
	 * userDetails.isAccountNonLocked() && userDetails.isCredentialsNonExpired()
	 * && userDetails.isEnabled(); }
	 */

}
