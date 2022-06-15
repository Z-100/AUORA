package com.auora.api.components.question.services.crud.impl;

import com.auora.api.components.account.entity.Account;
import com.auora.api.components.account.services.crud.impl.AccountService;
import com.auora.api.components.comment.entity.Comment;
import com.auora.api.components.comment.services.crud.impl.CommentService;
import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.question.repository.IQuestionRepository;
import com.auora.api.components.question.services.crud.IQuestionService;
import com.auora.api.components.question.services.mapper.AQuestionMapper;
import com.auora.api.other.Validate;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class QuestionService implements IQuestionService {

	private IQuestionRepository questionRepository;

	private AQuestionMapper questionMapper;
	private IPasswordValidationService passwordValidation;

	private AccountService accountService;
	private CommentService commentService;

	@Override
	public List<QuestionDTO> getAllFromAccount(String email) {
		Validate.notNull(email);

		Account account = accountService.getAccount(email);

		return questionRepository.findAllByfkAccountId(account).stream()
				.map(questionMapper::toDTO)
				.collect(Collectors.toList());
	}

	@Override
	public Boolean addQuestion(String email, String password, String title, String description) {
		Validate.notNull(title);
		Validate.notNull(description);

		Account account = passwordValidation.validate(email, password);

		if (account == null)
			return false;

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

		assert question != null;
		Long oldCount = question.getVotes();

		Long id = question.getId();

		question.addUpvote();

		Question updatedQuestion = questionRepository.findById(id).orElseThrow();

		return Objects.equals(++oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addDownVote(String email, String password, String questionId) {
		Question question = getQuestion(email, password, questionId);

		assert question != null;
		Long oldCount = question.getVotes();

		Long id = question.getId();

		question.addDownVote();

		Question updatedQuestion = questionRepository.findById(id).orElseThrow();

		return Objects.equals(--oldCount, updatedQuestion.getVotes());
	}

	@Override
	public Boolean addComment(String email, String password, String title, String description, String questionId) {
		Validate.notNull(email);
		Validate.notNull(password);

		return passwordValidation.validate(email, password) != null ?
				commentService.addComment(email, password, description, questionId, null) : false;
	}

	@Override
	public Boolean delete(String email, String password, String questionId) {

		if (passwordValidation.validate(email, password) == null)
			return false;

		Validate.notNull(questionId);

		Long id = Long.parseLong(questionId);

		questionRepository.delete(questionRepository.findById(id).orElseThrow());

		return questionRepository.findById(id).isEmpty();
	}

	private Question getQuestion(String email, String password, String questionId) {
		if (passwordValidation.validate(email, password) == null)
			return null;

		Validate.notNull(questionId);

		Long id = Long.parseLong(questionId);

		return questionRepository.findById(id).orElse(null);
	}
}
