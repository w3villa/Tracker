package com.w3villa.daoImpl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.w3villa.dao.UserDAO;
import com.w3villa.domain.Users;

@Component
public class UserDAOImpl extends CommonDAOImpl<Users> implements UserDAO {

	private SessionFactory sessionFactory;

	@Autowired
	public UserDAOImpl(SessionFactory sessionFactory) {
		super(Users.class,sessionFactory);
		this.sessionFactory = sessionFactory;
	}

	public Users saveUser(Users users) throws Exception {
		Integer id = (Integer) sessionFactory.getCurrentSession().save(users);
		users.setId(id);
		return users;
	}

	public Users getUser(String email) throws Exception {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Users.class);
		criteria.add(Restrictions.eq("email", email));
		Users users = (Users) criteria.uniqueResult();

		return users;
	}

}
