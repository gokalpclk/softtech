package com.baydijital.softtech.app.acc.converter;

import com.baydijital.softtech.app.acc.dto.AccMoneyTransferDto;
import com.baydijital.softtech.app.acc.dto.AccMoneyTransferSaveRequestDto;
import com.baydijital.softtech.app.acc.entity.AccMoneyTransfer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Gokalp on 7/26/22
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccMoneyTransferMapper {
    AccMoneyTransferMapper INSTANCE = Mappers.getMapper(AccMoneyTransferMapper.class);

    AccMoneyTransfer convertToAccMoneyTransfer(AccMoneyTransferSaveRequestDto accMoneyTransferSaveRequestDto);

    AccMoneyTransferDto convertToAccMoneyTransferDto(AccMoneyTransfer accMoneyTransfer);
}
