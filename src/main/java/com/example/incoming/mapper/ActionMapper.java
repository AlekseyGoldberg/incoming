package com.example.incoming.mapper;

import com.example.incoming.dto.request.ActionReqDto;
import com.example.incoming.dto.response.ActionDetailRespDto;
import com.example.incoming.dto.response.ActionRespDto;
import com.example.incoming.entity.Account;
import com.example.incoming.entity.Action;
import com.example.incoming.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.springframework.stereotype.Component;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS, componentModel = "spring")
@Component
public interface ActionMapper {
    @Mapping(source = "action.id", target = "id")
    @Mapping(source = "action.dataTransaction", target = "date")
    @Mapping(source = "action.account.nameAccount", target = "account")
    @Mapping(source = "action.amount", target = "sum")
    @Mapping(source = "action.typeOperation", target = "typeOperation")
    ActionRespDto toDto(Action action);


    @Mapping(source = "action.id", target = "id")
    @Mapping(source = "action.dataTransaction", target = "date")
    @Mapping(source = "action.account.nameAccount", target = "account")
    @Mapping(source = "action.amount", target = "sum")
    @Mapping(source = "action.comment", target = "comment")
    @Mapping(source = "action.typeOperation", target = "typeOperation")
    ActionDetailRespDto toDetailDto(Action action);

//    @Mapping(source = "amount", target = "amount")
//    @Mapping(source = "comment", target = "comment")
//    @Mapping(source = "date", target = "dataTransaction")
//    @Mapping(source = "typeAction", target = "typeOperation")
//    @Mapping(source = "account", target = "account")
//    @Mapping(source = "category", target = "category")
//    Action fromDto(ActionReqDto dto, Account account, Category category);
}
