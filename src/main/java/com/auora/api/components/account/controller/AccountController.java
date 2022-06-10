package com.auora.api.components.account.controller;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.services.crud.impl.AccountService;
import com.auora.api.other.Constants;
import com.auora.api.other.JsonString;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_ACCOUNT)
@AllArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@PostMapping(Constants.URL_LOGIN)
	public ResponseEntity<?> login(
			@RequestHeader("email") final String email,
			@RequestHeader("password") final String password) {

		return accountService.login(email, password) ?
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_REGISTER)
	public ResponseEntity<?> register(
			@RequestHeader("email") final String email,
			@RequestHeader("password") final String password) {

		return accountService.register(email, password) ?
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@GetMapping(Constants.URL_GET + "/{email}")
	public ResponseEntity<?> getAccount(
			@PathVariable("email") String email) {

		AccountDTO accountDTO = accountService.getAccount(email);

		return accountDTO == null ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(accountDTO, HttpStatus.OK);
	}

	@GetMapping(Constants.URL_GET + Constants.URL_ALL)
	public ResponseEntity<?> getAllAccounts() {

		List<AccountDTO> accountDTOs = accountService.getAllAccounts();

		return accountDTOs == null ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(accountDTOs, HttpStatus.OK);
	}

	@PostMapping(Constants.URL_DELETE)
	public ResponseEntity<?> deleteAccount(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password) {

		return accountService.delete(email, password) ?
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_UPDATE)
	public ResponseEntity<?> updateAccount(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("new-email") String newEmail,
			@RequestHeader("new-password") String newPassword) {

		return accountService.update(email, password, newEmail, newPassword) ?
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}
}