package com.w3villa.voBean;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class SecretQuestionBean extends ExtendMe {
	private Integer id;
	@Size(min = 1, max = 200, message = "Question description should be between 1 to 200 characters.")
	private String questionDescription;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestionDescription() {
		return questionDescription;
	}

	public void setQuestionDescription(String questionDescription) {
		this.questionDescription = questionDescription;
	}

}
