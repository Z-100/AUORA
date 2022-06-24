package com.auora.api.components.account.services.crud;

public interface IRegisterService {

	Boolean register(String email, String password, String validationSentence);
}
