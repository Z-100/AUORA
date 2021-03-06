package com.auora.api.components.account.services.mapper;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.entity.Account;
import com.auora.api.components.comment.services.mapper.ACommentMapper;
import com.auora.api.components.question.services.mapper.AQuestionMapper;
import com.auora.api.components.thread.services.mapper.AThreadMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AThreadMapper.class, AQuestionMapper.class, ACommentMapper.class })
public abstract class AAccountMapper {

	abstract public AccountDTO toDTO(Account entity);

	@Mapping(target = "validationSentence", ignore = true)
	@Mapping(target = "password", ignore = true)
	abstract public Account toEntity(AccountDTO dto);
}
