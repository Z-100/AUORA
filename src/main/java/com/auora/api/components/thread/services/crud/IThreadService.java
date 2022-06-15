package com.auora.api.components.thread.services.crud;

import com.auora.api.components.thread.dto.ThreadDTO;
import com.auora.api.components.thread.entity.Thread;

import java.util.List;

public interface IThreadService {

	List<ThreadDTO> getAllFromAccount(String accountId);

	Boolean addThread(String email, String password, String title, String description);

	Boolean addUpVote(String email, String password, String questionId);

	Boolean addDownVote(String email, String password, String questionId);

	Boolean addComment(String email, String password, String questionId, String title, String description);

	Boolean delete(String email, String password, String questionId);
}
