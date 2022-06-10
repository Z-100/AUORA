package com.auora.api.components.account.dto;

import com.auora.api.components.comment.entity.Comment;
import com.auora.api.components.question.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AccountDTO {

	private Long id;

	private String email;

	private List<Question> recipes;

	private List<Thread> threads;

	private List<Comment> comments;
}
