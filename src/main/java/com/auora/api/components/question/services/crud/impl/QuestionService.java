package com.auora.api.components.question.services.crud.impl;

import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.question.services.crud.IQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class QuestionService implements IQuestionService {

	@Override
	public QuestionDTO getQuestion(String questionId) {
		return null;
	}

	@Override
	public List<QuestionDTO> getAllFromAccount(String accountId) {
		return null;
	}

	@Override
	public Boolean addQuestion(String email, String password, String title, String description) {
		return null;
	}

	@Override
	public Boolean addUpVote(String email, String password, String questionId) {
		return null;
	}

	@Override
	public Boolean addDownVote(String email, String password, String questionId) {
		return null;
	}

	@Override
	public Boolean addComment(String email, String password, String questionId, String title, String description) {
		return null;
	}

	@Override
	public Boolean delete(String email, String password, String questionId) {
		return null;
	}
}
