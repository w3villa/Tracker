package com.w3villa.controller;

import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.w3villa.constants.TrackerConstant;
import com.w3villa.json.JsonResponse;
import com.w3villa.service.CommonService;
import com.w3villa.service.UserService;
import com.w3villa.voBean.UserEntityBean;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! the client locale is " + locale.toString());
		HttpSession session = request.getSession();
		if (!session.isNew()) {
			return "loginSuccess";
		}
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute(new UserEntityBean());
		return "home";
	}

	@RequestMapping(value = "/Login", method = RequestMethod.POST)
	public String loginMethod(@Valid UserEntityBean userEntityBean,
			BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) {
			model.addAttribute("loginFail", "fail");
			return "home";
		} else {
			try {
				UserEntityBean entityBean = userService.getUser(userEntityBean
						.getEmail());
				if (entityBean.getUserPassword().equals(
						userEntityBean.getUserPassword())) {
					HttpSession session = request.getSession();
					session.setAttribute("userEntityBean", entityBean);
					return "loginSuccess";
				} else {
					model.addAttribute("loginFail", "fail");
					return "home";
				}
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("loginFail", "fail");
				return "home";
			}
		}
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.POST)
	public @ResponseBody
	JsonResponse getUsers(Model model, HttpServletRequest request) {
		JsonResponse jsonResponse = new JsonResponse();
		UserEntityBean userEntityBean = new UserEntityBean();
		userEntityBean.setFullName(request.getParameter("FullName"));
		userEntityBean.setEmail(request.getParameter("email"));
		userEntityBean.setId("1");
		jsonResponse.setStatus("SUCCESS");
		jsonResponse.setResult(userEntityBean);
		return jsonResponse;
	}

	@RequestMapping(value = "/RegisterMe", method = RequestMethod.GET)
	public String RegisterMeNavigate(Model model, HttpServletRequest request) {
		logger.info("RegisterMeNavigate() entry.");
		HttpSession session = request.getSession();
		session.invalidate();
		model.addAttribute("secretQuestionMap", getSecretQuestionMap());
		model.addAttribute(new UserEntityBean());
		logger.info("RegisterMeNavigate() exit.");
		return "registerMe";
	}

	public Map<String, String> getSecretQuestionMap() {
		logger.info("getSecretQuestionMap() entry.");
		Map<String, String> secretQuesMap = new TreeMap<String, String>();
		secretQuesMap.put("1", "What is your first school name?");
		secretQuesMap.put("2", "What is your Mother maiden name?");
		secretQuesMap.put("3", "Brand of your first mobile?");
		secretQuesMap.put("4", "Who is your first Love?");
		secretQuesMap.put("5", "What is your faviourite past time?");
		logger.info("getSecretQuestionMap() exit.");
		return secretQuesMap;

	}

	@RequestMapping(value = "/RegisterMe", method = RequestMethod.POST)
	public String RegisterMe(@Valid UserEntityBean userEntityBean,
			BindingResult result, Model model, HttpServletRequest request) {
		logger.info("RegisterMe() entry.");
		if (result.hasErrors()) {
			model.addAttribute("loginFail", "fail");
			logger.info("RegisterMe() exit.");
			return "home";
		} else {
			HttpSession session = request.getSession();
			try {
				userService.saveUser(userEntityBean);
				session.setAttribute("emailId", userEntityBean.getEmail());
				logger.info("RegisterMe() exit.");
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Error in Controller");
			}
			return "loginSuccess";
		}

	}

	@RequestMapping(value = "/crud", method = RequestMethod.GET)
	public String crudOperation(Model model, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.isNew()) {
			model.addAttribute(new UserEntityBean());
			return "home";
		}
		String tableName = request.getParameter("tableName");
		model.addAttribute("tableName", tableName);
		/*String modelAttribute = TrackerConstant.modelAttributeMap
				.get(tableName);
		model.addAttribute("modelAttribute", modelAttribute);*/
		Class tableClass = Class.forName("com.w3villa.voBean." + tableName);
		model.addAttribute("classObject",
				tableClass.getConstructors()[0].newInstance(null));
		String service = TrackerConstant.serviceMap.get(tableName);
		for (Field field : this.getClass().getDeclaredFields()) {
			if (field.getName().equals(service)) {
				CommonService commonService = (CommonService) field.get(this);
				List<Object> objectList = commonService.getAllRecords();
				model.addAttribute("objects", objectList);
				System.out.println(objectList);
			}
		}
		return "commonCrudInclude";
	}
	
	@RequestMapping(value = "/doPostForTable", method = RequestMethod.POST)
	public String postCrudOperation(Model model, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.isNew()) {
			model.addAttribute(new UserEntityBean());
			return "home";
		}
		System.out.println("in postCrudOperation()");
		return "commonCrudInclude";
	}

}
