package com.auora.api.components.account.services.crud.impl;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.services.crud.IAccountService;
import com.auora.api.components.account.services.crud.ILoginService;
import com.auora.api.components.account.services.crud.IRegisterService;

import java.util.List;

public class AccountService implements IAccountService, IRegisterService, ILoginService {

	@Override
	public Boolean login(String email, String password) {
		return null;
	}

	@Override
	public Boolean register(String email, String password) {
		return null;
	}

	@Override
	public AccountDTO getAccount(String accountId) {
		return null;
	}

	@Override
	public List<AccountDTO> getAllAccounts() {
		return null;
	}

	@Override
	public Boolean update(String email, String password, String newEmail, String newPassword) {
		return null;
	}

	@Override
	public Boolean delete(String email, String password) {
		return null;
	}
}
