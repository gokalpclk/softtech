package com.baydijital.softtech.app.crd.converter;

import com.baydijital.softtech.app.crd.dto.CrdCreditCardResponseDto;
import com.baydijital.softtech.app.crd.entity.CrdCreditCard;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Gokalp on 8/4/22
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CrdCreditCardMapper {
    CrdCreditCardMapper INSTANCE = Mappers.getMapper(CrdCreditCardMapper.class);

    CrdCreditCardResponseDto convertToCrdCreditCardResponseDto(CrdCreditCard crdCreditCard);

    List<CrdCreditCardResponseDto> convertToCrdCreditCardResponseDtoList(List<CrdCreditCard> crdCreditCardList);
}
