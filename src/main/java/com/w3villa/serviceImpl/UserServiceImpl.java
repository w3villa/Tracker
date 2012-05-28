package com.w3villa.serviceImpl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.w3villa.dao.UserDAO;
import com.w3villa.domain.Users;
import com.w3villa.service.UserService;
import com.w3villa.voBean.UserEntityBean;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDAO userDAO;

	public UserEntityBean saveUser(UserEntityBean userEntityBean)
			throws Exception {
		logger.info("saveUser() entry.");
		Users users = new Users();
		userEntityBean.setIsActive("Y");
		userEntityBean.setUserRole("admin");
		userEntityBean.setCreatedBy("priyank");
		userEntityBean.setUpdatedBy("Ishank");
		getUserDomainFromVo(userEntityBean, users);
		users.setCreatedDate(new Date());
		users.setUpdatedDate(new Date());
		users.setLastReset(new Date());
		System.out.println(users);
		userDAO.saveUser(users);
		getUserVoFromDomain(userEntityBean, users);
		logger.info("saveUser() exit.");
		return null;
	}

	public UserEntityBean getUser(String userNameOrEmail)
			throws Exception {
		Users users = userDAO.getUser(userNameOrEmail);
		UserEntityBean entityBean = getUserVoFromDomain(new UserEntityBean(),users);
		return entityBean;
	}

	private Users getUserDomainFromVo(UserEntityBean userEntityBean, Users users) {
		users.setCreatedBy(userEntityBean.getCreatedBy());
		// users.setCreatedDate(userEntityBean.getCreatedDate());
		users.setEmail(userEntityBean.getEmail());
		users.setFullName(userEntityBean.getFullName());
		users.setIsActive(userEntityBean.getIsActive());
		users.setIsLoggedIn(userEntityBean.getIsLoggedIn());
		// users.setLastReset(userEntityBean.getLastReset());
		users.setNickName(userEntityBean.getNickName());
		users.setSecretAnswer(userEntityBean.getSecretAnswer());
		users.setSecretQuestionId(Integer.parseInt(userEntityBean
				.getSecretQuestionId()));
		users.setUpdatedBy(userEntityBean.getUpdatedBy());
		// users.setUserId(userEntityBean.getUserId());
		users.setUserPassword(userEntityBean.getUserPassword());
		users.setUserRole(userEntityBean.getUserRole());
		return users;
	}

	private UserEntityBean getUserVoFromDomain(UserEntityBean userEntityBean,
			Users users) {
		userEntityBean.setCreatedBy(users.getCreatedBy());
		userEntityBean.setCreatedDate(users.getCreatedDate().toString());
		userEntityBean.setEmail(users.getEmail());
		userEntityBean.setFullName(users.getFullName());
		userEntityBean.setIsActive(users.getIsActive());
		userEntityBean.setIsLoggedIn(users.getIsLoggedIn());
		userEntityBean.setLastReset(users.getLastReset().toString());
		userEntityBean.setNickName(users.getNickName());
		userEntityBean.setSecretAnswer(users.getSecretAnswer());
		userEntityBean
				.setSecretQuestionId(users.getSecretQuestionId().toString());
		userEntityBean.setUpdatedBy(users.getUpdatedBy());
		userEntityBean.setUpdatedDate(users.getUpdatedDate().toString());
		userEntityBean.setId(users.getId().toString());
		userEntityBean.setUserPassword(users.getUserPassword());
		userEntityBean.setUserRole(users.getUserRole());
		return userEntityBean;
	}

	public List<Object> getAllRecords() throws Exception {
		List<Object> allRecords = userDAO.getAllRecords();
		return allRecords;
	}

}
