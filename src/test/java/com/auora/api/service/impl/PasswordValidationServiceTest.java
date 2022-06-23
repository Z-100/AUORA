package com.auora.api.service.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.other.Constants;
import com.auora.api.service.PasswordValidationService;
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
class PasswordValidationServiceTest {

	@Mock
	IAccountRepository accountRepository;

	@InjectMocks
	PasswordValidationService passwordValidation;

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

		Account validatedAccount = passwordValidation.validate("email", "password");

		assertEquals(mockAccount.getEmail(), validatedAccount.getEmail());
		assertEquals(mockAccount.getPassword(), validatedAccount.getPassword());
	}

	@Test
	void testEmailIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> passwordValidation.validate(null, "sas"));

		Assertions.assertEquals(Constants.EMAIL_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testPasswordIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> passwordValidation.validate("sas", null));

		Assertions.assertEquals(Constants.PASSWORD_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testParamsAreNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> passwordValidation.validate(null, null));

		Assertions.assertEquals(Constants.EMAIL_NOT_NULL, thrown.getMessage());
	}
}
