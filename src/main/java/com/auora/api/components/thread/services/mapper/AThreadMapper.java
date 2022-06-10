package com.auora.api.components.thread.services.mapper;

import com.auora.api.components.comment.services.mapper.ACommentMapper;
import com.auora.api.components.thread.dto.ThreadDTO;
import com.auora.api.components.thread.entity.Thread;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ACommentMapper.class})
public abstract class AThreadMapper {

	@InheritInverseConfiguration
	abstract public ThreadDTO toDTO(Thread entity);

	@Mapping(target = "fkAccountId", ignore = true)
	abstract public Thread toEntity(ThreadDTO dto);
}
