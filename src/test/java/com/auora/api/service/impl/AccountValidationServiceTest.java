package com.auora.api.service.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.other.Constants;
import com.auora.api.service.EntityFactory;
import org.junit.jupiter.api.Assertions;
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
class AccountValidationServiceTest {

	@Mock
	IAccountRepository accountRepository;

	@InjectMocks
	AccountValidationService passwordValidation;

	Account mockAccount;

	void setUpMockAccount() {
		mockAccount = EntityFactory.getInstance(Account.class);
		mockAccount.setEmail("email");
		mockAccount.setPassword("password");

		Mockito.when(accountRepository.findByEmailAndPassword("email", "password"))
				.thenReturn(mockAccount);
	}

	@Test
	void testPasswordEnteredCorrectly() {

		setUpMockAccount();

		Account validatedAccount = passwordValidation.validateLogin("email", "password");

		assertEquals(mockAccount.getEmail(), validatedAccount.getEmail());
		assertEquals(mockAccount.getPassword(), validatedAccount.getPassword());
	}

	@Test
	void testEmailIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> passwordValidation.validateLogin(null, "sas"));

		Assertions.assertEquals(Constants.EMAIL_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testPasswordIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> passwordValidation.validateLogin("sas", null));

		Assertions.assertEquals(Constants.PASSWORD_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testParamsAreNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> passwordValidation.validateLogin(null, null));

		Assertions.assertEquals(Constants.EMAIL_NOT_NULL, thrown.getMessage());
	}
}
