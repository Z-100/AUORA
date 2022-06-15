package com.auora.api.service.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.other.Validate;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PasswordValidationService implements IPasswordValidationService {

	private IAccountRepository accountRepository;

	@Override
	public Account validate(String email, String password) {
		Validate.notNull(email);
		Validate.notNull(password);

		return accountRepository.findByEmailAndPassword(email, password);
	}
}
