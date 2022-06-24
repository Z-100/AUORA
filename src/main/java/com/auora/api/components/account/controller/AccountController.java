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
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_REGISTER)
	public ResponseEntity<?> register(
			@RequestHeader("email") final String email,
			@RequestHeader("password") final String password,
			@RequestHeader("validation-sentence") final String validationSentence) {

		return accountService.register(email, password, validationSentence) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@GetMapping(Constants.URL_GET + Constants.URL_ALL)
	public ResponseEntity<?> getAllAccounts() {

		List<AccountDTO> accountDTOs = accountService.getAllAccounts();

		return accountDTOs != null ?
				new ResponseEntity<>(accountDTOs, HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_DELETE)
	public ResponseEntity<?> deleteAccount(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password) {

		return accountService.delete(email, password) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_UPDATE)
	public ResponseEntity<?> updateAccount(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("new-email") String newEmail,
			@RequestHeader("new-password") String newPassword) {

		return accountService.update(email, password, newEmail, newPassword) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_PW_FORGOTTEN)
	public ResponseEntity<?> forgottenPassword(
			@RequestHeader("email") final String email,
			@RequestHeader("new-password") final String newPassword,
			@RequestHeader("validation-sentence") final String validationSentence) {

		return accountService.forgottenPassword(email, newPassword, validationSentence) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.INVALID_PASSWORD, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
