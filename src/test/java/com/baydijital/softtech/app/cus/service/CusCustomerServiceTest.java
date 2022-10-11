package com.baydijital.softtech.app.cus.service;

import com.baydijital.softtech.app.crd.enums.CrdErrorMessage;
import com.baydijital.softtech.app.cus.converter.CusCustomerMapper;
import com.baydijital.softtech.app.cus.dto.CusCustomerDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerSaveRequestDto;
import com.baydijital.softtech.app.cus.dto.CusCustomerUpdateRequestDto;
import com.baydijital.softtech.app.cus.entity.CusCustomer;
import com.baydijital.softtech.app.cus.enums.CusErrorMessage;
import com.baydijital.softtech.app.cus.service.entityservice.CusCustomerEntityService;
import com.baydijital.softtech.app.gen.exceptions.ItemNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Gokalp on 9/6/22
 */
@ExtendWith(MockitoExtension.class)
class CusCustomerServiceTest {

    @InjectMocks
    private CusCustomerService cusCustomerService;
    @Mock
    private CusCustomerEntityService cusCustomerEntityService;
    @Mock
    private PasswordEncoder passwordEncoder;


    @Test
    void findAll() {
        //CusCustomerMapper cusCustomerMapper = Mockito.spy(CusCustomerMapper.class);

        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        List<CusCustomer> cusCustomerList = new ArrayList<>();
        cusCustomerList.add(cusCustomer);

        CusCustomerDto cusCustomerDto = Mockito.mock(CusCustomerDto.class);
        List<CusCustomerDto> cusCustomerDtoList = new ArrayList<>();
        cusCustomerDtoList.add(cusCustomerDto);
        Mockito.when(cusCustomerEntityService.findAll()).thenReturn(cusCustomerList);
        //Mockito.when(CusCustomerMapper.INSTANCE.convertToCusCustomerDtoList(cusCustomerList)).thenReturn(cusCustomerDtoList);

        List<CusCustomerDto> result = cusCustomerService.findAll();

        assertEquals(1, result.size());


    }

    @Test
    void shouldSave() {
        CusCustomerSaveRequestDto cusCustomerSaveRequestDto = Mockito.mock(CusCustomerSaveRequestDto.class);
        Mockito.when(cusCustomerSaveRequestDto.getPassword()).thenReturn("123");

        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        Mockito.when(cusCustomer.getId()).thenReturn(1L);


//        try (MockedStatic<CusCustomerMapper> cusCustomerMapperMockedStatic = Mockito.mockStatic(CusCustomerMapper.class)) {
//            cusCustomerMapperMockedStatic.when(() -> CusCustomerMapper.INSTANCE.convertToCusCustomer(cusCustomerSaveRequestDto)).thenReturn(cusCustomer);
//        }

        //Mockito.when((passwordEncoder.encode(Mockito.anyString()))).thenReturn("123");
        Mockito.when((passwordEncoder.encode(Mockito.anyString()))).thenReturn("123");
        Mockito.when(cusCustomerEntityService.save(Mockito.any())).thenReturn(cusCustomer);

        CusCustomerDto result = cusCustomerService.save(cusCustomerSaveRequestDto);

        Assertions.assertEquals(1L, result.getId());
    }

    @Test
    void shouldNotSaveWhenParameterIsNull() {
        Assertions.assertThrows(NullPointerException.class, () -> cusCustomerService.save(null));
    }


    @Test
    void shouldDelete() {
        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);

        Mockito.when(cusCustomerEntityService.getByIdWithControl(Mockito.anyLong())).thenReturn(cusCustomer);

        cusCustomerService.delete(Mockito.anyLong());

        Mockito.verify(cusCustomerEntityService).getByIdWithControl(Mockito.anyLong());
        Mockito.verify(cusCustomerEntityService).delete(Mockito.any());
    }

    @Test
    void shouldDeleteWhenIdDoesNotExist() {

        Mockito.when(cusCustomerEntityService.getByIdWithControl(Mockito.anyLong())).thenThrow(ItemNotFoundException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> cusCustomerService.delete(Mockito.anyLong()));
    }

    @Test
    void shouldFindById() {
        Long id = 18L;
        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        Mockito.when(cusCustomer.getId()).thenReturn(id);

        Mockito.when(cusCustomerEntityService.getByIdWithControl(Mockito.anyLong())).thenReturn(cusCustomer);
        CusCustomerDto cusCustomerDto = cusCustomerService.findById(id);

        Assertions.assertEquals(id,cusCustomerDto.getId());
    }

    @Test
    void shouldNotFindByIdWhenIdDoesNotExist() {

        Mockito.when(cusCustomerEntityService.getByIdWithControl(Mockito.anyLong())).thenThrow(ItemNotFoundException.class);
        Assertions.assertThrows(ItemNotFoundException.class, () -> cusCustomerService.findById(Mockito.anyLong()));
        Mockito.verify(cusCustomerEntityService).getByIdWithControl(Mockito.anyLong());
    }

    @Test
    void shouldUpdate() {
        Long id = 18L;
        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = Mockito.mock(CusCustomerUpdateRequestDto.class);
        CusCustomer cusCustomer = Mockito.mock(CusCustomer.class);
        Mockito.when(cusCustomer.getId()).thenReturn(id);

        boolean isExist = true;
        Mockito.when(cusCustomerEntityService.save(Mockito.any())).thenReturn(cusCustomer);
        Mockito.when(cusCustomerEntityService.existsById(Mockito.anyLong())).thenReturn(isExist);
        CusCustomerDto cusCustomerDto = cusCustomerService.update(cusCustomerUpdateRequestDto);

        Assertions.assertEquals(id, cusCustomerDto.getId());

        Mockito.verify(cusCustomerEntityService).existsById(Mockito.anyLong());
    }

    @Test
    void shouldNotUpdateWhenCustomerDoesNotExist() {
        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = Mockito.mock(CusCustomerUpdateRequestDto.class);

        Mockito.when(cusCustomerEntityService.existsById(Mockito.any())).thenThrow(ItemNotFoundException.class);

        Assertions.assertThrows(ItemNotFoundException.class, () -> cusCustomerService.update(cusCustomerUpdateRequestDto));

        Mockito.verify(cusCustomerEntityService).existsById(Mockito.anyLong());
    }

    @Test
    void shouldNotUpdateWhenCustomerDoesNotExist2() {
        CusCustomerUpdateRequestDto cusCustomerUpdateRequestDto = Mockito.mock(CusCustomerUpdateRequestDto.class);

        Mockito.when(cusCustomerEntityService.existsById(Mockito.any())).thenReturn(false);

        ItemNotFoundException itemNotFoundException = assertThrows(ItemNotFoundException.class, () -> cusCustomerService.update(cusCustomerUpdateRequestDto));
        assertEquals(CusErrorMessage.CUSTOMER_ERROR_MESSAGE, itemNotFoundException.getBaseErrorMessage());

        Mockito.verify(cusCustomerEntityService).existsById(Mockito.anyLong());
    }
}