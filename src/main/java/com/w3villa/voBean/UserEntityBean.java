package com.w3villa.voBean;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
public class UserEntityBean extends ExtendMe {

	@Size(min = 1, max = 10, message = "User Id size lies between 1 to 10 character long.")
	@Pattern(regexp = "^[0-9]+$", message = "only integer value allowed in user id.")
	private String id;

	@Pattern(regexp = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", message = "Please enter email in the format xxx@xx.com")
	private String email;
	private String fullName;
	private String nickName;
	@Size(min = 1, max = 20, message = "Password size lies between 1 to 20 character long.")
	private String userPassword;
	@Size(min = 1, max = 20, message = "Password size lies between 1 to 20 character long.")
	private String ReTypePassword;
	private String isActive;
	private String userRole;
	private String lastReset;
	private String isLoggedIn;
	private String secretQuestionId;
	private String secretAnswer;

	public String getReTypePassword() {
		return ReTypePassword;
	}

	public void setReTypePassword(String reTypePassword) {
		ReTypePassword = reTypePassword;
	}

	public String getSecretQuestionId() {
		return secretQuestionId;
	}

	public void setSecretQuestionId(String secretQuestionId) {
		this.secretQuestionId = secretQuestionId;
	}

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getLastReset() {
		return lastReset;
	}

	public void setLastReset(String lastReset) {
		this.lastReset = lastReset;
	}

	public String getIsLoggedIn() {
		return isLoggedIn;
	}

	public void setIsLoggedIn(String isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

}
