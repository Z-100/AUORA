package com.auora.api.components.comment.services.crud.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.services.crud.impl.AccountService;
import com.auora.api.components.comment.dto.CommentDTO;
import com.auora.api.components.comment.entity.Comment;
import com.auora.api.components.comment.repository.ICommentRepository;
import com.auora.api.components.comment.services.crud.ICommentService;
import com.auora.api.components.comment.services.mapper.ACommentMapper;
import com.auora.api.components.question.services.crud.impl.QuestionService;
import com.auora.api.components.thread.services.crud.impl.ThreadService;
import com.auora.api.other.Validate;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class CommentService implements ICommentService {

	private ICommentRepository commentRepository;

	private ACommentMapper commentMapper;
	private IPasswordValidationService passwordValidation;

	private AccountService accountService;
	private QuestionService questionService;
	private ThreadService threadService;

	@Override
	public List<CommentDTO> getAllFromAccount(String email) {
		Validate.notNull(email);

		Account account = accountService.getAccount(email);

		return commentRepository.findAllByFkAccountId(account).stream()
				.map(commentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CommentDTO> getAllFromQuestion(String questionId) {
		return null; //TODO
	}

	@Override
	public List<CommentDTO> getAllFromThread(String threadId) {
		return null; //TODO
	}

	@Override
	public Boolean addUpVote(String email, String password, String commentId) {

		Comment comment = getComment(email, password, commentId);

		assert comment != null;
		Long oldCount = comment.getVotes();

		Long id = comment.getId();

		comment.addUpvote();

		Comment updatedComment = commentRepository.findById(id).orElseThrow();

		return Objects.equals(++oldCount, updatedComment.getVotes());
	}

	@Override
	public Boolean addDownVote(String email, String password, String commentId) {
		Comment comment = getComment(email, password, commentId);

		assert comment != null;
		Long oldCount = comment.getVotes();

		Long id = comment.getId();

		comment.addDownVote();

		Comment updatedComment = commentRepository.findById(id).orElseThrow();

		return Objects.equals(--oldCount, updatedComment.getVotes());
	}

	@Override
	public Boolean delete(String email, String password, String commentId) {

		if (passwordValidation.validate(email, password) == null)
			return false;

		Validate.notNull(commentId);

		Long id = Long.parseLong(commentId);

		commentRepository.delete(commentRepository.findById(id).orElseThrow());

		return commentRepository.findById(id).isEmpty();
	}

	@Override
	public Boolean addComment(String email, String password, String title, String description, String fkQuestionId, String fkThreadId) {
		Validate.notNull(title);
		Validate.notNull(description);
		Validate.notNull(email);

		Comment comment = new Comment();
		comment.setTitle(title);
		comment.setDescription(description);
		comment.setFkAccountId(accountService.getAccount(email));

		if (fkQuestionId != null) {
			comment.setFkQuestionId(questionService.getQuestion(email, password, fkQuestionId));
		} else if (fkThreadId != null) {
			comment.setFkThreadId(threadService.getThread(email, password, fkThreadId));
		} else {
			throw new IllegalArgumentException("Comment has to belong to a question/thread");
		}

		commentRepository.save(comment);

		return Boolean.TRUE.equals(true);
	}

	private Comment getComment(String email, String password, String commentId) {
		if (passwordValidation.validate(email, password) == null)
			return null;

		Validate.notNull(commentId);

		Long id = Long.parseLong(commentId);

		return commentRepository.findById(id).orElse(null);
	}
}
