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
import com.auora.api.other.Validate;
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
	public Boolean addComment(String email, String password, String title, String description, Question fkQuestionId, Thread fkThreadId) {
		Validate.notNull(title.length() > 0 ? title : null, Constants.TITLE_NOT_NULL);
		Validate.notNull(description.length() > 0 ? description : null, Constants.TITLE_NOT_NULL);

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
		if (passwordValidation.validate(email, password) == null)
			return null;

		Validate.notNull(commentId);

		Long id = Long.parseLong(commentId);

		return commentRepository.findById(id).orElse(null);
	}
}
