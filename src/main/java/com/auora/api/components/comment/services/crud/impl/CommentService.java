package com.auora.api.components.comment.services.crud.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.services.crud.impl.AccountService;
import com.auora.api.components.comment.dto.CommentDTO;
import com.auora.api.components.comment.entity.Comment;
import com.auora.api.components.comment.repository.ICommentRepository;
import com.auora.api.components.comment.services.crud.ICommentService;
import com.auora.api.components.comment.services.mapper.ACommentMapper;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.entity.Thread;
import com.auora.api.other.Constants;
import com.auora.api.other.Validator;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CommentService implements ICommentService {

	private final ICommentRepository commentRepository;

	private final ACommentMapper commentMapper;
	private final IPasswordValidationService passwordValidation;

	private final AccountService accountService;

	@Override
	public List<CommentDTO> getAllFromAccount(String email) {
		Validator.notNull(email);

		Account account = accountService.getAccount(email);

		Validator.notNull(account, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS }, account);

		List<Comment> commentsFromAccount = commentRepository.findAllByFkAccountId(account);

		Validator.notEmpty(commentsFromAccount, Constants.NOT_EXISTS);

		return commentsFromAccount.stream()
				.map(commentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CommentDTO> getAllFromQuestion(String questionId) {
		Validator.notNull(questionId);

		List<Comment> commentsFromQuestion = commentRepository.findAllByFkQuestionIdId(Long.valueOf(questionId));

		Validator.notEmpty(commentsFromQuestion, Constants.NOT_EXISTS);

		return commentsFromQuestion.stream()
				.map(commentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public List<CommentDTO> getAllFromThread(String threadId) {
		Validator.notNull(threadId);

		List<Comment> commentsFromThread = commentRepository.findAllByFkThreadIdId(Long.valueOf(threadId));

		Validator.notEmpty(commentsFromThread, Constants.NOT_EXISTS);

		return commentsFromThread.stream()
				.map(commentMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean addUpVote(String email, String password, String commentId) {

		Comment comment = getComment(email, password, commentId);
		Validator.notNull(comment, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = comment.getVotes();
		Long id = comment.getId();

		try { // In case value is null
			comment.addUpvote();
		} catch (NullPointerException e) {
			comment.setVotes(1L);
		}

		commentRepository.save(comment);

		Comment updatedComment = commentRepository.findById(id).orElseThrow();
		Validator.notNull(updatedComment, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(++oldCount, updatedComment.getVotes());
	}

	@Override
	public Boolean addDownVote(String email, String password, String commentId) {

		Comment comment = getComment(email, password, commentId);
		Validator.notNull(comment, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = comment.getVotes();
		Long id = comment.getId();

		try { // In case value is null
			comment.addDownVote();
		} catch (NullPointerException e) {
			comment.setVotes(1L);
		}

		commentRepository.save(comment);

		Comment updatedComment = commentRepository.findById(id).orElseThrow();
		Validator.notNull(updatedComment, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(--oldCount, updatedComment.getVotes());
	}

	@Override
	public Boolean delete(String email, String password, String commentId) {
		Validator.notNull(passwordValidation.validate(email, password), Constants.INVALID_PASSWORD);
		Validator.notNull(commentId);

		Long id = Long.parseLong(commentId);

		// ? GTK Deleting an entity, will delete all fk to that entity
		commentRepository.deleteById(id);

		return commentRepository.findById(id).isEmpty();
	}

	@Override
	public Boolean addComment(String email, String password, String title, String description, Question fkQuestionId, Thread fkThreadId) {
		Validator.notNull(title.length() > 0 ? title : null, Constants.TITLE_NOT_NULL);
		Validator.notNull(description.length() > 0 ? description : null, Constants.TITLE_NOT_NULL);

		Comment comment = new Comment();
		comment.setTitle(title);
		comment.setDescription(description);
		comment.setFkAccountId(accountService.getAccount(email));

		if (fkQuestionId != null) {
			comment.setFkQuestionId(fkQuestionId);
		} else if (fkThreadId != null) {
			comment.setFkThreadId(fkThreadId);
		} else {
			throw new IllegalArgumentException("Comment has to belong to a question/thread");
		}

		commentRepository.save(comment);

		return Boolean.TRUE.equals(true);
	}

	private Comment getComment(String email, String password, String commentId) {
		Validator.notNull(passwordValidation.validate(email, password), Constants.INVALID_PASSWORD);
		Validator.notNull(commentId);

		Long id = Long.parseLong(commentId);

		return commentRepository.findById(id).orElse(null);
	}
}
