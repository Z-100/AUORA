package com.auora.api.components.thread.services.mapper;

import com.auora.api.components.thread.dto.ThreadDTO;
import com.auora.api.components.thread.entity.Thread;
import org.mapstruct.Mapping;

public abstract class AThreadMapper {

	abstract public ThreadDTO toDTO(Thread entity);

	@Mapping(target = "fkAccountId", ignore = true)
	abstract public Thread toEntity(ThreadDTO dto);
}
