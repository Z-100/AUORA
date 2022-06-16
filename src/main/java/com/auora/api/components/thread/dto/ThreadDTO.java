package com.auora.api.components.thread.dto;

import com.auora.api.components.comment.dto.CommentDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ThreadDTO {

	private Long id;

	private String title;

	private String description;

	private Long votes;

	private List<CommentDTO> comments;
}
