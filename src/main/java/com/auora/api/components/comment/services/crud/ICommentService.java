package com.auora.api.components.comment.services.crud;

import com.auora.api.components.comment.dto.CommentDTO;

import java.util.List;

public interface ICommentService {

	List<CommentDTO> getAllFromAccount(String email);

	List<CommentDTO> getAllFromQuestion(String questionId);

	List<CommentDTO> getAllFromThread(String threadId);

	Boolean addUpVote(String email, String password, String commentId);

	Boolean addDownVote(String email, String password, String commentId);

	Boolean delete(String email, String password, String commentId);
}
