package com.w3villa.daoImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

public class CommonDAOImpl<T> {

	Class<T> className;
	private SessionFactory sessionFactory;

	public CommonDAOImpl(Class<T> className, SessionFactory sessionFactory) {
		this.className = className;
		this.sessionFactory = sessionFactory;
	}

	public List<Object> getAllRecords() throws Exception {
		System.out.println("className : " + className.getName());
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				className);
		criteria.setFirstResult(0);
		criteria.setMaxResults(10);
		criteria.addOrder(Order.asc("createdDate"));
		List<Object> objectList = criteria.list();
		return objectList;
	}

}
