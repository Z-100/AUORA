package com.auora.api.components.account.dto;

import com.auora.api.components.comment.dto.CommentDTO;
import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.thread.dto.ThreadDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AccountDTO {

	private Long id;

	private String email;

	private List<QuestionDTO> questions;

	private List<ThreadDTO> threads;

	private List<CommentDTO> comments;
}
