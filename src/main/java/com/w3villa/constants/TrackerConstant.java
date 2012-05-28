package com.w3villa.constants;

import java.util.HashMap;
import java.util.Map;

public class TrackerConstant {
	public static String UPLOAD_STATUS_FAIL = "FAIL";
	public static String UPLOAD_STATUS_PASS = "PASS";
	public static String SECRET_QUESTION_BEAN = "SecretQuestionBean";
	public static String USER_ENTITY_BEAN = "UserEntityBean";
	public static Map<String, String> serviceMap = new HashMap<String, String>();
	static {
		serviceMap.put(SECRET_QUESTION_BEAN, "secretQuestionService");
		serviceMap.put(USER_ENTITY_BEAN, "userService");
	}
	public static Map<String, String> modelAttributeMap = new HashMap<String, String>();
	static {
		modelAttributeMap.put(SECRET_QUESTION_BEAN, "secretQuestionBean");
		modelAttributeMap.put(USER_ENTITY_BEAN, "userEntityBean");
	}
}
