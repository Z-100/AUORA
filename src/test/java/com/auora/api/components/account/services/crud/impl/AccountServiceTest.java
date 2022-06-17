package com.auora.api.components.account.services.crud.impl;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.repository.IAccountRepository;
import com.auora.api.components.account.services.mapper.AAccountMapper;
import com.auora.api.other.Constants;
import com.auora.api.service.impl.PasswordValidationService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

	@Mock
	private IAccountRepository accountRepository;

	@Mock
	private PasswordValidationService passwordValidationService;

	@Mock
	private AAccountMapper accountMapper;

	@InjectMocks
	AccountService accountService;

	Account account = new Account();

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
		Mockito.when(passwordValidationService.validate(correctEmail, correctPassword)).thenReturn(account);

		assertTrue(accountService.login(correctEmail, correctPassword));
	}

	@Test
	void testLoginIncorrectly() {
		Mockito.when(passwordValidationService.validate(inCorrectEmail, inCorrectPassword)).thenReturn(null);

		assertFalse(accountService.login(inCorrectEmail, inCorrectPassword));
	}

	@Test
	void testRegister() {
		Account mockAccountInDB = new Account();
		mockAccountInDB.setEmail(correctEmail);
		mockAccountInDB.setPassword(correctPassword);

		Mockito.when(accountRepository.findByEmail(correctEmail)).thenReturn(mockAccountInDB);

		assertTrue(accountService.register(correctEmail, correctPassword));
	}

	@Test
	void testRegisterEmailIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> accountService.register(null, "sas"));

		Assertions.assertEquals(Constants.EMAIL_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testRegisterPasswordIsNull() {
		IllegalArgumentException thrown = Assertions.assertThrows(
				IllegalArgumentException.class, () -> accountService.register("sas", null));

		Assertions.assertEquals(Constants.PASSWORD_NOT_NULL, thrown.getMessage());
	}

	@Test
	void testGetAccount() {
		Account mockAccountInDB = new Account();
		mockAccountInDB.setEmail(correctEmail);
		mockAccountInDB.setPassword(correctPassword);

		Mockito.when(accountRepository.findByEmail(correctEmail)).thenReturn(mockAccountInDB);

		Account accountFromService = accountService.getAccount(correctEmail);

		assertAll(
				() -> assertEquals(accountFromService.getEmail(), mockAccountInDB.getEmail()),
				() -> assertEquals(accountFromService.getPassword(), mockAccountInDB.getPassword())
		);
	}

	@Test
	void testGetAllAccounts() {
		Account mock_1 = new Account();
		mock_1.setEmail(correctEmail);
		mock_1.setPassword(correctPassword);

		Account mock_2 = new Account();
		mock_2.setEmail(correctEmail);
		mock_2.setPassword(correctPassword);

		Mockito.when(accountRepository.findAll()).thenReturn(List.of(mock_1, mock_2));

		List<AccountDTO> allAccounts = accountService.getAllAccounts();

		allAccounts.forEach(System.out::println);
		//why? TODO: Make work
		assertAll(
				() -> assertEquals(allAccounts.get(0).getEmail(), mock_1.getEmail()),
				() -> assertEquals(allAccounts.get(1).getEmail(), mock_2.getEmail())
		);
	}

	@Test
	void testUpdate() {
		//TODO
	}

	@Test
	void testDelete() {
		//TODO
	}
}