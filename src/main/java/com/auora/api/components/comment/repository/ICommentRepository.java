package com.auora.api.components.comment.repository;

import com.auora.api.components.comment.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICommentRepository extends CrudRepository<Comment, Long> {

	Comment findAllByFkAccountId(Long fkAccountId);

	Comment findAllByFkQuestionId(Long fkQuestionId);

	Comment findAllByFkThreadId(Long fkThreadId);
}
