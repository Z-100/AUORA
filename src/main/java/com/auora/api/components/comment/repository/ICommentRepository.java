package com.auora.api.components.comment.repository;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.comment.entity.Comment;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.entity.Thread;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findAllByFkAccountId(Account fkAccountId);

	List<Comment> findAllByFkQuestionId(Question fkQuestionId);

	List<Comment> findAllByFkThreadId(Thread fkThreadId);

	void deleteById(Long id);
}
