package com.auora.api.components.account.services.crud.impl;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.components.account.services.crud.IAccountService;
import com.auora.api.components.account.services.crud.ILoginService;
import com.auora.api.components.account.services.crud.IRegisterService;
import com.auora.api.components.account.services.mapper.AAccountMapper;
import com.auora.api.other.Validate;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@AllArgsConstructor
public class AccountService implements IAccountService, IRegisterService, ILoginService {

	private IAccountRepository accountRepository;

	private IPasswordValidationService passwordValidationService;
	private AAccountMapper accountMapper;

	@Override
	public Boolean login(String email, String password) {
		return passwordValidationService.validate(email, password) != null;
	}

	@Override
	public Boolean register(String email, String password) {
		Validate.notNull(email);
		Validate.notNull(password);

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
		Validate.notNull(email);

		return accountRepository.findByEmail(email);
	}

	@Override
	public List<AccountDTO> getAllAccounts() {

		List<AccountDTO> accountDTOs = new ArrayList<>();

		try {
			accountRepository.findAll().forEach(account -> {
				if (account != null) {
					accountDTOs.add(accountMapper.toDTO(account));
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			return null;
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

		accountRepository.delete(accountRepository.findByEmailAndPassword(email, password));

		return accountRepository.findByEmail(email) == null;
	}
}
