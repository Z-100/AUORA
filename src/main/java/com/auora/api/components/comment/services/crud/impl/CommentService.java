package com.auora.api.components.comment.services.crud.impl;

import com.auora.api.components.comment.dto.CommentDTO;
import com.auora.api.components.comment.services.crud.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CommentService implements ICommentService {

	@Override
	public List<CommentDTO> getAllFromAccount(String email) {
		return null;
	}

	@Override
	public List<CommentDTO> getAllFromQuestion(String questionId) {
		return null;
	}

	@Override
	public List<CommentDTO> getAllFromThread(String threadId) {
		return null;
	}

	@Override
	public Boolean addUpVote(String email, String password, String commentId) {
		return null;
	}

	@Override
	public Boolean addDownVote(String email, String password, String commentId) {
		return null;
	}

	@Override
	public Boolean delete(String email, String password, String commentId) {
		return null;
	}
}
