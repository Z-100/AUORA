package com.auora.api.components.question.services.mapper;

import com.auora.api.components.comment.services.mapper.ACommentMapper;
import com.auora.api.components.question.dto.QuestionDTO;
import com.auora.api.components.question.entity.Question;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { ACommentMapper.class})
public abstract class AQuestionMapper {

	@InheritInverseConfiguration
	abstract public QuestionDTO toDTO(Question entity);

	@Mapping(target = "fkAccountId", ignore = true)
	abstract public Question toEntity(QuestionDTO dto);
}
