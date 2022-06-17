package com.auora.api.service.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.other.Constants;
import com.auora.api.other.Validator;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PasswordValidationService implements IPasswordValidationService {

	private IAccountRepository accountRepository;

	@Override
	public Account validate(String email, String password) {
		Validator.notNull(email, Constants.EMAIL_NOT_NULL);
		Validator.notNull(password, Constants.PASSWORD_NOT_NULL);

		return accountRepository.findByEmailAndPassword(email, password);
	}
}
