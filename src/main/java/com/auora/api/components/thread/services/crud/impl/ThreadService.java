package com.auora.api.components.thread.services.crud.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.services.crud.impl.AccountService;
import com.auora.api.components.comment.services.crud.impl.CommentService;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.dto.ThreadDTO;
import com.auora.api.components.thread.entity.Thread;
import com.auora.api.components.thread.repository.IThreadRepository;
import com.auora.api.components.thread.services.crud.IThreadService;
import com.auora.api.components.thread.services.mapper.AThreadMapper;
import com.auora.api.other.Validate;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ThreadService implements IThreadService {

	private IThreadRepository threadRepository;

	private AThreadMapper threadMapper;
	private IPasswordValidationService passwordValidation;

	private AccountService accountService;
	private CommentService commentService;

	@Override
	public List<ThreadDTO> getAllFromAccount(String email) {
		Validate.notNull(email);

		Account account = accountService.getAccount(email);

		return threadRepository.findAllByFkAccountId(account).stream()
				.map(threadMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean addThread(String email, String password, String title, String description) {
		Validate.notNull(title);
		Validate.notNull(description);

		Account account = passwordValidation.validate(email, password);

		if (account == null)
			return false;

		Thread thread = new Thread();
		thread.setTitle(title);
		thread.setDescription(description);
		thread.setFkAccountId(account);

		threadRepository.save(thread);

		return Boolean.TRUE.equals(true);
	}

	@Override
	public Boolean addUpVote(String email, String password, String questionId) {
		Thread thread = getThread(email, password, questionId);

		assert thread != null;
		Long oldCount = thread.getVotes();

		Long id = thread.getId();

		thread.addUpvote();

		Thread updatedThread = threadRepository.findById(id).orElseThrow();

		return Objects.equals(++oldCount, updatedThread.getVotes());
	}

	@Override
	public Boolean addDownVote(String email, String password, String questionId) {
		Thread thread = getThread(email, password, questionId);

		assert thread != null;
		Long oldCount = thread.getVotes();

		Long id = thread.getId();

		thread.addDownVote();

		Thread updatedThread = threadRepository.findById(id).orElseThrow();

		return Objects.equals(--oldCount, updatedThread.getVotes());
	}

	@Override
	public Boolean addComment(String email, String password, String fkThreadId, String title, String description) {
		Validate.notNull(email);
		Validate.notNull(password);

		Thread commentToThread = getThread(email, password, fkThreadId);

		return passwordValidation.validate(email, password) != null ?
				commentService.addComment(email, password, title, description, null, commentToThread) : false;
	}

	@Override
	public Boolean delete(String email, String password, String questionId) {
		if (passwordValidation.validate(email, password) == null)
			return false;

		Validate.notNull(questionId);

		Long id = Long.parseLong(questionId);

		threadRepository.delete(threadRepository.findById(id).orElseThrow());

		return threadRepository.findById(id).isEmpty();
	}

	public Thread getThread(String email, String password, String threadId) {
		if (passwordValidation.validate(email, password) == null)
			return null;

		Validate.notNull(threadId);

		Long id = Long.parseLong(threadId);

		return threadRepository.findById(id).orElse(null);
	}
}
