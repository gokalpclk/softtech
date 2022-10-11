package com.baydijital.softtech.app.cus.converter;

import com.baydijital.softtech.app.cus.dto.CusCustomerDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerSaveRequestDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerUpdateRequestDto;
import com.baydijital.softtech.app.cus.entity.CusCustomer;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Gokalp on 7/19/22
 */
@Mapper(unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CusCustomerMapper {
    CusCustomerMapper INSTANCE = Mappers.getMapper(CusCustomerMapper.class);

    List<CusCustomerDto> convertToCusCustomerDtoList(List<CusCustomer> cusCustomerList);

    CusCustomer convertToCusCustomer(CusCustomerSaveRequestDto cusCustomerSaveRequestDto);

    CusCustomerDto convertToCusCustomerDto(CusCustomer cusCustomer);

    CusCustomer convertToCusCustomer(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto);


}
