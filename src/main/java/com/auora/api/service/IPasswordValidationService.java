package com.auora.api.service;

import com.auora.api.components.account.entity.Account;

public interface IPasswordValidationService {

	Account validate(String email, String password);
}
