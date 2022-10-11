package com.baydijital.softtech.app.acc.converter;

import com.baydijital.softtech.app.acc.dto.AccAccountActivityDto;
import com.baydijital.softtech.app.acc.entity.AccAccountActivity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Gokalp on 7/28/22
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccAccountActivityMapper {
    AccAccountActivityMapper INSTANCE = Mappers.getMapper(AccAccountActivityMapper.class);

    AccAccountActivityDto convertToAccAccountActivityDto(AccAccountActivity accAccountActivity);
}
