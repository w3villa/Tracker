package com.w3villa.dao;

import java.util.List;

public interface CommonDAO<T> {
	List<Object> getAllRecords()throws Exception;
}
