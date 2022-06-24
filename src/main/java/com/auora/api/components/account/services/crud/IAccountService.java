package com.auora.api.components.account.services.crud;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.entity.Account;

import java.util.List;

public interface IAccountService {

	Account getAccount(String accountId);

	List<AccountDTO> getAllAccounts();

	Boolean update(String email, String password, String newEmail, String newPassword);

	Boolean delete(String email, String password);

	Boolean forgottenPassword(String email, String newPassword, String validationSentence);
}
