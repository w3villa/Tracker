package com.w3villa.voBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class ExtendMe {

	private List<String> classParameterList = new ArrayList<String>();

	private String createdBy;
	private String createdDate;
	private String updatedBy;
	private String updatedDate;

	public List<String> getClassParameterList() {
		for (Field field : this.getClass().getDeclaredFields())
			if (!"classParameterList".equals(field.getName())
					&& !"createdBy".equals(field.getName())
					&& !"createdDate".equals(field.getName())
					&& !"updatedBy".equals(field.getName())
					&& !"updatedDate".equals(field.getName())
					&& !"ReTypePassword".equals(field.getName()))
				classParameterList.add(field.getName());

		return classParameterList;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDt) {
		this.createdDate = createdDt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	
}
