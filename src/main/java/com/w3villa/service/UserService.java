package com.w3villa.service;

import java.io.Serializable;

import org.springframework.transaction.annotation.Transactional;

import com.w3villa.voBean.UserEntityBean;

public interface UserService extends Serializable,CommonService {
	public UserEntityBean saveUser(UserEntityBean userEntityBean)
			throws Exception;

	public UserEntityBean getUser(String userNameOrEmail)
			throws Exception;
}
