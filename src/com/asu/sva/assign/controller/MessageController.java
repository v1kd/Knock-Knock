package com.asu.sva.assign.controller;

import java.util.List;

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

import com.asu.sva.assign.bean.AppMessageBean;
import com.asu.sva.assign.bean.AppUserBean;
import com.asu.sva.assign.bean.PageViewBean;
import com.asu.sva.assign.exceptions.SVAAppBException;
import com.asu.sva.assign.exceptions.SVAAppException;
import com.asu.sva.assign.processor.SVAAppProcessor;
import com.asu.sva.assign.util.AppUtils;

/**
 * @author Vikranth
 *
 */
@Controller
@RequestMapping("/message")
public class MessageController {
	
	private static final Logger LOGGER = Logger
			.getLogger(MessageController.class);

	private static final int MESSAGES_LIST_CODE = 3;

	private static final int MESSAGES_ADD_CODE = 2;

	@Autowired
	SVAAppProcessor appProcessor;

	@Autowired
	@Qualifier("messageAddValidator")
	Validator messageAddValidator;

	/**
	 * @param model
	 * @param session
	 * @return loginPage
	 */
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public String getMessageList(final Model model, final HttpSession session) {

		PageViewBean page = new PageViewBean("Messages", "All Messages");
		model.addAttribute("pageView", page);
		AppUserBean authUser = AppUtils.getAuthUserFromSession(session);

		AppUtils.trackSecretCode(authUser, MESSAGES_LIST_CODE);
		LOGGER.debug("auth user for message list: " + authUser);
		
		List<AppMessageBean> messages = null;

		try {
			messages = appProcessor.getMessages(authUser);
		} catch (SVAAppException e) {
			page.setValid(false);
			page.setMessage("Something's wrongs. Please try again!!");

			return "message-page";
		} catch (SVAAppBException e) {
			// page.setValid(false);
			// page.setMessage("Something's wrongs. Please try again!!");
		}

		model.addAttribute("messages", messages);

		return "message-list";
	}

	/**
	 * @param model
	 * @param session
	 * @param messageAddBean
	 * @return loginPage
	 */
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public String getMessageForm(final Model model, final HttpSession session,
			@ModelAttribute("messageAddBean") AppMessageBean messageAddBean) {

		PageViewBean page = new PageViewBean("Messages", "Add Message");
		model.addAttribute("pageView", page);
		AppUserBean authUser = AppUtils.getAuthUserFromSession(session);

		AppUtils.trackSecretCode(authUser, MESSAGES_ADD_CODE);
		LOGGER.debug("auth user for message add: " + authUser);

		return "message-add";
	}

	/**
	 * @param model
	 * @param session
	 * @param messageAddBean
	 * @param result
	 * @return messageList
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String saveMessage(final Model model, final HttpSession session,
			@ModelAttribute("messageAddBean") AppMessageBean messageAddBean,
			final BindingResult result) {

		PageViewBean page = new PageViewBean("Messages", "Add Message");
		model.addAttribute("pageView", page);

		AppUserBean authUser = AppUtils.getAuthUserFromSession(session);
		boolean isSuccess = false;

		// validate the form
		messageAddValidator.validate(messageAddBean, result);

		if (result.hasErrors()) {
			return "message-add";
		}

		try {
			isSuccess = appProcessor.saveMessage(authUser, messageAddBean);
		} catch (SVAAppException e) {
			page.setMessage("Something's wrong. Please try again!");
		} catch (SVAAppBException e) {
			page.setMessage("Save action failed!");
		}

		page.setValid(isSuccess);

		if (isSuccess) {
			page.setMessage("Message saved!!");
			return "message-page";
		}

		return "message-add";
	}
}
