package com.auora.api.components.account.services.crud.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.components.account.services.mapper.AAccountMapper;
import com.auora.api.other.Constants;
import com.auora.api.service.EntityFactory;
import com.auora.api.service.impl.AccountValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	private IAccountRepository accountRepository;

	@Mock
	private AccountValidationService accountValidationService;

	@Mock
	private AAccountMapper accountMapper;

	@InjectMocks
	AccountService accountService;

	Account account = EntityFactory.getInstance(Account.class);

	String correctEmail = "correct";
	String inCorrectEmail = "incorrect";
	String correctPassword = "correct";
	String inCorrectPassword = "incorrect";

	@BeforeEach
	public void setup() {
		account.setEmail(correctEmail);
		account.setPassword(correctPassword);
	}

	@Test
	void testLoginCorrectly() {
		Mockito.when(accountValidationService.validateLogin(correctEmail, correctPassword)).thenReturn(account);

		assertTrue(accountService.login(correctEmail, correctPassword));
	}

	@Test
	void testLoginIncorrectly() {
		Mockito.when(accountValidationService.validateLogin(inCorrectEmail, inCorrectPassword)).thenReturn(null);

		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> accountService.login(inCorrectEmail, inCorrectPassword));

		Assertions.assertEquals(Constants.INVALID_PASSWORD, thrown.getMessage());
	}

	@Test
	void testRegisterEmailIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> accountService.register(null, "sas", "sos"));

		Assertions.assertEquals(Constants.EMAIL_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testRegisterPasswordIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> accountService.register("sas", null, "ses"));

		Assertions.assertEquals(Constants.PASSWORD_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testRegisterValSentenceIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> accountService.register("sas", "ses", null));

		Assertions.assertEquals(Constants.VALIDATION_SENTENCE_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testGetAccount() {
		Account mockAccountInDB = EntityFactory.getInstance(Account.class);
		mockAccountInDB.setEmail(correctEmail);
		mockAccountInDB.setPassword(correctPassword);

		Mockito.when(accountRepository.findByEmail(correctEmail)).thenReturn(mockAccountInDB);

		Account accountFromService = accountService.getAccount(correctEmail);

		assertAll(
				() -> assertEquals(accountFromService.getEmail(), mockAccountInDB.getEmail()),
				() -> assertEquals(accountFromService.getPassword(), mockAccountInDB.getPassword())
		);
	}
}
