package com.auora.api.components.account.services.crud.impl;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.components.account.services.crud.IAccountService;
import com.auora.api.components.account.services.crud.ILoginService;
import com.auora.api.components.account.services.crud.IRegisterService;
import com.auora.api.components.account.services.mapper.AAccountMapper;
import com.auora.api.other.Constants;
import com.auora.api.other.Validate;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class AccountService implements IAccountService, IRegisterService, ILoginService {

	private final IAccountRepository accountRepository;

	private final IPasswordValidationService passwordValidationService;
	private final AAccountMapper accountMapper;

	@Override
	public Boolean login(String email, String password) {
		Account account = passwordValidationService.validate(email, password);

		Validate.notNull(account, Constants.INVALID_PASSWORD);

		return true;
	}

	@Override
	public Boolean register(String email, String password) {
		Validate.notNull(email, Constants.EMAIL_NOT_NULL);
		Validate.notNull(password, Constants.PASSWORD_NOT_NULL);

		Account possiblyExistingAccount = accountRepository.findByEmail(email);

		if (possiblyExistingAccount != null) {
			throw new IllegalArgumentException(Constants.ALREADY_EXISTS);
		}

		Account account = new Account();
		account.setEmail(email);
		account.setPassword(password);

		accountRepository.save(account);

		Account savedAccount = accountRepository.findByEmail(email);

		return savedAccount.getEmail().equals(email)
				&& savedAccount.getPassword().equals(password);
	}

	@Override
	public Account getAccount(String email) {
		Validate.notNull(email, Constants.NOT_EXISTS);

		return accountRepository.findByEmail(email);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {

		List<AccountDTO> accountDTOs = new ArrayList<>();
		List<Account> accounts = accountRepository.findAll();

		Validate.notEmpty(accounts, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		try {
			accounts.forEach(account -> {
				if (account != null) {
					accountDTOs.add(accountMapper.toDTO(account));
				}
			});
		} catch (Exception e) {
			throw new IllegalArgumentException(e.getMessage());
		}

		return accountDTOs;
	}

	@Override
	public Boolean update(String email, String password, String newEmail, String newPassword) {
		Validate.notNull(email);
		Validate.notNull(password);
		Validate.notNull(newEmail);
		Validate.notNull(newPassword);

		Account account = passwordValidationService.validate(email, password);

		Validate.notNull(account, new String[]{ Constants.SOMETHING_WRONG, Constants.INVALID_PASSWORD });

		account.setEmail(newEmail);
		account.setPassword(newPassword);

		accountRepository.save(account);

		Account updatedAccount = accountRepository.findByEmail(newEmail);

		return !updatedAccount.getEmail().equals(email)
				&& !updatedAccount.getPassword().equals(password)
				&& updatedAccount.getEmail().equals(newEmail)
				&& updatedAccount.getPassword().equals(newPassword);
	}

	@Override
	public Boolean delete(String email, String password) {
		Validate.notNull(email);
		Validate.notNull(password);

		Account toBeDeleted = accountRepository.findByEmailAndPassword(email, password);

		Validate.notNull(toBeDeleted, new String[] { Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });
		accountRepository.delete(toBeDeleted);

		return accountRepository.findByEmail(email) == null;
	}
}
