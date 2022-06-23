package com.auora.api.components.account.services.crud.impl;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.components.account.services.crud.IAccountService;
import com.auora.api.components.account.services.crud.ILoginService;
import com.auora.api.components.account.services.crud.IRegisterService;
import com.auora.api.components.account.services.mapper.AAccountMapper;
import com.auora.api.other.Constants;
import com.auora.api.service.Validator;
import com.auora.api.service.IPasswordValidationService;
import com.auora.api.service.impl.EntityFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class AccountService implements IAccountService, IRegisterService, ILoginService {

	private final IAccountRepository accountRepository;

	private final IPasswordValidationService passwordValidation;
	private final AAccountMapper accountMapper;

	@Override
	public Boolean login(String email, String password) {
		Account account = passwordValidation.validate(email, password);

		Validator.notNull(account, Constants.INVALID_PASSWORD);

		return true;
	}

	@Override
	public Boolean register(String email, String password) {
		Validator.notNull(email, Constants.EMAIL_NOT_NULL);
		Validator.notNull(password, Constants.PASSWORD_NOT_NULL);

		Account possiblyExistingAccount = accountRepository.findByEmail(email);

		if (possiblyExistingAccount != null) {
			throw new IllegalArgumentException(Constants.ALREADY_EXISTS);
		}

		Account account = EntityFactory.getInstance(Account.class);
		account.setEmail(email);
		account.setPassword(password);

		accountRepository.save(account);

		Account savedAccount = accountRepository.findByEmail(email);

		return savedAccount.getEmail().equals(email)
				&& savedAccount.getPassword().equals(password);
	}

	@Override
	public Account getAccount(String email) {
		Validator.notNull(email, Constants.NOT_EXISTS);

		return accountRepository.findByEmail(email);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {

		List<AccountDTO> accountDTOs =  EntityFactory.getListInstance(AccountDTO.class);
		List<Account> accounts = accountRepository.findAll();

		Validator.notEmpty(accounts, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

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
		Validator.notNull(email);
		Validator.notNull(password);
		Validator.notNull(newEmail);
		Validator.notNull(newPassword);

		Account account = passwordValidation.validate(email, password);

		Validator.notNull(account, new String[]{ Constants.SOMETHING_WRONG, Constants.INVALID_PASSWORD });

		Account possiblyExistingAccount = accountRepository.findByEmail(newEmail);

		if (possiblyExistingAccount != null) {
			throw new IllegalArgumentException(Constants.ALREADY_EXISTS);
		}

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
		Validator.notNull(passwordValidation.validate(email, password), Constants.INVALID_PASSWORD);

		accountRepository.delete(accountRepository.findByEmailAndPassword(email, password));

		return accountRepository.findByEmail(email) == null;
	}
}
