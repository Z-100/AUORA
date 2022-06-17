package com.auora.api.components.comment.repository;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.comment.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends CrudRepository<Comment, Long> {

	List<Comment> findAllByFkAccountId(Account fkAccountId);

	List<Comment> findAllByFkQuestionIdId(Long fkQuestionId);

	List<Comment> findAllByFkThreadIdId(Long fkThreadId);

	void deleteById(Long id);
}
