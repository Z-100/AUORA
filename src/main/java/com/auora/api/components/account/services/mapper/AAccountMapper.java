package com.auora.api.components.account.services.mapper;

import com.auora.api.components.account.dto.AccountDTO;
import com.auora.api.components.account.entity.Account;
import com.auora.api.components.thread.services.mapper.AThreadMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { AThreadMapper.class })
public abstract class AAccountMapper {

	abstract public AccountDTO toDTO(Account entity);

	@Mapping(target = "token", ignore = true)
	@Mapping(target = "password", ignore = true)
	abstract public Account toEntity(AccountDTO dto);
}
