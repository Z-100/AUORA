package com.auora.api.service;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.other.Constants;
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
		//TODO only check email & hash password
		//TODO parameter: account. No database connection
		return accountRepository.findByEmailAndPassword(email, password);
	}
}
