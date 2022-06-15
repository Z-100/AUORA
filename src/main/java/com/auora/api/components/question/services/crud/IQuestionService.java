package com.auora.api.components.question.services.crud;

import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.question.entity.Question;

import java.util.List;

public interface IQuestionService {

	List<QuestionDTO> getAllFromAccount(String accountId);

	Boolean addQuestion(String email, String password, String title, String description);

	Boolean addUpVote(String email, String password, String questionId);

	Boolean addDownVote(String email, String password, String questionId);

	Boolean addComment(String email, String password, String title, String description, String questionId);

	Boolean delete(String email, String password, String questionId);
}
