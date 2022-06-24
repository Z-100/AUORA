package com.auora.api.service.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.other.Constants;
import com.auora.api.service.IAccountValidationService;
import com.auora.api.service.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AccountValidationService implements IAccountValidationService {

	private IAccountRepository accountRepository;

	@Override
	public Account validateLogin(String email, String password) {
		Validator.notNull(email, Constants.EMAIL_NOT_NULL);
		Validator.notNull(password, Constants.PASSWORD_NOT_NULL);
		//TODO only check email & hash password
		//TODO parameter: account. No database connection
		return accountRepository.findByEmailAndPassword(email, password);
	}

	@Override
	public Account validateSentence(String email, String sentence) {
		Validator.notNull(email, Constants.EMAIL_NOT_NULL);
		Validator.notNull(sentence, Constants.VALIDATION_SENTENCE_NOT_NULL);

		return accountRepository.findByEmailAndValidationSentence(email, sentence);
	}
}
