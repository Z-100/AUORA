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
import com.auora.api.service.Validator;
import com.auora.api.service.IAccountValidationService;
import com.auora.api.service.EntityFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuestionService implements IQuestionService {

	private final IQuestionRepository questionRepository;

	private final AQuestionMapper questionMapper;
	private final IAccountValidationService passwordValidation;

	private final AccountService accountService;
	private final CommentService commentService;

	@Override
	public List<QuestionDTO> getAllFromAccount(String email) {
		Validator.notNull(email);

		Account account = accountService.getAccount(email);

		Validator.notNull(account, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS }, account);

		List<Question> questionsFromAccount = questionRepository.findAllByfkAccountId(account);

		Validator.notEmpty(questionsFromAccount, Constants.NOT_EXISTS);

		 return questionsFromAccount.stream()
				 .map(questionMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean addQuestion(String email, String password, String title, String description) {
		Validator.notNull(title.length() > 0 ? title : null, Constants.TITLE_NOT_NULL);
		Validator.notNull(description.length() > 0 ? description : null, Constants.TITLE_NOT_NULL);

		Account account = passwordValidation.validateLogin(email, password);

		Validator.notNull(account, Constants.INVALID_PASSWORD);

		Question question = EntityFactory.getInstance(Question.class);
		question.setTitle(title);
		question.setDescription(description);
		question.setFkAccountId(account);

		questionRepository.save(question);

		return Boolean.TRUE.equals(true);
	}

	@Override
	public Boolean addUpVote(String email, String password, String questionId) {

		Question question = getQuestion(email, password, questionId);
		Validator.notNull(question, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = question.getVotes();
		Long id = question.getId();

		try { // In case value is null
			question.addUpvote();
		} catch (NullPointerException e) {
			question.setVotes(1L);
		}

		questionRepository.save(question);

		Question updatedQuestion = questionRepository.findById(id).orElseThrow();
		Validator.notNull(updatedQuestion, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(++oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addDownVote(String email, String password, String questionId) {

		Question question = getQuestion(email, password, questionId);
		Validator.notNull(question, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		Long oldCount = question.getVotes();
		Long id = question.getId();

		try { // In case value is null
			question.addDownVote();
		} catch (NullPointerException e) {
			question.setVotes(1L);
		}

		questionRepository.save(question);

		Question updatedQuestion = questionRepository.findById(id).orElseThrow();
		Validator.notNull(updatedQuestion, new String[]{ Constants.SOMETHING_WRONG, Constants.NOT_EXISTS });

		return Objects.equals(--oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addComment(String email, String password, String title, String description, String fkQuestionId) {
		Question commentToQuestion = getQuestion(email, password, fkQuestionId);

		return commentService.addComment(email, password, title, description, commentToQuestion, null);
	}

	@Override
	public Boolean delete(String email, String password, String questionId) {
		Validator.notNull(questionId);
		Long id = Long.parseLong(questionId);

		Account validate = passwordValidation.validateLogin(email, password);
		Validator.notNull(validate, Constants.INVALID_PASSWORD);

		Optional<Question> byId = questionRepository.findById(id);

		if (byId.isEmpty()) {
			throw new IllegalArgumentException(Constants.NOT_EXISTS);
		}

		Validator.equals(validate.getId(), byId.get().getFkAccountId().getId(), Constants.INVALID_PASSWORD);

		// ? GTK Deleting an entity, will delete all fk to that entity
		questionRepository.deleteById(id);

		return questionRepository.findById(id).isEmpty();
	}

	public Question getQuestion(String email, String password, String questionId) {

		Validator.notNull(passwordValidation.validateLogin(email, password), Constants.INVALID_PASSWORD);
		Validator.notNull(questionId);

		Long id = Long.parseLong(questionId);

		return questionRepository.findById(id).orElse(null);
	}
}

