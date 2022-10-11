package com.baydijital.softtech.app.log.converter;

import com.baydijital.softtech.app.kafka.dto.LogMessage;
import com.baydijital.softtech.app.log.entity.LogDetail;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author Gokalp on 9/20/22
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface LogMapper {
    LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    @Mapping( target = "details",source = "description")
    LogDetail convertToLogDetail(LogMessage logMessage);
}
