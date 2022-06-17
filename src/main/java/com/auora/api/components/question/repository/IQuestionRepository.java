package com.auora.api.components.question.repository;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.question.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IQuestionRepository extends CrudRepository<Question, Long> {

	List<Question> findAllByfkAccountId(Account fkAccountId);
}
