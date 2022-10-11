package com.baydijital.softtech.app.crd.converter;

import com.baydijital.softtech.app.crd.dto.CrdCreditCardActivityDto;
import com.baydijital.softtech.app.crd.entity.CrdCreditCardActivity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.List;

/**
 * @author Gokalp on 8/8/22
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CrdCreditCardActivityMapper {
    CrdCreditCardActivityMapper INSTANCE = Mappers.getMapper(CrdCreditCardActivityMapper.class);

    CrdCreditCardActivityDto convertToCrdCreditCardActivityDto(CrdCreditCardActivity crdCreditCardActivity);

    List<CrdCreditCardActivityDto> convertToCrdCreditCardActivityDtoList(List<CrdCreditCardActivity> crdCreditCardActivityList);
}
