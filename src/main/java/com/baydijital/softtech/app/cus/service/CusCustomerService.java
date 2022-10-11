package com.baydijital.softtech.app.cus.service;

import com.baydijital.softtech.app.cus.converter.CusCustomerMapper;
import com.baydijital.softtech.app.cus.dto.CusCustomerDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerSaveRequestDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerUpdateRequestDto;
import com.baydijital.softtech.app.cus.entity.CusCustomer;
import com.baydijital.softtech.app.cus.enums.CusErrorMessage;
import com.baydijital.softtech.app.cus.service.entityservice.CusCustomerEntityService;
import com.baydijital.softtech.app.gen.exceptions.ItemNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Gokalp on 7/19/22
 */
@Service
@RequiredArgsConstructor
public class CusCustomerService {

    private final CusCustomerEntityService cusCustomerEntityService;
    private final PasswordEncoder passwordEncoder;

    public CusCustomerDto save(CusCustomerSaveRequestDto cusCustomerSaveRequestDto) {
        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto);
        cusCustomer.setPassword(passwordEncoder.encode(cusCustomer.getPassword()));
        cusCustomer = cusCustomerEntityService.save(cusCustomer);
        return CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);
    }

    public List<CusCustomerDto> findAll() {
        List<CusCustomer> cusCustomerList = cusCustomerEntityService.findAll();
        return CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList);
    }

    public void delete(Long id) {
        CusCustomer cusCustomer = cusCustomerEntityService.getByIdWithControl(id);
        cusCustomerEntityService.delete(cusCustomer);

    }

    public CusCustomerDto findById(Long id) {
        CusCustomer cusCustomer = cusCustomerEntityService.getByIdWithControl(id);
        return CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);
    }


    public CusCustomerDto update(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {
        controlIsCustomerExist(cusCustomerUpdateRequestDto);

        CusCustomer cusCustomer = CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerUpdateRequestDto);
        cusCustomer = cusCustomerEntityService.save(cusCustomer);

        return CusCustomerMapper.INSTANCE.convertToCusCustomerDto(cusCustomer);
    }

    private void controlIsCustomerExist(CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto) {
        Long id = cusCustomerUpdateRequestDto.getId();
        boolean isExist = cusCustomerEntityService.existsById(id);
        if (!isExist) {
            throw new ItemNotFoundException(CusErrorMessage.CUSTOMER_ERROR_MESSAGE);
        }
    }
}