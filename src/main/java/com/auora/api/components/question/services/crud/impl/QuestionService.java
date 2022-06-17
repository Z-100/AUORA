package com.auora.api.components.question.services.crud.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.services.crud.impl.AccountService;
import com.auora.api.components.comment.services.crud.impl.CommentService;
import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.question.repository.IQuestionRepository;
import com.auora.api.components.question.services.crud.IQuestionService;
import com.auora.api.components.question.services.mapper.AQuestionMapper;
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
public class QuestionService implements IQuestionService {

	private final IQuestionRepository questionRepository;

	private final AQuestionMapper questionMapper;
	private final IPasswordValidationService passwordValidation;

	private final AccountService accountService;
	private final CommentService commentService;

	@Override
	public List<QuestionDTO> getAllFromAccount(String email) {
		Validate.notNull(email);

		Account account = accountService.getAccount(email);

		Validate.notNull(account, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS }, account);

		List<Question> questionsFromAccount = questionRepository.findAllByfkAccountId(account);

		Validate.notEmpty(questionsFromAccount, Constants.NOT_EXISTS);

		 return questionsFromAccount.stream()
				 .map(questionMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean addQuestion(String email, String password, String title, String description) {
		Validate.notNull(title);
		Validate.notNull(description);

		Account account = passwordValidation.validate(email, password);

		Validate.notNull(account, Constants.INVALID_PASSWORD);

		Question question = new Question();
		question.setTitle(title);
		question.setDescription(description);
		question.setFkAccountId(account);

		questionRepository.save(question);

		return Boolean.TRUE.equals(true);
	}

	@Override
	public Boolean addUpVote(String email, String password, String questionId) {

		Question question = getQuestion(email, password, questionId);
		Validate.notNull(question, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = question.getVotes();
		Long id = question.getId();

		try { // In case value is null
			question.addUpvote();
		} catch (NullPointerException e) {
			question.setVotes(1L);
		}

		questionRepository.save(question);

		Question updatedQuestion = questionRepository.findById(id).orElseThrow();
		Validate.notNull(updatedQuestion, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(++oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addDownVote(String email, String password, String questionId) {

		Question question = getQuestion(email, password, questionId);
		Validate.notNull(question, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = question.getVotes();
		Long id = question.getId();

		try { // In case value is null
			question.addDownVote();
		} catch (NullPointerException e) {
			question.setVotes(1L);
		}

		questionRepository.save(question);

		Question updatedQuestion = questionRepository.findById(id).orElseThrow();
		Validate.notNull(updatedQuestion, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(--oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addComment(String email, String password, String title, String description, String fkQuestionId) {
		Question commentToQuestion = getQuestion(email, password, fkQuestionId);

		return commentService.addComment(email, password, title, description, commentToQuestion, null);
	}

	@Override
	public Boolean delete(String email, String password, String questionId) {
		Validate.notNull(passwordValidation.validate(email, password), Constants.INVALID_PASSWORD);
		Validate.notNull(questionId);

		Long id = Long.parseLong(questionId);

		// ? GTK Deleting an entity, will delete all fk to that entity
		questionRepository.delete(questionRepository.findById(id).orElseThrow());

		return questionRepository.findById(id).isEmpty();
	}

	public Question getQuestion(String email, String password, String questionId) {

		Validate.notNull(passwordValidation.validate(email, password), Constants.INVALID_PASSWORD);
		Validate.notNull(questionId);

		Long id = Long.parseLong(questionId);

		return questionRepository.findById(id).orElse(null);
	}
}

