package com.auora.api.components.account.repository;

import com.auora.api.components.account.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface IAccountRepository extends CrudRepository<Account, Long> {

	Account findByEmail(String email);

	Account findByEmailAndPassword(String email, String password);
}
