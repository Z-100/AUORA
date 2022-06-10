package com.auora.api.components.thread.services.crud.impl;

import com.auora.api.components.thread.dto.ThreadDTO;
import com.auora.api.components.thread.services.crud.IThreadService;
import com.auora.api.service.IPasswordValidationService;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ThreadService implements IThreadService {

	private IPasswordValidationService pwValidation;

	@Override
	public ThreadDTO getThread(String threadId) {
		return null;
	}

	@Override
	public List<ThreadDTO> getAllFromAccount(String email) {
		return null;
	}

	@Override
	public Boolean addThread(String email, String password, String title, String description) {
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
