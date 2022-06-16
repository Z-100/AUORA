package com.auora.api.components.question.dto;

import com.auora.api.components.comment.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class QuestionDTO {

	private Long id;

	private String title;

	private String description;

	private Long votes;

	private List<CommentDTO> comments;
}
