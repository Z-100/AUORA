package com.auora.api.components.comment.controller;

import com.auora.api.components.comment.dto.CommentDTO;
import com.auora.api.components.comment.services.crud.ICommentService;
import com.auora.api.other.Constants;
import com.auora.api.other.JsonString;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_COMMENT)
@AllArgsConstructor
public class CommentController {

	private final ICommentService commentService;

	@GetMapping(Constants.URL_GET + Constants.URL_ALL + "/from-account/{email}")
	public ResponseEntity<?> getAllFromAccount(
			@PathVariable("email") String email) {

		List<CommentDTO> commentDTOs = commentService.getAllFromAccount(email);

		return commentDTOs == null ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(commentDTOs, HttpStatus.OK);
	}

	@GetMapping(Constants.URL_GET + Constants.URL_ALL + "/from-question/{question-id}")
	public ResponseEntity<?> getAllFromQuestion(
			@PathVariable("question-id") String questionId) {

		List<CommentDTO> commentDTOs = commentService.getAllFromQuestion(questionId);

		return commentDTOs == null ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(commentDTOs, HttpStatus.OK);
	}

	@GetMapping(Constants.URL_GET + Constants.URL_ALL + "/from-thread/{thread-id}")
	public ResponseEntity<?> getAllFromThread(
			@PathVariable("thread-id") String threadId) {

		List<CommentDTO> commentDTOs = commentService.getAllFromThread(threadId);

		return commentDTOs == null ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(commentDTOs, HttpStatus.OK);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_UPVOTE)
	public ResponseEntity<?> addUpvote(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("comment-id") String commentId) {

		return commentService.addUpVote(email, password, commentId) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_DOWNVOTE)
	public ResponseEntity<?> addDownVote(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("comment-id") String commentId) {

		return commentService.addDownVote(email, password, commentId) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_DELETE)
	public ResponseEntity<?> deleteComment(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("comment-id") String commentId) {

		return commentService.addDownVote(email, password, commentId) ?
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR) :
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK);
	}
}

