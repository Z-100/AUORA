package com.auora.api.components.account.repository;

import com.auora.api.components.account.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAccountRepository extends CrudRepository<Account, Long> {

	Account findByEmail(String email);

	Account findByEmailAndPassword(String email, String password);

	Account findByEmailAndValidationSentence(String email, String validationSentence);

	List<Account> findAll();

	void deleteById(Long id);
}
