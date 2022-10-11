package com.baydijital.softtech.app.acc.converter;

import com.baydijital.softtech.app.acc.dto.AccAccountDto;
import com.baydijital.softtech.app.acc.dto.AccAccountSaveRequestDto;
import com.baydijital.softtech.app.acc.entity.AccAccount;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Gokalp on 7/23/22
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccAccountMapper {
    AccAccountMapper INSTANCE = Mappers.getMapper(AccAccountMapper.class);

    AccAccountDto convertToAccAccountDto(AccAccount accAccount);

    List<AccAccountDto> convertToAccAccountDtoList(List<AccAccount> accAccountList);

    AccAccount convertToAccAccount(AccAccountSaveRequestDto accAccountSaveRequestDto);
}
