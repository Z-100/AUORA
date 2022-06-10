package com.auora.api.components.question.services.crud;

import com.auora.api.components.question.dto.QuestionDTO;

import java.util.List;

public interface IQuestionService {

	QuestionDTO getQuestion(String questionId);

	List<QuestionDTO> getAllFromAccount(String accountId);

	Boolean addQuestion(String email, String password, String title, String description);

	Boolean addUpVote(String email, String password, String questionId);

	Boolean addDownVote(String email, String password, String questionId);

	Boolean addComment(String email, String password, String questionId, String title, String description);

	Boolean delete(String email, String password, String questionId);
}
