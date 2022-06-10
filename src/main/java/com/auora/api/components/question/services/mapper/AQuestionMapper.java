package com.auora.api.components.question.services.mapper;

import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.question.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class AQuestionMapper {

	abstract public QuestionDTO toDTO(Question entity);

	@Mapping(target = "token", ignore = true)
	@Mapping(target = "password", ignore = true)
	abstract public Question toEntity(QuestionDTO dto);
}
