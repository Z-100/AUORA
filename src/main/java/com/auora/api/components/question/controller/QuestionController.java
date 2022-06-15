package com.auora.api.components.question.controller;

import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.question.services.crud.IQuestionService;
import com.auora.api.other.Constants;
import com.auora.api.other.JsonString;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_QUESTION)
@AllArgsConstructor
public class QuestionController {

	private final IQuestionService questionService;

	@GetMapping(Constants.URL_GET + Constants.URL_ALL + "/{email}")
	public ResponseEntity<?> getAllQuestionsFromAccount(
			@PathVariable("email") String email) {

		List<QuestionDTO> questionDTOs = questionService.getAllFromAccount(email);

		return questionDTOs == null ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(questionDTOs, HttpStatus.OK);
	}

	@PostMapping(Constants.URL_ADD)
	public ResponseEntity<?> addQuestion(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("title") String title,
			@RequestHeader("description") String description) {

		return questionService.addQuestion(email, password, title, description) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_UPVOTE)
	public ResponseEntity<?> addUpvote(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("question-id") String questionId) {

		return questionService.addUpVote(email, password, questionId) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_DOWNVOTE)
	public ResponseEntity<?> addDownVote(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("question-id") String questionId) {

		return questionService.addDownVote(email, password, questionId) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_ADD_COMMENT)
	public ResponseEntity<?> addComment(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("question-id") String questionId,
			@RequestHeader("title") String title,
			@RequestHeader("description") String description) {

		return questionService.addComment(email, password, questionId, title, description) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_DELETE)
	public ResponseEntity<?> deleteQuestion(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("question-id") String questionId) {

		return questionService.addDownVote(email, password, questionId) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}
}

