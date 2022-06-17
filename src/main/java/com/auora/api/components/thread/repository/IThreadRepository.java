package com.auora.api.components.thread.repository;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.thread.entity.Thread;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IThreadRepository extends CrudRepository<Thread, Long> {

	Optional<Thread> findById(Long id);

	List<Thread> findAllByFkAccountId(Account fkAccountId);

	Iterable<Thread> findAll();
}
