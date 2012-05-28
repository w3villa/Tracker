package com.w3villa.dao;

import java.io.Serializable;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.w3villa.domain.Users;


public interface UserDAO<T> extends Serializable,CommonDAO<Users> {

	public Users saveUser(Users users) throws Exception;
	public Users getUser(String email) throws Exception;
}
