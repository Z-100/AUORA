package com.auora.api.components.comment.services.mapper;

import com.auora.api.components.comment.dto.CommentDTO;
import com.auora.api.components.comment.entity.Comment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ACommentMapper {

	abstract public CommentDTO toDTO(Comment entity);

	abstract public Comment toEntity(CommentDTO dto);
}
