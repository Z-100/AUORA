package com.auora.api.components.question.repository;

import com.auora.api.components.question.entity.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IQuestionRepository extends CrudRepository<Question, Long> {

	Question findAllByfkAccountId(Long id);

}
