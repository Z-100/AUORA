package com.auora.api.components.comment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CommentDTO {

	private Long id;

	private String title;

	private String description;

	private Long votes;
}
