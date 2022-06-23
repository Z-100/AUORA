package com.auora.api.components.thread.services.crud.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.services.crud.impl.AccountService;
import com.auora.api.components.comment.services.crud.impl.CommentService;
import com.auora.api.components.thread.dto.ThreadDTO;
import com.auora.api.components.thread.entity.Thread;
import com.auora.api.components.thread.repository.IThreadRepository;
import com.auora.api.components.thread.services.crud.IThreadService;
import com.auora.api.components.thread.services.mapper.AThreadMapper;
import com.auora.api.other.Constants;
import com.auora.api.other.Validator;
import com.auora.api.service.IPasswordValidationService;
import com.auora.api.service.impl.EntityFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ThreadService implements IThreadService {

	private final IThreadRepository threadRepository;

	private final AThreadMapper threadMapper;
	private final IPasswordValidationService passwordValidation;

	private final AccountService accountService;
	private final CommentService commentService;

	@Override
	public List<ThreadDTO> getAllFromAccount(String email) {
		Validator.notNull(email);

		Account account = accountService.getAccount(email);

		Validator.notNull(account, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS }, account);

		List<Thread> threadsFromAccount = threadRepository.findAllByFkAccountId(account);

		Validator.notEmpty(threadsFromAccount, Constants.NOT_EXISTS);

		return threadsFromAccount.stream()
				.map(threadMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean addThread(String email, String password, String title, String description) {
		Validator.notNull(title.length() > 0 ? title : null, Constants.TITLE_NOT_NULL);
		Validator.notNull(description.length() > 0 ? description : null, Constants.TITLE_NOT_NULL);

		Account account = passwordValidation.validate(email, password);

		Validator.notNull(account, Constants.INVALID_PASSWORD);

		Thread thread = EntityFactory.getInstance(Thread.class);
		thread.setTitle(title);
		thread.setDescription(description);
		thread.setFkAccountId(account);

		threadRepository.save(thread);

		return Boolean.TRUE.equals(true);
	}

	@Override
	public Boolean addUpVote(String email, String password, String threadId) {

		Thread thread = getThread(email, password, threadId);
		Validator.notNull(thread, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = thread.getVotes();
		Long id = thread.getId();

		try { // In case value is null
			thread.addUpvote();
		} catch (NullPointerException e) {
			thread.setVotes(1L);
		}

		threadRepository.save(thread);

		Thread updatedQuestion = threadRepository.findById(id).orElseThrow();
		Validator.notNull(updatedQuestion, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(++oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addDownVote(String email, String password, String questionId) {

		Thread thread = getThread(email, password, questionId);
		Validator.notNull(thread, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = thread.getVotes();
		Long id = thread.getId();

		try { // In case value is null
			thread.addDownVote();
		} catch (NullPointerException e) {
			thread.setVotes(1L);
		}

		threadRepository.save(thread);

		Thread updatedQuestion = threadRepository.findById(id).orElseThrow();
		Validator.notNull(updatedQuestion, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(--oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addComment(String email, String password, String fkThreadId, String title, String description) {
		Thread commentToThread = getThread(email, password, fkThreadId);

		return commentService.addComment(email, password, title, description, null, commentToThread);
	}

	@Override
	public Boolean delete(String email, String password, String threadId) {
		Validator.notNull(threadId);
		Long id = Long.parseLong(threadId);

		Account validate = passwordValidation.validate(email, password);
		Validator.notNull(validate, Constants.INVALID_PASSWORD);

		Optional<Thread> byId = threadRepository.findById(id);

		if (byId.isEmpty()) {
			throw new IllegalArgumentException(Constants.NOT_EXISTS);
		}

		Validator.equals(validate.getId(), byId.get().getFkAccountId().getId(), Constants.INVALID_PASSWORD);

		threadRepository.deleteById(id);

		return threadRepository.findById(id).isEmpty();
	}

	public Thread getThread(String email, String password, String threadId) {

		Validator.notNull(passwordValidation.validate(email, password), Constants.INVALID_PASSWORD);
		Validator.notNull(threadId);

		Long id = Long.parseLong(threadId);

		return threadRepository.findById(id).orElse(null);
	}
}
