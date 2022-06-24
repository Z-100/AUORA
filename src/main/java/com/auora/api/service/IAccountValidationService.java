package com.auora.api.service;

import com.auora.api.components.account.entity.Account;

public interface IAccountValidationService {

	Account validateLogin(String email, String password);

	Account validateSentence(String email, String password);
}
