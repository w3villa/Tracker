package com.w3villa.dao;

import com.w3villa.domain.SecretQuestionMst;

public interface SecretQuestionMstDAO {
	public SecretQuestionMst saveSecretQuestionMstDAO(SecretQuestionMst secretQuestionMst) throws Exception;
	public SecretQuestionMst getSecretQuestionMstDAO(Long secretQuestionMstId) throws Exception;
	public SecretQuestionMst getSecretQuestionMstDAOList() throws Exception;
}
