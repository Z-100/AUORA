package com.auora.api.components.thread.controller;

import com.auora.api.components.thread.dto.ThreadDTO;
import com.auora.api.components.thread.services.crud.IThreadService;
import com.auora.api.other.Constants;
import com.auora.api.other.JsonString;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constants.URL_THREAD)
@AllArgsConstructor
public class ThreadController {

	private final IThreadService threadService;

	@GetMapping(Constants.URL_GET + Constants.URL_ALL + "/from-account")
	public ResponseEntity<?> getAllThreadsFromAccount(
			@RequestParam("email") String email) {

		List<ThreadDTO> threadDTOs = threadService.getAllFromAccount(email);

		return threadDTOs != null ?
				new ResponseEntity<>(threadDTOs, HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_ADD)
	public ResponseEntity<?> addThread(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("title") String title,
			@RequestHeader("description") String description) {

		return threadService.addThread(email, password, title, description) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_UPVOTE)
	public ResponseEntity<?> addUpvote(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("thread-id") String threadId) {

		return threadService.addUpVote(email, password, threadId) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_DOWNVOTE)
	public ResponseEntity<?> addDownVote(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("thread-id") String threadId) {

		return threadService.addDownVote(email, password, threadId) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_ADD + Constants.URL_ADD_COMMENT)
	public ResponseEntity<?> addComment(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("thread-id") String threadId,
			@RequestHeader("title") String title,
			@RequestHeader("description") String description) {

		return threadService.addComment(email, password, threadId, title, description) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(Constants.URL_DELETE)
	public ResponseEntity<?> deleteThread(
			@RequestHeader("email") String email,
			@RequestHeader("password") String password,
			@RequestHeader("thread-id") String threadId) {

		return threadService.delete(email, password, threadId) ?
				new ResponseEntity<>(new JsonString(Constants.SUCCESS), HttpStatus.OK) :
				new ResponseEntity<>(Constants.SOMETHING_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
