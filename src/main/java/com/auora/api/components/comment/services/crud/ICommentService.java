package com.auora.api.components.comment.services.crud;

import com.auora.api.components.comment.dto.CommentDTO;
import com.auora.api.components.question.entity.Question;
import com.auora.api.components.thread.entity.Thread;

import java.util.List;

public interface ICommentService {

	List<CommentDTO> getAllFromAccount(String email);

	List<CommentDTO> getAllFromQuestion(String questionId);

	List<CommentDTO> getAllFromThread(String threadId);

	Boolean addUpVote(String email, String password, String commentId);

	Boolean addDownVote(String email, String password, String commentId);

	Boolean delete(String email, String password, String commentId);

	Boolean addComment(String email, String password, String title, String description, Question fkQuestionId, Thread fkThreadId);
}
