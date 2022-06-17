package com.auora.api.components.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentDTO {

	private Long id;

	private String title;

	private String description;

	private Long votes;
}
